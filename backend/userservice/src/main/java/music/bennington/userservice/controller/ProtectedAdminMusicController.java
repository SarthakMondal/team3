package music.bennington.userservice.controller;

import music.bennington.userservice.model.MusicModel;
import music.bennington.userservice.service.UserCommentForMusicService;
import music.bennington.userservice.service.UserLikeDislikeForMusicService;
import music.bennington.userservice.service.UserMusicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/musicapp/backend/admin/protected")
public class ProtectedAdminMusicController
{
    @Autowired
    UserMusicService userMusic;

    @Autowired
    UserCommentForMusicService commentForMusicService;

    @Autowired
    UserLikeDislikeForMusicService likeDislikeForMusicService;

    @PostMapping(path="/addalbum")
    public ResponseEntity<Object> addAlbumByAdmin(@RequestHeader(value = "accessedThroughGateway") String header, @RequestBody MusicModel musicModel)
    {
        return this.userMusic.addAlbumByAdmin(musicModel);
    }

    @PostMapping(path="/addmusic")
    public ResponseEntity<Object> addMusicByAdmin(@RequestHeader(value = "accessedThroughGateway") String header, @RequestBody MusicModel musicModel)
    {
        return this.userMusic.addMusicToAlbumByAdmin(musicModel);
    }

    @PutMapping(path="/updatealbum")
    public ResponseEntity<Object> updateAlbumByAdmin(@RequestHeader(value = "accessedThroughGateway") String header, @RequestBody MusicModel musicModel)
    {
        return this.userMusic.updateAlbumByAdmin(musicModel);
    }

    @PutMapping(path="/updatemusic")
    public ResponseEntity<Object> updateMusicByAdmin(@RequestHeader(value = "accessedThroughGateway") String header, @RequestBody MusicModel musicModel)
    {
        return this.userMusic.updateMusicByAdmin(musicModel);
    }

    @DeleteMapping(path="/deletealbum")
    public ResponseEntity<Object> deleteAlbumByAdmin(@RequestHeader(value = "accessedThroughGateway") String header, @RequestBody MusicModel musicModel)
    {
        return this.userMusic.removeAlbumByAdmin(musicModel);
    }

    @DeleteMapping(path="/deletemusic")
    public ResponseEntity<Object> deleteMusicByAdmin(@RequestHeader(value = "accessedThroughGateway") String header, @RequestBody MusicModel musicModel)
    {
        return this.userMusic.removeMusicByAdmin(musicModel);
    }

    @PostMapping(path = "/getlikedusers/{musicId}")
    public ResponseEntity<Object> getLikedUsersByAdmin(@RequestHeader(value = "accessedThroughGateway") String header, @PathVariable String musicId)
    {
        MusicModel musicModel = new MusicModel();
        musicModel.setMusicId(musicId);
        return this.likeDislikeForMusicService.getLikedUsersByAdmin(musicModel);
    }

    @PostMapping(path = "/getdislikedusers/{musicId}")
    public ResponseEntity<Object> getDislikedUsersByAdmin(@RequestHeader(value = "accessedThroughGateway") String header, @PathVariable String musicId)
    {
        MusicModel musicModel = new MusicModel();
        musicModel.setMusicId(musicId);
        return this.likeDislikeForMusicService.getDislikedUsersByAdmin(musicModel);
    }

    @PostMapping(path = "/getallcomments/{musicId}")
    public ResponseEntity<Object> getAllCommentsByAdmin(@RequestHeader(value = "accessedThroughGateway") String header, @PathVariable String musicId)
    {
        MusicModel musicModel = new MusicModel();
        musicModel.setMusicId(musicId);
        return this.commentForMusicService.viewAllCommentsByAdmin(musicModel);
    }

}
