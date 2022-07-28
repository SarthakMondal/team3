package music.bennington.musicservice.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;


import javax.persistence.*;

@Entity
@Table(name="music_info")

public class MusicEntity {
    @Id
    @Column(name = "music_id", nullable = false)
    private String musicId;

    @Column(name = "music_name", nullable = false, unique = true)
    private String musicName;

    @Column(name = "artist_name")
    private String artistName;

    @Column(name = "music_url", nullable = false)
    private String musicUrl;

    @Column(name = "admin_id", nullable = false)
    private String addedByAdminEmail;

    @ManyToOne
    @JoinColumn(name = "music_album_id")
    @JsonIgnore
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

	public MusicEntity(String musicId, String musicName, String artistName, String musicUrl, String addedByAdminEmail,
			MusicAlbumEntity musicAlbum) {
		super();
		this.musicId = musicId;
		this.musicName = musicName;
		this.artistName = artistName;
		this.musicUrl = musicUrl;
		this.addedByAdminEmail = addedByAdminEmail;
		this.musicAlbum = musicAlbum;
	}

	public MusicEntity() {
	
	}
    
    

}
