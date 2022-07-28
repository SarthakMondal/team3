package music.bennington.musicservice.service;

import music.bennington.musicservice.model.LikeDislikeSendModel;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

public interface MusicCommentService {
    public ResponseEntity<Object> addCommentToMusic(HttpHeaders headers, LikeDislikeSendModel sendModel);
    public ResponseEntity<Object> viewUsersCommentsOnMusic(HttpHeaders headers, LikeDislikeSendModel sendModel);
    public ResponseEntity<Object> viewAllCommentsOnMusic(HttpHeaders headers, LikeDislikeSendModel sendModel);

}
