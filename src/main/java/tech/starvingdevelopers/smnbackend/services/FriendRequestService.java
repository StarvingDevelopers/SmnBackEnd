package tech.starvingdevelopers.smnbackend.services;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import tech.starvingdevelopers.smnbackend.exceptions.friends.FriendRequestAlreadyExistsException;
import tech.starvingdevelopers.smnbackend.exceptions.friends.FriendRequestNotFoundException;
import tech.starvingdevelopers.smnbackend.models.dto.friend.input.FriendRequestDTO;
import tech.starvingdevelopers.smnbackend.models.entities.Account;
import tech.starvingdevelopers.smnbackend.models.entities.Friend;
import tech.starvingdevelopers.smnbackend.models.entities.FriendRequest;
import tech.starvingdevelopers.smnbackend.models.repositories.FriendRepository;
import tech.starvingdevelopers.smnbackend.models.repositories.FriendRequestRepository;

import java.util.List;
import java.util.Optional;

@Service
public class FriendRequestService {
    private final FriendRequestRepository friendRequestRepository;
    private final FriendRepository friendRepository;
    private final AccountService accountService;

    public FriendRequestService(FriendRequestRepository friendRequestRepository, FriendRepository friendRepository, AccountService accountService) {
        this.friendRequestRepository = friendRequestRepository;
        this.friendRepository = friendRepository;
        this.accountService = accountService;
    }

    public FriendRequest createFriendRequest(FriendRequestDTO friendRequestDTO) {
        Optional<FriendRequest> friendRequest = this.friendRequestRepository.findFriendRequestsBySenderAndReceiver(friendRequestDTO.sender(), friendRequestDTO.receiver());
        if (friendRequest.isPresent())
            throw new FriendRequestAlreadyExistsException();

        Account senderAccount = accountService.getAccountByUsername(friendRequestDTO.sender());
        Account receiverAccount = accountService.getAccountByUsername(friendRequestDTO.receiver());
        return this.friendRequestRepository.save(new FriendRequest(senderAccount.getUsername(), receiverAccount.getUsername()));
    }

    @Cacheable(value = "pending_friend_requests", key = "#receiver")
    public List<FriendRequest> getPendingRequests(String receiver) {
        return this.friendRequestRepository.findFriendRequestsByReceiver(receiver);
    }

    public void deleteFriendRequest(Long id) {
        Optional<FriendRequest> friendRequest = this.friendRequestRepository.findById(id);
        if (friendRequest.isEmpty())
            throw new FriendRequestNotFoundException("Friend request not found! (" + id + ")");

        this.friendRequestRepository.deleteById(id);
    }

    public void acceptFriendRequest(Long id) {
        Optional<FriendRequest> friendRequest = this.friendRequestRepository.findById(id);
        if (friendRequest.isEmpty())
            throw new FriendRequestNotFoundException("Friend request not found! (" + id + ")");

        Account sender = this.accountService.getAccountByUsername(friendRequest.get().getSender());
        Account receiver = this.accountService.getAccountByUsername(friendRequest.get().getReceiver());

        Friend friend = new Friend(sender, receiver);
        this.friendRepository.save(friend);
        this.friendRequestRepository.deleteById(id);
    }
}
