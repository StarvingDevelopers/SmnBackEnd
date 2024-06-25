package tech.starvingdevelopers.smnbackend.models.dto.friend.input;

import tech.starvingdevelopers.smnbackend.models.entities.FriendRequest;

public record FriendRequestDTO(String sender, String receiver) {

    public static FriendRequest toFriendRequest(FriendRequestDTO dto) {
        return new FriendRequest(dto.sender(), dto.receiver());
    }
}
