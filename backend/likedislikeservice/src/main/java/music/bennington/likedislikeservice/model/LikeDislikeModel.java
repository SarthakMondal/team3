package music.bennington.likedislikeservice.model;


public class LikeDislikeModel {
    private int id;
    private String musicId;
    private String userId;
    private boolean liked;
    private boolean disliked;
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
	public boolean isLiked() {
		return liked;
	}
	public void setLiked(boolean liked) {
		this.liked = liked;
	}
	public boolean isDisliked() {
		return disliked;
	}
	public void setDisliked(boolean disliked) {
		this.disliked = disliked;
	}
	public LikeDislikeModel(int id, String musicId, String userId, boolean liked, boolean disliked) {
		super();
		this.id = id;
		this.musicId = musicId;
		this.userId = userId;
		this.liked = liked;
		this.disliked = disliked;
	}
	public LikeDislikeModel() {
		
	}
    
    

}
