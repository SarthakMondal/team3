package music.bennington.likedislikeservice.entity;



import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="comment_info")
public class CommentEntity {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private int id;

    @Column(name = "music_id")
    private String musicId;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "music_comment")
    private String musicComment;

    @Column(name = "comment_time")
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

	public CommentEntity(int id, String musicId, String userId, String musicComment, Date commentTime) {
		super();
		this.id = id;
		this.musicId = musicId;
		this.userId = userId;
		this.musicComment = musicComment;
		this.commentTime = commentTime;
	}

	public CommentEntity() {
	
	}
    
    
}
