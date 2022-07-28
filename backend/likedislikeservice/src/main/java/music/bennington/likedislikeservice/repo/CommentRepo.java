package music.bennington.likedislikeservice.repo;

import music.bennington.likedislikeservice.entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepo extends JpaRepository<CommentEntity, Integer> {
    List<CommentEntity> findByMusicIdAndUserId(String musicId, String userId);
    List<CommentEntity> findByMusicId(String musicId);
}
