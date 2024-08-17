package tech.starvingdevelopers.smnbackend.services;

import org.springframework.stereotype.Service;
import tech.starvingdevelopers.smnbackend.exceptions.group.GroupAlreadyExistsException;
import tech.starvingdevelopers.smnbackend.models.dto.group.input.CreateGroupDTO;
import tech.starvingdevelopers.smnbackend.models.entities.Group;
import tech.starvingdevelopers.smnbackend.models.repositories.GroupRepository;
import tech.starvingdevelopers.smnbackend.utils.ConvertNameUtils;

import java.util.Optional;

@Service
public class GroupService {
    private final GroupRepository groupRepository;

    public GroupService(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    public Group createGroup(CreateGroupDTO createGroupDTO) {
        Optional<Group> customNameGroup = this.groupRepository.findByCustomName(createGroupDTO.name());
        if (customNameGroup.isPresent())
            throw new GroupAlreadyExistsException("Group already exists! (" + customNameGroup.get().getCustomName() + ")");

        String searchableName = ConvertNameUtils.formatName(createGroupDTO.name());
        return this.groupRepository.save(createGroupDTO.toGroup(searchableName));
    }
}
