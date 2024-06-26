package tech.starvingdevelopers.smnbackend.models.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.stereotype.Repository;
import tech.starvingdevelopers.smnbackend.models.entities.Profile;

import java.util.Optional;

@Repository
@EnableRedisRepositories
public interface ProfileRepository extends JpaRepository<Profile, Long> {

    Optional<Profile> findByUsername(String username);
}
