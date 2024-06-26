package tech.starvingdevelopers.smnbackend.models.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.stereotype.Repository;
import tech.starvingdevelopers.smnbackend.models.entities.Friend;

import java.util.List;

@Repository
@EnableRedisRepositories
public interface FriendRepository extends JpaRepository<Friend, Long> {

    @Query("SELECT f FROM Friend f WHERE f.leftUsername = :username OR f.rightUsername = :username")
    List<Friend> findFriendsByUsername(String username);
}
