package music.bennington.likedislikeservice.controller;

import music.bennington.likedislikeservice.entity.CommentEntity;
import music.bennington.likedislikeservice.model.CommentModel;
import music.bennington.likedislikeservice.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/backend/likedislikeservice")
public class CommentController {

    @Autowired
    CommentService commentService;

    @PostMapping(path = "/commentsong")
    public ResponseEntity<Object> addComment(@RequestHeader(value = "accessedByLoggedInUser") String header, @RequestBody CommentModel commentModel) {
    	CommentEntity commentEntity = new CommentEntity();
    	commentEntity.setUserId(commentModel.getUserId());
    	commentEntity.setMusicId(commentModel.getMusicId());
    	commentEntity.setMusicComment(commentModel.getMusicComment());
    	commentEntity.setCommentTime(commentModel.getCommentTime());
    	
    	return this.commentService.addComment(commentEntity);
    }

    @PostMapping(path = "/mycomments")
    public ResponseEntity<Object> viewMyComments(@RequestHeader(value = "accessedByLoggedInUser") String header, @RequestBody CommentModel commentModel) {
        
    	return this.commentService.viewMyComments(commentModel.getMusicId(), commentModel.getUserId());
    }

    @PostMapping(path = "/commentsonmusic")
    public ResponseEntity<Object> viewUsersComments(@RequestHeader(value = "accessedByAdmin") String header, @RequestBody CommentModel commentModel) {
        return this.commentService.viewCommentsOfMusic(commentModel.getMusicId());
    }
}
