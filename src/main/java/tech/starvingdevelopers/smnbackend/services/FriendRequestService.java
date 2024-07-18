package tech.starvingdevelopers.smnbackend.services;

import org.springframework.stereotype.Service;
import tech.starvingdevelopers.smnbackend.exceptions.friends.FriendAlreadyExistsException;
import tech.starvingdevelopers.smnbackend.exceptions.friends.FriendRequestAlreadyExistsException;
import tech.starvingdevelopers.smnbackend.exceptions.friends.FriendRequestNotFoundException;
import tech.starvingdevelopers.smnbackend.models.dto.friend.input.FriendRequestDTO;
import tech.starvingdevelopers.smnbackend.models.entities.Account;
import tech.starvingdevelopers.smnbackend.models.entities.Friend;
import tech.starvingdevelopers.smnbackend.models.entities.FriendRequest;
import tech.starvingdevelopers.smnbackend.models.repositories.FriendRequestRepository;

import java.util.List;
import java.util.Optional;

@Service
public class FriendRequestService {
    private final FriendRequestRepository friendRequestRepository;
    private final FriendService friendService;
    private final AccountService accountService;

    public FriendRequestService(FriendRequestRepository friendRequestRepository, FriendService friendService, AccountService accountService) {
        this.friendRequestRepository = friendRequestRepository;
        this.friendService = friendService;
        this.accountService = accountService;
    }

    public FriendRequest createFriendRequest(FriendRequestDTO friendRequestDTO) {
        Optional<FriendRequest> friendRequest = this.friendRequestRepository.findFriendRequestsBySenderAndReceiver(friendRequestDTO.sender(), friendRequestDTO.receiver());
        if (friendRequest.isPresent())
            throw new FriendRequestAlreadyExistsException();

        if (this.friendService.isFriend(friendRequestDTO.sender(), friendRequestDTO.receiver()))
            throw new FriendAlreadyExistsException("Relation between " + friendRequestDTO.sender() + " and " + friendRequestDTO.receiver() + " already exists!");

        Account senderAccount = accountService.getAccountByUsername(friendRequestDTO.sender());
        Account receiverAccount = accountService.getAccountByUsername(friendRequestDTO.receiver());
        return this.friendRequestRepository.save(new FriendRequest(senderAccount.getUsername(), receiverAccount.getUsername()));
    }

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

        Friend friend = new Friend(friendRequest.get().getSender(), friendRequest.get().getReceiver());
        this.friendService.createFriend(friend);
        this.friendRequestRepository.deleteById(id);
    }
}
