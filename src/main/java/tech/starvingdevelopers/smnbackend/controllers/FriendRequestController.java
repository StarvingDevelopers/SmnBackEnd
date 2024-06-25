package tech.starvingdevelopers.smnbackend.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import tech.starvingdevelopers.smnbackend.models.dto.friend.input.FriendRequestDTO;
import tech.starvingdevelopers.smnbackend.models.dto.friend.output.GetPendingRequestsDTO;
import tech.starvingdevelopers.smnbackend.models.entities.FriendRequest;
import tech.starvingdevelopers.smnbackend.services.FriendRequestService;

import java.util.List;

@RestController
@RequestMapping("/friend-request")
public class FriendRequestController {
    private final FriendRequestService friendService;

    public FriendRequestController(FriendRequestService friendService) {
        this.friendService = friendService;
    }

    @PostMapping("/create")
    public ResponseEntity<FriendRequest> sendFriendRequest(@RequestBody @Validated FriendRequestDTO friendRequestDTO) {
        FriendRequest friendRequest = this.friendService.createFriendRequest(friendRequestDTO);
        return ResponseEntity.ok(friendRequest);
    }

    @GetMapping("/pending-requests/{username}")
    public ResponseEntity<GetPendingRequestsDTO> getPendingFriendRequests(@PathVariable String username) {
        List<FriendRequest> pendingRequests = this.friendService.getPendingRequests(username);
        return ResponseEntity.ok(new GetPendingRequestsDTO(pendingRequests));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteFriendRequest(@PathVariable Long id) {
        this.friendService.deleteFriendRequest(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/accept/{id}")
    public ResponseEntity<Void> acceptFriendRequest(@PathVariable Long id) {
        this.friendService.acceptFriendRequest(id);
        return ResponseEntity.ok().build();
    }
}
