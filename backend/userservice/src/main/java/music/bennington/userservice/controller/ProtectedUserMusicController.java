package music.bennington.userservice.controller;

import music.bennington.userservice.model.MusicModel;
import music.bennington.userservice.service.UserCommentForMusicService;
import music.bennington.userservice.service.UserLikeDislikeForMusicService;
import music.bennington.userservice.service.UserMusicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/musicapp/backend/user/protected")
public class ProtectedUserMusicController {
    @Autowired
    UserMusicService userMusic;

    @Autowired
    UserCommentForMusicService commentForMusicService;

    @Autowired
    UserLikeDislikeForMusicService likeDislikeForMusicService;

    @GetMapping(path = "/viewallalbums")
    public ResponseEntity<Object> viewAlbums(@RequestHeader(value = "accessedThroughGateway") String header)
    {
        return this.userMusic.viewAllAlbums();
    }

    @GetMapping(path = "/viewalbumsongs/{id}")
    public ResponseEntity<Object> viewAlbumSongs(@RequestHeader(value = "accessedThroughGateway") String header, @PathVariable String id)
    {
        MusicModel musicModel = new MusicModel();
        musicModel.setAlbumId(id);
        return this.userMusic.viewAlbumSongs(musicModel);
    }

    @GetMapping(path = "/viewalbum/{id}")
    public ResponseEntity<Object> viewAlbum(@RequestHeader(value = "accessedThroughGateway") String header, @PathVariable String id)
    {
        MusicModel musicModel = new MusicModel();
        musicModel.setAlbumId(id);
        return this.userMusic.viewAlbum(musicModel);
    }

    @GetMapping(path = "/viewmusic/{id}")
    public ResponseEntity<Object> viewMusic(@RequestHeader(value = "accessedThroughGateway") String header, @PathVariable String id)
    {
        MusicModel musicModel = new MusicModel();
        musicModel.setMusicId(id);
        return this.userMusic.viewMusic(musicModel);
    }

    @GetMapping(path = "/searchalbum/{keyword}")
    public ResponseEntity<Object> searchAlbum(@RequestHeader(value = "accessedThroughGateway") String header, @PathVariable String keyword)
    {
        MusicModel musicModel = new MusicModel();
        musicModel.setKeyword(keyword);
        return this.userMusic.searchAlbum(musicModel);
    }

    @GetMapping(path = "/searchmusic/{keyword}")
    public ResponseEntity<Object> searchMusic(@RequestHeader(value = "accessedThroughGateway") String header, @PathVariable String keyword)
    {
        MusicModel musicModel = new MusicModel();
        musicModel.setKeyword(keyword);
        return this.userMusic.searchMusic(musicModel);
    }

    @PostMapping(path = "/likemusic/{musicId}")
    public ResponseEntity<Object> likeMusicByUser(@RequestHeader(value = "accessedThroughGateway") String header, @PathVariable String musicId)
    {
        MusicModel musicModel = new MusicModel();
        musicModel.setMusicId(musicId);
        return this.likeDislikeForMusicService.likeOrOmitLikeByUser(musicModel);
    }

    @PostMapping(path = "/dislikemusic/{musicId}")
    public ResponseEntity<Object> dislikeMusicByUser(@RequestHeader(value = "accessedThroughGateway") String header, @PathVariable String musicId)
    {
        MusicModel musicModel = new MusicModel();
        musicModel.setMusicId(musicId);
        return this.likeDislikeForMusicService.dislikeOrOmitDislikeByUser(musicModel);
    }

    @PostMapping(path = "/getlikecount/{musicId}")
    public ResponseEntity<Object> getLikeCount(@RequestHeader(value = "accessedThroughGateway") String header, @PathVariable String musicId)
    {
        MusicModel musicModel = new MusicModel();
        musicModel.setMusicId(musicId);
        return this.likeDislikeForMusicService.getLikeCountByUser(musicModel);
    }

    @PostMapping(path = "/getdislikecount/{musicId}")
    public ResponseEntity<Object> getDislikeCountUser(@RequestHeader(value = "accessedThroughGateway") String header, @PathVariable String musicId)
    {
        MusicModel musicModel = new MusicModel();
        musicModel.setMusicId(musicId);
        return this.likeDislikeForMusicService.getDislikeCountByUser(musicModel);
    }

    @PostMapping(path = "/comment")
    public ResponseEntity<Object> commentByUser(@RequestHeader(value = "accessedThroughGateway") String header, @RequestBody MusicModel musicModel )
    {
        return this.commentForMusicService.addCommentByUser(musicModel);
    }

    @PostMapping(path = "/mycomments/{musicId}")
    public ResponseEntity<Object> getCommentsByUser(@RequestHeader(value = "accessedThroughGateway") String header, @PathVariable String musicId)
    {
        MusicModel musicModel = new MusicModel();
        musicModel.setMusicId(musicId);
        return this.commentForMusicService.viewMyCommentsByUser(musicModel);
    }

}
