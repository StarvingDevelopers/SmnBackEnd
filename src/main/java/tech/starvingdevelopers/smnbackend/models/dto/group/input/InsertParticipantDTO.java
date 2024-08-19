package tech.starvingdevelopers.smnbackend.models.dto.group.input;

import tech.starvingdevelopers.smnbackend.models.entities.GroupParticipant;

public record InsertParticipantDTO(long groupID, String username) {

    public GroupParticipant toGroupParticipant() {
        return new GroupParticipant(this.groupID, this.username);
    }
}
