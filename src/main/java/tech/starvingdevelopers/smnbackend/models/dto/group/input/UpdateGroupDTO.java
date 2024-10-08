package tech.starvingdevelopers.smnbackend.models.dto.group.input;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record UpdateGroupDTO(long id, String customName, String description, String profileImage, String baseColor) {
}
