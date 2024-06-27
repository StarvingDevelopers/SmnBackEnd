package tech.starvingdevelopers.smnbackend.services;

import org.springframework.stereotype.Service;
import tech.starvingdevelopers.smnbackend.exceptions.friends.FriendNotFoundException;
import tech.starvingdevelopers.smnbackend.models.entities.Friend;
import tech.starvingdevelopers.smnbackend.models.repositories.FriendRepository;

import java.util.List;
import java.util.Optional;

@Service
public class FriendService {
    private final FriendRepository friendRepository;

    public FriendService(FriendRepository friendRepository) {
        this.friendRepository = friendRepository;
    }

    public void createFriend(Friend friend) {
        this.friendRepository.save(friend);
    }

    public boolean isFriend(String leftUsername, String rightUsername) {
        Optional<Friend> relation = this.friendRepository.findRelation(leftUsername, rightUsername);
        return relation.isPresent();
    }

    public List<Friend> getFriendList(String username) {
        return this.friendRepository.findFriendsByUsername(username);
    }

    public void deleteFriend(Long id) {
        Optional<Friend> friend = this.friendRepository.findById(id);
        if (friend.isPresent())
            throw new FriendNotFoundException("Friend not found! (" + id + ")");
    }
}
