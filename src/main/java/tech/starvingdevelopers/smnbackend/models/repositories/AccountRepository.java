package tech.starvingdevelopers.smnbackend.models.repositories;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import tech.starvingdevelopers.smnbackend.models.entities.Account;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {

    Optional<Account> findByUsername(String username);

    Optional<Account> findByEmail(String email);

    @Transactional
    void deleteAccountByUsername(String username);
}
