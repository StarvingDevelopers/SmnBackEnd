package tech.starvingdevelopers.smnbackend.models.dto.friend.output;

import tech.starvingdevelopers.smnbackend.models.entities.Friend;

import java.io.Serializable;
import java.util.List;

public record GetFriendListDTO(List<Friend> friends) implements Serializable {
}
