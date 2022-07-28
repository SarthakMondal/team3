package music.bennington.musicservice.service;

import music.bennington.musicservice.entity.MusicAlbumEntity;
import music.bennington.musicservice.entity.MusicEntity;
import org.springframework.http.ResponseEntity;

public interface MusicService
{
    public ResponseEntity<Object> insertMusicAlbum(MusicAlbumEntity musicAlbum);
    public ResponseEntity<Object> addMusicToAlbum(String albumId, MusicEntity musicEntity);

    public ResponseEntity<Object> updateMusicAlbum(String albumId, MusicAlbumEntity oldMusicAlbum);
    public ResponseEntity<Object> updateMusic(String musicId, MusicEntity oldMusicEntity);

    public ResponseEntity<Object> removeMusicAlbum(String albumId);
    public ResponseEntity<Object> removeMusic(String musicId);

    public ResponseEntity<Object> viewAllAlbums();
    public ResponseEntity<Object> viewAlbumMusics(String albumId);
    public ResponseEntity<Object> viewAlbumDetails(String albumId);
    public ResponseEntity<Object> viewMusicDetails(String musicId);

    public ResponseEntity<Object> searchAlbum(String keyword);
    public ResponseEntity<Object> searchMusic(String keyword);

}
