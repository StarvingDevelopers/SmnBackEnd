package tech.starvingdevelopers.smnbackend.models.repositories;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.starvingdevelopers.smnbackend.models.entities.Group;

import java.util.List;
import java.util.Optional;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {

    Optional<Group> findByCustomName(String customName);

    @Transactional
    List<Group> findBySearchableNameStartingWith(String searchableName);
}
