package tech.starvingdevelopers.smnbackend.models.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.stereotype.Repository;
import tech.starvingdevelopers.smnbackend.models.entities.Friend;

import java.util.List;
import java.util.Optional;

@Repository
@EnableRedisRepositories
public interface FriendRepository extends JpaRepository<Friend, Long> {

    @Query("SELECT f FROM Friend f WHERE f.leftUsername = :username OR f.rightUsername = :username")
    List<Friend> findFriendsByUsername(String username);

    @Query("SELECT f FROM Friend f WHERE f.leftUsername = :primary AND f.rightUsername = :secondary OR f.leftUsername = :secondary AND f.rightUsername = :primary")
    Optional<Friend> findRelation(String primary, String secondary);
}
