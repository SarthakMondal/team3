package music.bennington.musicservice.model;

import music.bennington.musicservice.entity.MusicAlbumEntity;


public class MusicModel {
    private String musicId;
    private String musicName;
    private String artistName;
    private String musicUrl;
    private String addedByAdminEmail;
    private MusicAlbumEntity musicAlbum;
	public String getMusicId() {
		return musicId;
	}
	public void setMusicId(String musicId) {
		this.musicId = musicId;
	}
	public String getMusicName() {
		return musicName;
	}
	public void setMusicName(String musicName) {
		this.musicName = musicName;
	}
	public String getArtistName() {
		return artistName;
	}
	public void setArtistName(String artistName) {
		this.artistName = artistName;
	}
	public String getMusicUrl() {
		return musicUrl;
	}
	public void setMusicUrl(String musicUrl) {
		this.musicUrl = musicUrl;
	}
	public String getAddedByAdminEmail() {
		return addedByAdminEmail;
	}
	public void setAddedByAdminEmail(String addedByAdminEmail) {
		this.addedByAdminEmail = addedByAdminEmail;
	}
	public MusicAlbumEntity getMusicAlbum() {
		return musicAlbum;
	}
	public void setMusicAlbum(MusicAlbumEntity musicAlbum) {
		this.musicAlbum = musicAlbum;
	}
	public MusicModel(String musicId, String musicName, String artistName, String musicUrl, String addedByAdminEmail,
			MusicAlbumEntity musicAlbum) {
		super();
		this.musicId = musicId;
		this.musicName = musicName;
		this.artistName = artistName;
		this.musicUrl = musicUrl;
		this.addedByAdminEmail = addedByAdminEmail;
		this.musicAlbum = musicAlbum;
	}
	
    
    

}
