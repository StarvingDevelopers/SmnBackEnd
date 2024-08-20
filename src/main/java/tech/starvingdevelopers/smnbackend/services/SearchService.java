package tech.starvingdevelopers.smnbackend.services;

import org.springframework.stereotype.Service;
import tech.starvingdevelopers.smnbackend.models.entities.Group;
import tech.starvingdevelopers.smnbackend.models.entities.Profile;

import java.util.List;

@Service
public class SearchService {
    private final ProfileService profileService;
    private final GroupService groupService;

    public SearchService(ProfileService profileService, GroupService groupService) {
        this.profileService = profileService;
        this.groupService = groupService;
    }

    public List<Profile> searchProfiles(String searchKey) {
        return this.profileService.searchProfile(searchKey);
    }

    public List<Group> searchGroups(String searchKey) {
        return this.groupService.searchGroups(searchKey);
    }
}
