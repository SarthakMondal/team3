package music.bennington.userservice.service;

import music.bennington.userservice.model.MusicModel;
import org.springframework.http.ResponseEntity;

public interface UserMusicService {
    public ResponseEntity<Object> addAlbumByAdmin(MusicModel musicModel);
    public ResponseEntity<Object> addMusicToAlbumByAdmin(MusicModel musicModel);

    public ResponseEntity<Object> updateAlbumByAdmin(MusicModel musicModel);
    public ResponseEntity<Object> updateMusicByAdmin(MusicModel musicModel);

    public ResponseEntity<Object> removeAlbumByAdmin(MusicModel musicModel);
    public ResponseEntity<Object> removeMusicByAdmin(MusicModel musicModel);

    public ResponseEntity<Object> viewAllAlbums();
    public ResponseEntity<Object> viewAlbumSongs(MusicModel musicModel);
    public ResponseEntity<Object> viewAlbum(MusicModel musicModel);
    public ResponseEntity<Object> viewMusic(MusicModel musicModel);

    public ResponseEntity<Object> searchAlbum(MusicModel musicModel);
    public ResponseEntity<Object> searchMusic(MusicModel musicModel);


}
