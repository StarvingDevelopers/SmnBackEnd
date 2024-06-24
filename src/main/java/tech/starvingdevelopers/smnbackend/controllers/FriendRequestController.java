package tech.starvingdevelopers.smnbackend.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import tech.starvingdevelopers.smnbackend.models.dto.friend.input.FriendRequestDTO;
import tech.starvingdevelopers.smnbackend.models.dto.friend.output.GetPendingRequestsDTO;
import tech.starvingdevelopers.smnbackend.models.entities.FriendRequest;
import tech.starvingdevelopers.smnbackend.services.FriendService;

@RestController
@RequestMapping("/friend")
public class FriendRequestController {
    private final FriendService friendService;

    public FriendRequestController(FriendService friendService) {
        this.friendService = friendService;
    }

    @PostMapping("/create")
    public ResponseEntity<FriendRequest> sendFriendRequest(@RequestBody @Validated FriendRequestDTO friendRequestDTO) {
        FriendRequest friendRequest = this.friendService.createFriendRequest(friendRequestDTO);
        return ResponseEntity.ok(friendRequest);
    }

    @GetMapping("/pending-requests")
    public ResponseEntity<GetPendingRequestsDTO> getPendingFriendRequests(String receiver) {
        GetPendingRequestsDTO pendingRequestsDTO = this.friendService.getPendingRequests(receiver);
        return ResponseEntity.ok(pendingRequestsDTO);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteFriendRequest(@PathVariable Long id) {
        this.friendService.deleteFriendRequest(id);
        return ResponseEntity.ok().build();
    }
}
