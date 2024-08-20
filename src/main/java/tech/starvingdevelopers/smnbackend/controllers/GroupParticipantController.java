package tech.starvingdevelopers.smnbackend.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.starvingdevelopers.smnbackend.models.dto.group.input.CreateParticipantDTO;
import tech.starvingdevelopers.smnbackend.models.dto.group.output.GetAllParticipantDTO;
import tech.starvingdevelopers.smnbackend.models.dto.group.output.GetGroupParticipantDTO;
import tech.starvingdevelopers.smnbackend.models.entities.GroupParticipant;
import tech.starvingdevelopers.smnbackend.services.GroupParticipantService;

import java.util.List;

@RestController
@RequestMapping("/relations")
@CrossOrigin
public class GroupParticipantController {
    private final GroupParticipantService groupParticipantService;

    public GroupParticipantController(GroupParticipantService groupParticipantService) {
        this.groupParticipantService = groupParticipantService;
    }

    @PostMapping("/create")
    public ResponseEntity<GetGroupParticipantDTO> createParticipant(CreateParticipantDTO createParticipantDTO) {
        GroupParticipant groupParticipant = this.groupParticipantService.insertParticipant(createParticipantDTO);
        return ResponseEntity.ok(new GetGroupParticipantDTO(groupParticipant.getId(), groupParticipant.getGroupID(), groupParticipant.getUsername()));
    }

    @GetMapping("/{username}")
    public ResponseEntity<GetAllParticipantDTO> getAllParticipant(@PathVariable String username) {
        List<GroupParticipant> allParticipants = this.groupParticipantService.getUserParticipants(username);
        return ResponseEntity.ok(new GetAllParticipantDTO(allParticipants));
    }
}
