package music.bennington.likedislikeservice.controller;

import music.bennington.likedislikeservice.entity.LikeDislikeEntity;
import music.bennington.likedislikeservice.model.LikeDislikeModel;
import music.bennington.likedislikeservice.service.LikeDislikeService;
import org.springframework.web.bind.annotation.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping(path = "/backend/likedislikeservice")
public class LikeDislikeController {

    @Autowired
    LikeDislikeService likeDislikeService;

    @PostMapping(path = "/addormonitlike")
    public ResponseEntity<Object> addOrOmitLike(@RequestHeader(value = "accessedByLoggedInUser") String header, @RequestBody LikeDislikeModel likeDislikeModel) {

    	LikeDislikeEntity likeDislikeEntity = new LikeDislikeEntity();
    	likeDislikeEntity.setUserId(likeDislikeModel.getUserId());
    	likeDislikeEntity.setMusicId(likeDislikeModel.getMusicId());
    	likeDislikeEntity.setLiked(likeDislikeModel.isLiked());
    	likeDislikeEntity.setLiked(likeDislikeModel.isDisliked());
        return this.likeDislikeService.addOrOmitLike(likeDislikeEntity);

    }

    @PostMapping(path = "/addoromitdislike")
    public ResponseEntity<Object> addOrOmitDislike(@RequestHeader(value = "accessedByLoggedInUser") String header,@RequestBody LikeDislikeModel likeDislikeModel) {
    	LikeDislikeEntity likeDislikeEntity = new LikeDislikeEntity();
    	likeDislikeEntity.setUserId(likeDislikeModel.getUserId());
    	likeDislikeEntity.setMusicId(likeDislikeModel.getMusicId());
    	likeDislikeEntity.setLiked(likeDislikeModel.isLiked());
    	likeDislikeEntity.setLiked(likeDislikeModel.isDisliked());
        return this.likeDislikeService.addOrOmitDislike(likeDislikeEntity);

    }

    @PostMapping("/likecount")
    public ResponseEntity<Object> likeCount(@RequestHeader(value = "accessedByLoggedInUser") String header,@RequestBody LikeDislikeModel likeDislikeModel) {
    	LikeDislikeEntity likeDislikeEntity = new LikeDislikeEntity();
    	likeDislikeEntity.setUserId(likeDislikeModel.getUserId());
    	likeDislikeEntity.setMusicId(likeDislikeModel.getMusicId());
    	likeDislikeEntity.setLiked(likeDislikeModel.isLiked());
    	likeDislikeEntity.setLiked(likeDislikeModel.isDisliked());
    	return this.likeDislikeService.showLikeCount(likeDislikeEntity);
    }

    @PostMapping("/disLikeCount")
    public ResponseEntity<Object> dislikeCount(@RequestHeader(value = "accessedByLoggedInUser") String header,@RequestBody LikeDislikeModel likeDislikeModel) {
    	LikeDislikeEntity likeDislikeEntity = new LikeDislikeEntity();
    	likeDislikeEntity.setUserId(likeDislikeModel.getUserId());
    	likeDislikeEntity.setMusicId(likeDislikeModel.getMusicId());
    	likeDislikeEntity.setLiked(likeDislikeModel.isLiked());
    	likeDislikeEntity.setLiked(likeDislikeModel.isDisliked());
    	return this.likeDislikeService.showDislikeCount(likeDislikeEntity);
    }

    @PostMapping(path = "/showlikeduserslist")
    public ResponseEntity<Object> showLikedUsers(@RequestHeader(value = "accessedByAdmin") String header,@RequestBody LikeDislikeModel likeDislikeModel) {
    	LikeDislikeEntity likeDislikeEntity = new LikeDislikeEntity();
    	likeDislikeEntity.setUserId(likeDislikeModel.getUserId());
    	likeDislikeEntity.setMusicId(likeDislikeModel.getMusicId());
    	likeDislikeEntity.setLiked(likeDislikeModel.isLiked());
    	likeDislikeEntity.setLiked(likeDislikeModel.isDisliked());
    	return this.likeDislikeService.showLikedUsers(likeDislikeEntity);

    }

    @PostMapping("/showdislikeduserslist")
    public ResponseEntity<Object> showDislikedUsers(@RequestHeader(value = "accessedByAdmin") String header,@RequestBody LikeDislikeModel likeDislikeModel) {
    	LikeDislikeEntity likeDislikeEntity = new LikeDislikeEntity();
    	likeDislikeEntity.setUserId(likeDislikeModel.getUserId());
    	likeDislikeEntity.setMusicId(likeDislikeModel.getMusicId());
    	likeDislikeEntity.setLiked(likeDislikeModel.isLiked());
    	likeDislikeEntity.setLiked(likeDislikeModel.isDisliked());
    	return this.likeDislikeService.showDislikedUsers(likeDislikeEntity);

    }
}
