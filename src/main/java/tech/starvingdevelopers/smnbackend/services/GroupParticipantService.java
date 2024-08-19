package tech.starvingdevelopers.smnbackend.services;

import org.springframework.stereotype.Service;
import tech.starvingdevelopers.smnbackend.exceptions.group.ParticipantAlreadyExists;
import tech.starvingdevelopers.smnbackend.models.dto.group.input.InsertParticipantDTO;
import tech.starvingdevelopers.smnbackend.models.entities.GroupParticipant;
import tech.starvingdevelopers.smnbackend.models.repositories.GroupParticipantRepository;

import javax.security.auth.login.AccountNotFoundException;
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

    //TODO: ADICIONAR RELAÇÃO
    public GroupParticipant insertParticipant(InsertParticipantDTO insertParticipantDTO) {
        Optional<GroupParticipant> participant = this.groupParticipantRepository.findGroupParticipantByUsernameAndGroupId(insertParticipantDTO.username(), insertParticipantDTO.groupID());
        if (participant.isPresent())
            throw new ParticipantAlreadyExists("Relation of " + insertParticipantDTO.username() + " and " + insertParticipantDTO.groupID() + " already Exists");

        return this.groupParticipantRepository.save(insertParticipantDTO.toGroupParticipant());
    }

    //TODO: LISTAR RELAÇÕES DE X USUÁRIO
    public List<GroupParticipant> getUserParticipants(String username) throws AccountNotFoundException {
        if(accountService.getAccountByUsername(username) == null)
            throw new AccountNotFoundException("Account not found");

        return this.groupParticipantRepository.findGroupParticipantByUsername(username);
    }

    //TODO: REMOVER RELAÇÃO!
    public void deleteParticipant(String username, Long groupID) throws AccountNotFoundException {
        if(accountService.getAccountByUsername(username) == null)
            throw new AccountNotFoundException("Account not found");

        groupParticipantRepository.deleteGroupParticipantByUsernameAndGroupID(username, groupID);
    }
}
