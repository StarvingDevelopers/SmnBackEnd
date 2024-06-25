package tech.starvingdevelopers.smnbackend.models.dto.friend.output;

import lombok.Getter;
import lombok.Setter;
import tech.starvingdevelopers.smnbackend.models.entities.FriendRequest;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public record GetPendingRequestsDTO(List<FriendRequest> friendRequests) implements Serializable {
}
