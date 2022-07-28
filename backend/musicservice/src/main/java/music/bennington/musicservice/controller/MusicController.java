package music.bennington.musicservice.controller;

import music.bennington.musicservice.entity.MusicAlbumEntity;
import music.bennington.musicservice.entity.MusicEntity;
import music.bennington.musicservice.model.AlbumModel;
import music.bennington.musicservice.model.MusicModel;
import music.bennington.musicservice.service.MusicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/backend/musicservice")
public class MusicController
{
    @Autowired
    MusicService musicService;

    @PostMapping(path = "/addmusicalbum")
    public ResponseEntity<Object> insertMusicAlbum(@RequestHeader(value = "accessedByAdmin") String header, @RequestBody AlbumModel albumModel){
    	MusicAlbumEntity musicAlbum = new MusicAlbumEntity();
    	musicAlbum.setAlbumName(albumModel.getAlbumName());
    	musicAlbum.setAlbumImageUrl(albumModel.getAlbumImageUrl());
    	musicAlbum.setPublishYear(albumModel.getPublishYear());
    
        return this.musicService.insertMusicAlbum(musicAlbum);
    }

    @PostMapping(path = "/addmusictoalbum/{id}")
    public ResponseEntity<Object> addMusicToAlbum(@RequestHeader(value = "accessedByAdmin") String header, @PathVariable String id, @RequestBody MusicModel musicModel){
    	MusicEntity musicEntity = new MusicEntity();
    	musicEntity.setArtistName(musicModel.getArtistName());
    	musicEntity.setMusicName(musicModel.getMusicName());
    	musicEntity.setMusicUrl(musicModel.getMusicUrl());
    	musicEntity.setMusicAlbum(musicModel.getMusicAlbum());
    	musicEntity.setAddedByAdminEmail(musicModel.getAddedByAdminEmail());
    	
    	return this.musicService.addMusicToAlbum(id, musicEntity);
    }

    @PutMapping(path = "/updatealbum/{id}")
    public ResponseEntity<Object>updateMusicAlbum(@RequestHeader(value = "accessedByAdmin") String header, @PathVariable String id, @RequestBody AlbumModel albumModel){
    	MusicAlbumEntity musicAlbum = new MusicAlbumEntity();
    	musicAlbum.setAlbumName(albumModel.getAlbumName());
    	musicAlbum.setAlbumImageUrl(albumModel.getAlbumImageUrl());
    	musicAlbum.setPublishYear(albumModel.getPublishYear());
    	
    	return this.musicService.updateMusicAlbum(id, musicAlbum);
    }

    @PutMapping(path = "/updatemusic/{id}")
    public ResponseEntity<Object> updateMusic(@RequestHeader(value = "accessedByAdmin") String header, @PathVariable String id, @RequestBody MusicModel musicModel){
    	MusicEntity musicEntity = new MusicEntity();
    	musicEntity.setArtistName(musicModel.getArtistName());
    	musicEntity.setMusicName(musicModel.getMusicName());
    	musicEntity.setMusicUrl(musicModel.getMusicUrl());
    	musicEntity.setMusicAlbum(musicModel.getMusicAlbum());
    	musicEntity.setAddedByAdminEmail(musicModel.getAddedByAdminEmail());
    	
        return this.musicService.updateMusic(id, musicEntity);
    }

    @DeleteMapping(path = "/deletealbum/{id}")
    public ResponseEntity<Object> removeMusicAlbum(@RequestHeader(value = "accessedByAdmin") String header, @PathVariable String id){
        return this.musicService.removeMusicAlbum(id);
    }

    @DeleteMapping(path = "/deletemusic/{id}")
    public ResponseEntity<Object> removeMusic(@RequestHeader(value = "accessedByAdmin") String header, @PathVariable String id){
        return this.musicService.removeMusic(id);
    }

    @GetMapping(path = "/viewallalbums")
    public ResponseEntity<Object> viewAllAlbums(){
        return this.musicService.viewAllAlbums();
    }

    @GetMapping(path = "/viewmusicsofalbum/{id}")
    public ResponseEntity<Object> viewAlbumMusics(@RequestHeader(value = "accessedByLoggedInUser") String header, @PathVariable String id){
        return this.musicService.viewAlbumMusics(id);
    }

    @GetMapping(path = "/viewalbum/{id}")
    public ResponseEntity<Object> viewAlbumDetails(@RequestHeader(value = "accessedByLoggedInUser") String header, @PathVariable String id){
        return this.musicService.viewAlbumDetails(id);
    }

    @GetMapping(path = "/viewmusic/{id}")
    public ResponseEntity<Object> viewMusicDetails(@RequestHeader(value = "accessedByLoggedInUser") String header, @PathVariable String id){
        return this.musicService.viewMusicDetails(id);
    }

    @GetMapping(path = "/searchalbum/{keyword}")
    public ResponseEntity<Object> searchAlbum(@RequestHeader(value = "accessedByLoggedInUser") String header, @PathVariable String keyword){
        return this.musicService.searchAlbum(keyword);
    }

    @GetMapping(path = "/searchmusic/{keyword}")
    public ResponseEntity<Object> searchMusic(@RequestHeader(value = "accessedByLoggedInUser") String header, @PathVariable String keyword){
        return this.musicService.searchMusic(keyword);
    }


}
