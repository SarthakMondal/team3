package music.bennington.likedislikeservice.model;

import java.util.Date;


public class CommentModel {

    private int id;
    private String musicId;
    private String userId;
    private String musicComment;
    private Date commentTime;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMusicId() {
		return musicId;
	}
	public void setMusicId(String musicId) {
		this.musicId = musicId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getMusicComment() {
		return musicComment;
	}
	public void setMusicComment(String musicComment) {
		this.musicComment = musicComment;
	}
	public Date getCommentTime() {
		return commentTime;
	}
	public void setCommentTime(Date commentTime) {
		this.commentTime = commentTime;
	}
	public CommentModel(int id, String musicId, String userId, String musicComment, Date commentTime) {
		super();
		this.id = id;
		this.musicId = musicId;
		this.userId = userId;
		this.musicComment = musicComment;
		this.commentTime = commentTime;
	}
	public CommentModel() {
	
	}
    
    
}
