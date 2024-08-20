package tech.starvingdevelopers.smnbackend.models.dto.search.output;

import tech.starvingdevelopers.smnbackend.models.entities.Group;
import tech.starvingdevelopers.smnbackend.models.entities.Profile;

import java.util.List;

public record SearchResultDTO(List<Group> groups, List<Profile> users) {
}
