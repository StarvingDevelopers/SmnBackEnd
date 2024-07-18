package tech.starvingdevelopers.smnbackend.models.dto.friend.output;

import tech.starvingdevelopers.smnbackend.models.entities.Profile;

import java.io.Serializable;
import java.util.List;

public record GetFriendListWithDetailsDTO(List<Profile> friendsProfile) implements Serializable {
}
