package tech.starvingdevelopers.smnbackend.services;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import tech.starvingdevelopers.smnbackend.exceptions.group.GroupAlreadyExistsException;
import tech.starvingdevelopers.smnbackend.exceptions.group.GroupNotFoundException;
import tech.starvingdevelopers.smnbackend.models.dto.group.input.CreateGroupDTO;
import tech.starvingdevelopers.smnbackend.models.dto.group.input.UpdateGroupDTO;
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

        //TODO: ADICIONAR O DONO DO GRUPO A TABELA DE PARTICIPANTS!

        String searchableName = ConvertNameUtils.formatName(createGroupDTO.name());
        return this.groupRepository.save(createGroupDTO.toGroup(searchableName));
    }

    public Group getGroupByCustomName(String groupName) {
        Optional<Group> customNameGroup = this.groupRepository.findByCustomName(groupName);
        if (customNameGroup.isEmpty())
            throw new GroupNotFoundException("Group not found! (" + groupName + ")");

        return customNameGroup.get();
    }

    public Group getGroupByID(long id) {
        Optional<Group> group = this.groupRepository.findById(id);
        if (group.isEmpty())
            throw new GroupNotFoundException("Group not found! (" + id + ")");

        return group.get();
    }

    @Transactional
    public Group updateGroup(UpdateGroupDTO updateGroupDTO) {
        Optional<Group> group = this.groupRepository.findById(updateGroupDTO.id());
        if (group.isEmpty())
            throw new GroupNotFoundException("Group not found! (" + updateGroupDTO.id() + ")");

        if (updateGroupDTO.customName() != null)
            group.get().setCustomName(updateGroupDTO.customName());

        if (updateGroupDTO.description() != null)
            group.get().setDescription(updateGroupDTO.description());

        if (updateGroupDTO.profileImage() != null)
            group.get().setProfileImage(updateGroupDTO.profileImage());

        if (updateGroupDTO.baseColor() != null)
            group.get().setBaseColor(updateGroupDTO.baseColor());

        return this.groupRepository.save(group.get());
    }

    public void deleteGroup(long id) {
        Optional<Group> group = this.groupRepository.findById(id);
        if (group.isEmpty())
            throw new GroupNotFoundException("Group not found! (" + id + ")");

        this.groupRepository.delete(group.get());
    }
}
