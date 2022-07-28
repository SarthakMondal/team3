package music.bennington.likedislikeservice.service;

import music.bennington.likedislikeservice.entity.CommentEntity;
import org.springframework.http.ResponseEntity;

public interface CommentService {
    public ResponseEntity<Object> addComment(CommentEntity commentEntity);
    public ResponseEntity<Object> viewMyComments(String musicId, String userId);
    public ResponseEntity<Object> viewCommentsOfMusic(String musicId);
}
