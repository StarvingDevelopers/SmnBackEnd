package tech.starvingdevelopers.smnbackend.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.starvingdevelopers.smnbackend.models.dto.friend.output.GetFriendListRequestDTO;
import tech.starvingdevelopers.smnbackend.models.entities.Friend;
import tech.starvingdevelopers.smnbackend.services.FriendService;

import java.util.List;

@RestController
@RequestMapping("friend")
@CrossOrigin
public class FriendController {
    private final FriendService friendService;

    public FriendController(FriendService friendService) {
        this.friendService = friendService;
    }

    @GetMapping("/friend-list/{username}")
    public ResponseEntity<GetFriendListRequestDTO> getFriendListRequests(@PathVariable String username) {
        List<Friend> friends = this.friendService.getFriendList(username);
        return ResponseEntity.ok(new GetFriendListRequestDTO(friends));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteFriend(@PathVariable Long id) {
        this.friendService.deleteFriend(id);
        return ResponseEntity.ok().build();
    }
}
