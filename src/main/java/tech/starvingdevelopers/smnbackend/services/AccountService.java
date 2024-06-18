package tech.starvingdevelopers.smnbackend.services;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import tech.starvingdevelopers.smnbackend.exceptions.account.AccountAlreadyExistsException;
import tech.starvingdevelopers.smnbackend.exceptions.account.AccountNotFoundByEmailException;
import tech.starvingdevelopers.smnbackend.exceptions.account.AccountNotFoundByUsernameException;
import tech.starvingdevelopers.smnbackend.models.dto.account.CreateAccountDTO;
import tech.starvingdevelopers.smnbackend.models.entities.Account;
import tech.starvingdevelopers.smnbackend.models.repositories.AccountRepository;

import java.util.Optional;

@Service
public class AccountService {
    private final AccountRepository accountRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public AccountService(AccountRepository accountRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.accountRepository = accountRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public Account createAccount(CreateAccountDTO createAccountDTO) {
        Optional<Account> account = this.accountRepository.findByUsername(createAccountDTO.username());
        if (account.isPresent()) {
            throw new AccountAlreadyExistsException("Account already Exists! (" + account.get().getUsername() + ")");
        }

        String encryptedPassword = bCryptPasswordEncoder.encode(createAccountDTO.password());
        return this.accountRepository.save(createAccountDTO.toAccount(encryptedPassword));
    }

    public Account getAccountByUsername(String username) {
        Optional<Account> account = this.accountRepository.findByUsername(username);
        if (account.isEmpty()) {
            throw new AccountNotFoundByUsernameException("Account Not Found! (" + username + ")");
        }

        return account.get();
    }

    public Account getAccountByEmail(String email) {
        Optional<Account> account = this.accountRepository.findByEmail(email);
        if (account.isEmpty()) {
            throw new AccountNotFoundByEmailException("Account Not Found! (" + email + ")");
        }

        return account.get();
    }

    public boolean deleteAccountByUsername(String username) {
        Optional<Account> account = this.accountRepository.findByUsername(username);
        if (account.isEmpty()) {
            throw new AccountNotFoundByUsernameException("Account Not Found! (" + username + ")");
        }

        return this.accountRepository.deleteAccountByUsername(username);
    }

    public boolean deleteAccountByEmail(String email) {
        Optional<Account> account = this.accountRepository.findByEmail(email);
        if (account.isEmpty()) {
            throw new AccountNotFoundByEmailException("Account Not Found! (" + email + ")");
        }

        return this.accountRepository.deleteAccountByEmail(email);
    }
}
