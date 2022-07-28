package music.bennington.userservice.service;

import music.bennington.userservice.model.MusicModel;
import org.springframework.http.ResponseEntity;

public interface UserLikeDislikeForMusicService {
    public ResponseEntity<Object> likeOrOmitLikeByUser(MusicModel musicModel);
    public ResponseEntity<Object> dislikeOrOmitDislikeByUser(MusicModel musicModel);
    public ResponseEntity<Object> getLikeCountByUser(MusicModel musicModel);
    public ResponseEntity<Object> getDislikeCountByUser(MusicModel musicModel);
    public ResponseEntity<Object> getLikedUsersByAdmin(MusicModel musicModel);
    public ResponseEntity<Object> getDislikedUsersByAdmin(MusicModel musicModel);
}
