package music.bennington.musicservice.repo;

import music.bennington.musicservice.entity.MusicAlbumEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MusicAlbumRepo extends JpaRepository<MusicAlbumEntity, String> {
    List<MusicAlbumEntity> findByAlbumIdOrAlbumNameOrPublishYear(String albumId, String albumName, int publishYear);
}
