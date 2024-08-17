package tech.starvingdevelopers.smnbackend.models.dto.group.input;

import tech.starvingdevelopers.smnbackend.models.entities.Group;

public record CreateGroupDTO(String name, String description, String color) {

    public Group toGroup(String searchableName) {
        return new Group(name, searchableName, description, color);
    }
}
