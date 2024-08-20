package tech.starvingdevelopers.smnbackend.services;

import org.springframework.stereotype.Service;
import tech.starvingdevelopers.smnbackend.exceptions.account.AccountNotFoundByUsernameException;
import tech.starvingdevelopers.smnbackend.exceptions.group.ParticipantAlreadyExists;
import tech.starvingdevelopers.smnbackend.models.dto.group.input.CreateParticipantDTO;
import tech.starvingdevelopers.smnbackend.models.entities.GroupParticipant;
import tech.starvingdevelopers.smnbackend.models.repositories.GroupParticipantRepository;

import java.util.List;
import java.util.Optional;

@Service
public class GroupParticipantService {
    private final GroupParticipantRepository groupParticipantRepository;
    private final AccountService accountService;

    public GroupParticipantService(GroupParticipantRepository groupParticipantRepository, AccountService accountService) {
        this.groupParticipantRepository = groupParticipantRepository;
        this.accountService = accountService;
    }

    public GroupParticipant insertParticipant(CreateParticipantDTO insertParticipantDTO) {
        Optional<GroupParticipant> participant = this.groupParticipantRepository.findGroupParticipantByUsernameAndGroupId(insertParticipantDTO.username(), insertParticipantDTO.groupID());
        if (participant.isPresent())
            throw new ParticipantAlreadyExists("Relation of " + insertParticipantDTO.username() + " and " + insertParticipantDTO.groupID() + " already Exists");

        return this.groupParticipantRepository.save(insertParticipantDTO.toGroupParticipant());
    }

    public List<GroupParticipant> getUserParticipants(String username) {
        if(accountService.getAccountByUsername(username) == null)
            throw new AccountNotFoundByUsernameException("Account not found");

        return this.groupParticipantRepository.findGroupParticipantByUsername(username);
    }

    public void deleteParticipant(String username, long groupID) {
        if(accountService.getAccountByUsername(username) == null)
            throw new AccountNotFoundByUsernameException("Account not found");

        groupParticipantRepository.deleteGroupParticipantByUsernameAndGroupID(username, groupID);
    }
}
