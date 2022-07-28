package music.bennington.likedislikeservice.service;

import music.bennington.likedislikeservice.entity.CommentEntity;
import music.bennington.likedislikeservice.excption.CommentBlockedException;
import music.bennington.likedislikeservice.model.WordBlocker;
import music.bennington.likedislikeservice.repo.CommentRepo;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class CommentServiceImp implements CommentService{

    @Autowired
    CommentRepo commentRepo;

    private static final Logger LOGGER = Logger.getLogger(CommentServiceImp.class);
    @Override
    public ResponseEntity<Object> addComment(CommentEntity commentEntity) {
        ResponseEntity<Object> response = null;

        try{
            String comment = commentEntity.getMusicComment();
            Set<String> blockedWords = Arrays.stream(WordBlocker.offensiveWords).collect(Collectors.toSet());
            Set<String> commentWords = Arrays.stream(comment.split(" ")).collect(Collectors.toSet());

            if(this.isOffensive(blockedWords, commentWords)){
                throw new CommentBlockedException("Your Comment is violating our Community rules");
            }
            else{
                Date date = new Date();
                SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss dd-MM-yyyy");
                formatter.setTimeZone(TimeZone.getTimeZone("Asia/Kolkata"));
                formatter.format(date);

                commentEntity.setCommentTime(date);

                commentRepo.save(commentEntity);
                response = new ResponseEntity<>("COMMENT-ADDED", HttpStatus.OK);
            }

        }
        catch (CommentBlockedException e) {
            response = new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
        }

        catch (Exception e) {
            response = new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        LOGGER.debug(response);
        return response;

    }

    @Override
    public ResponseEntity<Object> viewMyComments(String musicId, String userId) {
        ResponseEntity<Object> response = null;

        try{
            List<CommentEntity> commentEntities = commentRepo.findByMusicIdAndUserId(musicId, userId);
            response = new ResponseEntity<>(commentEntities, HttpStatus.OK);
        }
        catch (Exception e) {
            response = new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        LOGGER.debug(response);
        return response;
    }

    @Override
    public ResponseEntity<Object> viewCommentsOfMusic(String musicId) {
        ResponseEntity<Object> response = null;

        try{
            List<CommentEntity> commentEntities = commentRepo.findByMusicId(musicId);
            response = new ResponseEntity<>(commentEntities, HttpStatus.OK);
        }
        catch (Exception e) {
            response = new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        LOGGER.debug(response);
        return response;
    }

    private boolean isOffensive(Set<String> blockedWords, Set<String> commentWords){
        boolean offensive = false;

        for(String word1: commentWords){
            for(String word2: blockedWords){
                if(word1.contains(word2)){
                    offensive = true;
                    break;
                }
            }
        }

        return offensive;
    }
}
