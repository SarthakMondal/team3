package music.bennington.likedislikeservice.service;

import music.bennington.likedislikeservice.entity.LikeDislikeEntity;
import music.bennington.likedislikeservice.model.LikeDislikeStatus;
import org.springframework.http.ResponseEntity;

public interface LikeDislikeService
{
    public LikeDislikeStatus likeDislikeStatus(LikeDislikeEntity likeDislikeEntity);
    public ResponseEntity<Object> addOrOmitLike(LikeDislikeEntity likeDislikeEntity);
    public ResponseEntity<Object> addOrOmitDislike(LikeDislikeEntity likeDislikeEntity);
    public ResponseEntity<Object> showLikedUsers(LikeDislikeEntity likeDislikeEntity);
    public ResponseEntity<Object> showDislikedUsers(LikeDislikeEntity likeDislikeEntity);
    public ResponseEntity<Object> showLikeCount(LikeDislikeEntity likeDislikeEntity);
    public ResponseEntity<Object> showDislikeCount(LikeDislikeEntity likeDislikeEntity);


}
