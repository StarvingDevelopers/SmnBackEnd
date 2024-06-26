package tech.starvingdevelopers.smnbackend.models.dto.profile.output;

import tech.starvingdevelopers.smnbackend.models.entities.Profile;

public record GetProfileDTO(long id, String username, String nickname, String description, String profileImage) {

    public static GetProfileDTO toGetProfileDTO(Profile profile) {
        return new GetProfileDTO(profile.getId(), profile.getUsername(), profile.getNickname(), profile.getDescription(), profile.getProfileImage());
    }
}
