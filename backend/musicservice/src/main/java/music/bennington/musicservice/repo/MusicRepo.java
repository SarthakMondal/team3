package music.bennington.musicservice.repo;

import music.bennington.musicservice.entity.MusicEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MusicRepo extends JpaRepository<MusicEntity, String> {
    List<MusicEntity> findByMusicIdOrMusicNameOrArtistName(String musicId, String musicName, String artistName);
}
