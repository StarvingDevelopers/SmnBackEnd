package tech.starvingdevelopers.smnbackend.services;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import tech.starvingdevelopers.smnbackend.exceptions.account.AccountNotFoundByUsernameException;
import tech.starvingdevelopers.smnbackend.exceptions.friends.FriendRequestAlreadyExistsException;
import tech.starvingdevelopers.smnbackend.exceptions.friends.FriendRequestNotFoundException;
import tech.starvingdevelopers.smnbackend.models.dto.account.output.GetAccountDTO;
import tech.starvingdevelopers.smnbackend.models.dto.friend.input.FriendRequestDTO;
import tech.starvingdevelopers.smnbackend.models.dto.friend.output.GetPendingRequestsDTO;
import tech.starvingdevelopers.smnbackend.models.entities.Account;
import tech.starvingdevelopers.smnbackend.models.entities.FriendRequest;
import tech.starvingdevelopers.smnbackend.models.repositories.AccountRepository;
import tech.starvingdevelopers.smnbackend.models.repositories.FriendRequestRepository;

import java.util.Optional;

@Service
public class FriendService {
    private final FriendRequestRepository friendRequestRepository;
    private final AccountRepository accountRepository;

    public FriendService(FriendRequestRepository friendRequestRepository, AccountRepository accountRepository) {
        this.friendRequestRepository = friendRequestRepository;
        this.accountRepository = accountRepository;
    }

    public FriendRequest createFriendRequest(FriendRequestDTO friendRequestDTO) {
        Optional<FriendRequest> friendRequest = this.friendRequestRepository.findFriendRequestsBySenderAndReceiver(friendRequestDTO.sender(), friendRequestDTO.receiver());
        if (friendRequest.isPresent())
            throw new FriendRequestAlreadyExistsException();

        Optional<Account> senderAccount = this.accountRepository.findByUsername(friendRequestDTO.sender());
        if (senderAccount.isEmpty())
            throw new AccountNotFoundByUsernameException("Account Not Found! (" + friendRequestDTO.sender() + ")");

        Optional<Account> receiverAccount = this.accountRepository.findByUsername(friendRequestDTO.receiver());
        if (receiverAccount.isEmpty())
            throw new AccountNotFoundByUsernameException("Account Not Found! (" + friendRequestDTO.receiver() + ")");

        return this.friendRequestRepository.save(FriendRequestDTO.fromDTO(friendRequestDTO));
    }

    @Cacheable(value = "pending_friend_requests", key = "#receiver")
    public GetPendingRequestsDTO getPendingRequests(String receiver) {
        return new GetPendingRequestsDTO(this.friendRequestRepository.findFriendRequestsByReceiver(receiver));
    }

    public void deleteFriendRequest(Long id) {
        Optional<FriendRequest> friendRequest = this.friendRequestRepository.findById(id);
        if (friendRequest.isEmpty())
            throw new FriendRequestNotFoundException("Friend request not found! (" + id + ")");

        this.friendRequestRepository.deleteById(id);
    }
}
