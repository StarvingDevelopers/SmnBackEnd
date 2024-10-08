package tech.starvingdevelopers.smnbackend.models.repositories;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.stereotype.Repository;
import tech.starvingdevelopers.smnbackend.models.entities.Profile;

import java.util.List;
import java.util.Optional;

@Repository
@EnableRedisRepositories
public interface ProfileRepository extends JpaRepository<Profile, Long> {

    @Transactional
    Optional<Profile> findByUsername(String username);

    @Transactional
    List<Profile> findBySearchableNameStartingWith(String searchableName);
}
