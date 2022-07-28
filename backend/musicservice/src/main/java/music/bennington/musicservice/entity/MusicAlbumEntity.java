package music.bennington.musicservice.entity;

import java.util.List;

import javax.persistence.*;


@Entity
@Table(name = "music_album_info")

public class MusicAlbumEntity {
    @Id
    @Column(name = "music_album_id", nullable = false)
    private String albumId;

    @Column(name = "album_name", nullable = false, unique = true)
    private String albumName;

    @Column(name = "publish_year")
    private int publishYear;

    @Column(name = "album_img")
    private String albumImageUrl;

    @OneToMany(mappedBy = "musicAlbum", cascade = CascadeType.ALL, orphanRemoval = true,fetch=FetchType.EAGER)
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

	public MusicAlbumEntity(String albumId, String albumName, int publishYear, String albumImageUrl,
			List<MusicEntity> albumMusics) {
		super();
		this.albumId = albumId;
		this.albumName = albumName;
		this.publishYear = publishYear;
		this.albumImageUrl = albumImageUrl;
		this.albumMusics = albumMusics;
	}

	public MusicAlbumEntity() {
		
	}
    
    
    
}
