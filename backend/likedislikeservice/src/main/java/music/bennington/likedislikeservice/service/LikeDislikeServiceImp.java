package music.bennington.likedislikeservice.service;

import music.bennington.likedislikeservice.entity.LikeDislikeEntity;
import music.bennington.likedislikeservice.excption.LikeDislikeStatusException;
import music.bennington.likedislikeservice.model.LikeDislikeStatus;
import music.bennington.likedislikeservice.repo.LikeDislikeRepo;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LikeDislikeServiceImp implements LikeDislikeService{
    @Autowired
    LikeDislikeRepo likeDislikeRepo;

    private static final Logger LOGGER = Logger.getLogger(LikeDislikeServiceImp.class);


    @Override
    public LikeDislikeStatus likeDislikeStatus(LikeDislikeEntity likeDislikeEntity) {

        LikeDislikeStatus status = LikeDislikeStatus.NONE;
        try {

            LikeDislikeEntity likeDislike = likeDislikeRepo.likeDislikeStatus(likeDislikeEntity.getMusicId(), likeDislikeEntity.getUserId());
            if (likeDislike != null) {
                if (likeDislike.isLiked()) {
                    status = LikeDislikeStatus.LIKED;
                } else if (likeDislike.isDisliked()) {
                    status = LikeDislikeStatus.DISLIKED;
                }
                else{
                    status = LikeDislikeStatus.NUTRAL;
                }
            }
        }

        catch (Exception e) {
            status = LikeDislikeStatus.ERROR;
        }

        LOGGER.debug(status);
        return status;
    }

    @Override
    public ResponseEntity<Object> addOrOmitLike(LikeDislikeEntity likeDislikeEntity) {

        ResponseEntity<Object> response = null;

        try {
            LikeDislikeStatus status = this.likeDislikeStatus(likeDislikeEntity);
            String message="";
            String musicId = likeDislikeEntity.getMusicId();
            String userId = likeDislikeEntity.getUserId();

            if(status == LikeDislikeStatus.ERROR){
                throw new LikeDislikeStatusException("Error getting Like-Dislike stats from Database");
            }

            else {
                if(status == LikeDislikeStatus.NONE){
                    likeDislikeEntity.setLiked(true);
                    likeDislikeRepo.save(likeDislikeEntity);
                    message = "LIKE-ADDED";
                }
                else if(status == LikeDislikeStatus.DISLIKED){

                    likeDislikeRepo.changeLikeStatus(musicId, userId, true);
                    likeDislikeRepo.changeDisLikeStatus(musicId, userId, false);

                    message = "LIKE-ADDED-DISLIKE-OMITTED";
                }

                else if(status == LikeDislikeStatus.LIKED){
                    likeDislikeRepo.changeLikeStatus(musicId, userId, false);
                    message = "LIKE-OMITTED";
                }

                else{
                    likeDislikeRepo.changeLikeStatus(musicId, userId, true);
                    message = "LIKE-ADDED";
                }

                response = new ResponseEntity<>(message, HttpStatus.OK);
            }
        }
        catch (Exception e) {
            response = new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        LOGGER.debug(response);
        return response;
    }

    @Override
    public ResponseEntity<Object> addOrOmitDislike(LikeDislikeEntity likeDislikeEntity) {
        ResponseEntity<Object> response = null;

        try {
            LikeDislikeStatus status = this.likeDislikeStatus(likeDislikeEntity);
            String message="";
            String musicId = likeDislikeEntity.getMusicId();
            String userId = likeDislikeEntity.getUserId();

            if(status == LikeDislikeStatus.ERROR){
                throw new LikeDislikeStatusException("Error getting Like-Dislike stats from Database");
            }

            else {
                if(status == LikeDislikeStatus.NONE){
                    likeDislikeEntity.setDisliked(true);
                    likeDislikeRepo.save(likeDislikeEntity);

                    message = "DISLIKE-ADDED";
                }
                else if(status == LikeDislikeStatus.LIKED){
                    likeDislikeRepo.changeDisLikeStatus(musicId, userId, true);
                    likeDislikeRepo.changeLikeStatus(musicId, userId, false);

                    message = "DISLIKE-ADDED-LIKE-OMITTED";
                }

                else if(status == LikeDislikeStatus.DISLIKED){
                    likeDislikeRepo.changeDisLikeStatus(musicId, userId, false);
                    message = "DISLIKE-OMITTED";
                }

                else{
                    likeDislikeRepo.changeDisLikeStatus(musicId, userId, true);
                    message = "DISLIKE-ADDED";
                }

                response = new ResponseEntity<>(message, HttpStatus.OK);
            }
        }
        catch (Exception e) {
            response = new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        LOGGER.debug(response);
        return response;
    }

    @Override
    public ResponseEntity<Object> showLikedUsers(LikeDislikeEntity likeDislikeEntity) {
        ResponseEntity<Object> response = null;

        try {
            List<String> list;
            list = likeDislikeRepo.showLikedUsers(likeDislikeEntity.getMusicId());
            response = new ResponseEntity<>(list, HttpStatus.OK);


        } catch (Exception e) {
            response = new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        LOGGER.debug(response);
        return response;
    }

    @Override
    public ResponseEntity<Object> showDislikedUsers(LikeDislikeEntity likeDislikeEntity) {
        ResponseEntity<Object> response = null;

        try {
            List<String> list;
            list = likeDislikeRepo.showDisLikedUsers(likeDislikeEntity.getMusicId());
            response = new ResponseEntity<>(list, HttpStatus.OK);


        } catch (Exception e) {
            response = new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        LOGGER.debug(response);
        return response;
    }

    @Override
    public ResponseEntity<Object> showLikeCount(LikeDislikeEntity likeDislikeEntity) {
        ResponseEntity<Object> response = null;

        try {
            int likeCount = likeDislikeRepo.likeCount(likeDislikeEntity.getMusicId());
            response = new ResponseEntity<>(likeCount, HttpStatus.OK);


        } catch (Exception e) {
            response = new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        LOGGER.debug(response);
        return response;
    }

    @Override
    public ResponseEntity<Object> showDislikeCount(LikeDislikeEntity likeDislikeEntity) {
        ResponseEntity<Object> response = null;

        try {
            int disLikeCount = likeDislikeRepo.disLikeCount(likeDislikeEntity.getMusicId());
            response = new ResponseEntity<>(disLikeCount, HttpStatus.OK);


        } catch (Exception e) {
            response = new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        LOGGER.debug(response);
        return response;
    }
}
