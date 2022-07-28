package music.bennington.userservice.service;

import music.bennington.userservice.model.MusicModel;
import org.springframework.http.ResponseEntity;

public interface UserCommentForMusicService {
    public ResponseEntity<Object> addCommentByUser(MusicModel musicModel);
    public ResponseEntity<Object> viewMyCommentsByUser(MusicModel musicModel);
    public ResponseEntity<Object> viewAllCommentsByAdmin(MusicModel musicModel);
}
