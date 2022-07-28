package music.bennington.musicservice.controller;

import music.bennington.musicservice.service.MusicCommentService;
import music.bennington.musicservice.model.LikeDislikeSendModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/backend/musicservice")
public class MusicCommentController {

    @Autowired
    MusicCommentService commentService;

    @PostMapping(path="/commentonmusic")
    public ResponseEntity<Object> addCommentToMusic(@RequestHeader(value = "accessedByLoggedInUser") String header, @RequestBody LikeDislikeSendModel sendModel){

        HttpHeaders headers = new HttpHeaders();
        headers.add("accessedByLoggedInUser", header);
        return this.commentService.addCommentToMusic(headers, sendModel);
    }

    @PostMapping(path="/viewcommentsonmusic")
    public ResponseEntity<Object> viewAllCommentsOnMusic(@RequestHeader(value = "accessedByLoggedInUser") String header, @RequestBody LikeDislikeSendModel sendModel){

        HttpHeaders headers = new HttpHeaders();
        headers.add("accessedByLoggedInUser", header);
        return this.commentService.viewUsersCommentsOnMusic(headers, sendModel);
    }

    @PostMapping(path="/viewalluserscommnetonmusic")
    public ResponseEntity<Object> viewUsersCommentsOnMusic(@RequestHeader(value = "accessedByAdmin") String header, @RequestBody LikeDislikeSendModel sendModel){

        HttpHeaders headers = new HttpHeaders();
        headers.add("accessedByAdmin", header);
        return this.commentService.viewAllCommentsOnMusic(headers, sendModel);
    }
}
