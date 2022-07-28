package music.bennington.userservice.repo;

import music.bennington.userservice.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<UserEntity, String>
{
    Optional<UserEntity> findByEmailId(String emailId);
}
