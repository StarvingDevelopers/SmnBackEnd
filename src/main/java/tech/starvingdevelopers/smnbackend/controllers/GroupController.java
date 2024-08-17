package tech.starvingdevelopers.smnbackend.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import tech.starvingdevelopers.smnbackend.models.dto.group.input.CreateGroupDTO;
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
        Group group = groupService.createGroup(createGroupDTO);
        return ResponseEntity.ok(GetGroupDTO.fromGroup(group));
    }
}
