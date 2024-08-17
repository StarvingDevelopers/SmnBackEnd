package tech.starvingdevelopers.smnbackend.models.dto.group.output;

import tech.starvingdevelopers.smnbackend.models.entities.Group;

public record GetGroupDTO(long id, String customName, String searchableName, String description, String profileImage, String baseColor) {

    public static GetGroupDTO fromGroup(Group group) {
        return new GetGroupDTO(group.getId(), group.getCustomName(), group.getSearchableName(), group.getDescription(), group.getProfileImage(), group.getBaseColor());
    }
}
