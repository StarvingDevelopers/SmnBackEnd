package tech.starvingdevelopers.smnbackend.models.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.starvingdevelopers.smnbackend.models.entities.GroupParticipant;

import java.util.List;
import java.util.Optional;

@Repository
public interface GroupParticipantRepository extends JpaRepository<GroupParticipant, Long> {

    Optional<GroupParticipant> findGroupParticipantByUsernameAndGroupId(String username, Long groupId);

    List<GroupParticipant> findGroupParticipantByUsername(String username);

    void deleteGroupParticipantByUsernameAndGroupID(String username, Long groupId);
}
