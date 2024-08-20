package tech.starvingdevelopers.smnbackend.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import tech.starvingdevelopers.smnbackend.models.dto.group.input.CreateGroupDTO;
import tech.starvingdevelopers.smnbackend.models.dto.group.input.UpdateGroupDTO;
import tech.starvingdevelopers.smnbackend.models.dto.group.output.GetGroupDTO;
import tech.starvingdevelopers.smnbackend.models.entities.Group;
import tech.starvingdevelopers.smnbackend.services.GroupService;

@RestController
@RequestMapping("/group")
@CrossOrigin
public class GroupController {
    private final GroupService groupService;

    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @PostMapping("/create")
    public ResponseEntity<GetGroupDTO> createGroup(@RequestBody @Validated CreateGroupDTO createGroupDTO) {
        Group group = this.groupService.createGroup(createGroupDTO);
        return ResponseEntity.ok(GetGroupDTO.fromGroup(group));
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<GetGroupDTO> getGroupById(@PathVariable Long id) {
        Group group = this.groupService.getGroupByID(id);
        return ResponseEntity.ok(GetGroupDTO.fromGroup(group));
    }

    @GetMapping("/custom/{groupName}")
    public ResponseEntity<GetGroupDTO> getGroup(@PathVariable String groupName) {
        Group group = this.groupService.getGroupByCustomName(groupName);
        return ResponseEntity.ok(GetGroupDTO.fromGroup(group));
    }

    @PostMapping("/update")
    public ResponseEntity<GetGroupDTO> updateGroup(@RequestBody @Validated UpdateGroupDTO updateGroupDTO) {
        Group group = groupService.updateGroup(updateGroupDTO);
        return ResponseEntity.ok(GetGroupDTO.fromGroup(group));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteGroup(@PathVariable long id) {
        this.groupService.deleteGroup(id);
        return ResponseEntity.ok().build();
    }
}
