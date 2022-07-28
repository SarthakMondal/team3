package music.bennington.musicservice.controller;

import music.bennington.musicservice.model.LikeDislikeSendModel;
import music.bennington.musicservice.service.MusicLikeDislikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/backend/musicservice")
public class MusicLikeDislikeController {
	private static final String ACCESSEDBYLOGGEDINUSER="accessedByLoggedInUser";
    @Autowired
    MusicLikeDislikeService likeDislikeService;

    @PostMapping(path="/likemusicoromitlikefrommusic")
    public ResponseEntity<Object> likeOrOmitLike(@RequestHeader(value = "accessedByLoggedInUser") String header, @RequestBody LikeDislikeSendModel sendModel){

        HttpHeaders headers = new HttpHeaders();
        headers.add(ACCESSEDBYLOGGEDINUSER, header);
        return this.likeDislikeService.likeOrOmitLike(headers, sendModel);
    }

    @PostMapping(path="/dislikemusicoromitdislikefrommusic")
    public ResponseEntity<Object> dislikeOrOmitDislike(@RequestHeader(value = "accessedByLoggedInUser") String header, @RequestBody LikeDislikeSendModel sendModel){

        HttpHeaders headers = new HttpHeaders();
        headers.add(ACCESSEDBYLOGGEDINUSER, header);
        return this.likeDislikeService.disLikeOrOmitDislike(headers, sendModel);
    }

    @PostMapping(path="/likecountformusic")
    public ResponseEntity<Object> likeCountForMusic(@RequestHeader(value = "accessedByLoggedInUser") String header, @RequestBody LikeDislikeSendModel sendModel){

        HttpHeaders headers = new HttpHeaders();
        headers.add(ACCESSEDBYLOGGEDINUSER, header);
        return this.likeDislikeService.getLikeCount(headers, sendModel);
    }

    @PostMapping(path="/dislikecountformusic")
    public ResponseEntity<Object> dislikeCountForMusic(@RequestHeader(value = "accessedByLoggedInUser") String header, @RequestBody LikeDislikeSendModel sendModel){

        HttpHeaders headers = new HttpHeaders();
        headers.add(ACCESSEDBYLOGGEDINUSER, header);
        return this.likeDislikeService.getDislikeCount(headers, sendModel);
    }

    @PostMapping(path="/likeduserformusic")
    public ResponseEntity<Object> likedUserForUser(@RequestHeader(value = "accessedByAdmin") String header, @RequestBody LikeDislikeSendModel sendModel){

        HttpHeaders headers = new HttpHeaders();
        headers.add(ACCESSEDBYLOGGEDINUSER, header);
        return this.likeDislikeService.getLikedUsers(headers, sendModel);
    }

    @PostMapping(path="/dislikeduserformusic")
    public ResponseEntity<Object> dislikedUserForMusic(@RequestHeader(value = "accessedByAdmin") String header, @RequestBody LikeDislikeSendModel sendModel){

        HttpHeaders headers = new HttpHeaders();
        headers.add(ACCESSEDBYLOGGEDINUSER, header);
        return this.likeDislikeService.getDisLikedUsers(headers, sendModel);
    }
}
