package music.bennington.likedislikeservice.repo;
import java.util.List;

import music.bennington.likedislikeservice.entity.LikeDislikeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface LikeDislikeRepo extends JpaRepository<LikeDislikeEntity, Integer> {

    @Query(value = "UPDATE `like_dislike_info` SET `liked`= :status WHERE `music_id`= :musicId AND `user_id`= :userId", nativeQuery = true)
    @Modifying
    @Transactional
    public void changeLikeStatus(String musicId, String userId, boolean status);

    @Query(value = "UPDATE `like_dislike_info` SET `disliked`= :status WHERE `music_id`= :musicId AND `user_id`= :userId", nativeQuery = true)
    @Modifying
    @Transactional
    public void changeDisLikeStatus(String musicId, String userId, boolean status);

    @Query(value = "SELECT * FROM `like_dislike_info` WHERE `music_id`= :musicId AND `user_id`= :userId", nativeQuery = true)
    public LikeDislikeEntity likeDislikeStatus(String musicId, String userId);

    @Query(value = "SELECT COUNT(*) FROM  `like_dislike_info` WHERE `music_id`= :musicId AND `liked`=true", nativeQuery = true)
    public int likeCount(String musicId);

    @Query(value = "SELECT COUNT(*) FROM `like_dislike_info` WHERE `music_id`= :musicId AND `disliked`=true", nativeQuery = true)
    public int disLikeCount(String musicId);

    @Query(value = "SELECT `user_id` FROM `like_dislike_info` WHERE `music_id`= :musicId AND `liked`=true", nativeQuery = true)
    public List<String> showLikedUsers(String musicId);

    @Query(value = "SELECT `user_id` FROM `like_dislike_info` WHERE `music_id`= :musicId AND `disliked`=true", nativeQuery = true)
    public List<String> showDisLikedUsers(String musicId);



}
