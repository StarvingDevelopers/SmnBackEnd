package tech.starvingdevelopers.smnbackend.models.dto.friend.output;

import tech.starvingdevelopers.smnbackend.models.entities.FriendRequest;

import java.io.Serializable;
import java.util.List;

public record GetPendingDTO(List<FriendRequest> friendRequests) implements Serializable {
}
