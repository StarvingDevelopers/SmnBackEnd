package tech.starvingdevelopers.smnbackend.models.dto.profile.input;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record UpdateProfileDTO(String username, String nickname, String description, String profileImage) {
}
