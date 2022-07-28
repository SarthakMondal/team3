package music.bennington.musicservice.service;

import music.bennington.musicservice.model.LikeDislikeSendModel;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

public interface MusicLikeDislikeService {
    public ResponseEntity<Object> likeOrOmitLike(HttpHeaders headers, LikeDislikeSendModel sendModel);
    public ResponseEntity<Object> disLikeOrOmitDislike(HttpHeaders headers, LikeDislikeSendModel sendModel);
    public ResponseEntity<Object> getLikeCount(HttpHeaders headers, LikeDislikeSendModel sendModel);
    public ResponseEntity<Object> getDislikeCount(HttpHeaders headers, LikeDislikeSendModel sendModel);
    public ResponseEntity<Object> getLikedUsers(HttpHeaders headers, LikeDislikeSendModel sendModel);
    public ResponseEntity<Object> getDisLikedUsers(HttpHeaders headers, LikeDislikeSendModel sendModel);
}
