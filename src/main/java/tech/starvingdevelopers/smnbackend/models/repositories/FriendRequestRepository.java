package tech.starvingdevelopers.smnbackend.models.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.stereotype.Repository;
import tech.starvingdevelopers.smnbackend.models.entities.FriendRequest;

import java.util.List;
import java.util.Optional;

@Repository
@EnableRedisRepositories
public interface FriendRequestRepository extends JpaRepository<FriendRequest, Long> {

    List<FriendRequest> findFriendRequestsByReceiver(String receiver);

    Optional<FriendRequest> findFriendRequestsBySenderAndReceiver(String sender, String receiver);
}
