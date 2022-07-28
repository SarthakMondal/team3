package music.bennington.musicservice.model;


public class LikeDislikeSendModel
{
    private String musicId;
    private String userId;
    private String status;
    private String musicComment;
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMusicComment() {
		return musicComment;
	}
	public void setMusicComment(String musicComment) {
		this.musicComment = musicComment;
	}
    
    
}
