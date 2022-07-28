package music.bennington.musicservice.model;

import java.util.List;


import music.bennington.musicservice.entity.MusicEntity;

public class AlbumModel {
    private String albumId;
    private String albumName;
    private int publishYear;
    private String albumImageUrl;
    private List<MusicEntity> albumMusics;
	public String getAlbumId() {
		return albumId;
	}
	public void setAlbumId(String albumId) {
		this.albumId = albumId;
	}
	public String getAlbumName() {
		return albumName;
	}
	public void setAlbumName(String albumName) {
		this.albumName = albumName;
	}
	public int getPublishYear() {
		return publishYear;
	}
	public void setPublishYear(int publishYear) {
		this.publishYear = publishYear;
	}
	public String getAlbumImageUrl() {
		return albumImageUrl;
	}
	public void setAlbumImageUrl(String albumImageUrl) {
		this.albumImageUrl = albumImageUrl;
	}
	public List<MusicEntity> getAlbumMusics() {
		return albumMusics;
	}
	public void setAlbumMusics(List<MusicEntity> albumMusics) {
		this.albumMusics = albumMusics;
	}
	public AlbumModel(String albumId, String albumName, int publishYear, String albumImageUrl,
			List<MusicEntity> albumMusics) {
		super();
		this.albumId = albumId;
		this.albumName = albumName;
		this.publishYear = publishYear;
		this.albumImageUrl = albumImageUrl;
		this.albumMusics = albumMusics;
	}
	public AlbumModel() {}

    
}
