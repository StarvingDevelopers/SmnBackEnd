package tech.starvingdevelopers.smnbackend.models.dto.group.output;

import tech.starvingdevelopers.smnbackend.models.entities.GroupParticipant;

import java.util.List;

public record GetAllParticipantDTO(List<GroupParticipant> groups) {
}
