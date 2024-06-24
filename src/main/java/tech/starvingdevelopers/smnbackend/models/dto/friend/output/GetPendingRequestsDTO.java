package tech.starvingdevelopers.smnbackend.models.dto.friend.output;

import tech.starvingdevelopers.smnbackend.models.entities.FriendRequest;

import java.util.List;

public record GetPendingRequestsDTO(List<FriendRequest> friendRequests) {
}
