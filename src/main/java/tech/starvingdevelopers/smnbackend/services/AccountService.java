package tech.starvingdevelopers.smnbackend.services;

import jakarta.transaction.Transactional;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import tech.starvingdevelopers.smnbackend.exceptions.account.AccountAlreadyExistsException;
import tech.starvingdevelopers.smnbackend.exceptions.account.AccountNotFoundByUsernameException;
import tech.starvingdevelopers.smnbackend.exceptions.account.AccountPasswordIncorrectlyException;
import tech.starvingdevelopers.smnbackend.models.dto.account.input.CreateAccountDTO;
import tech.starvingdevelopers.smnbackend.models.dto.account.input.UpdateAccountDTO;
import tech.starvingdevelopers.smnbackend.models.dto.account.input.UpdateAccountPasswordDTO;
import tech.starvingdevelopers.smnbackend.models.dto.account.output.GetAccountDTO;
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
        Optional<Account> accountByUsername = this.accountRepository.findByUsername(createAccountDTO.username());
        if (accountByUsername.isPresent())
            throw new AccountAlreadyExistsException("Account already Exists! (" + accountByUsername.get().getUsername() + ")");

        Optional<Account> accountByEmail = this.accountRepository.findByEmail(createAccountDTO.email());
        if (accountByEmail.isPresent())
            throw new AccountAlreadyExistsException("Account already Exists! (" + accountByEmail.get().getEmail() + ")");

        String encryptedPassword = bCryptPasswordEncoder.encode(createAccountDTO.password());
        return this.accountRepository.save(createAccountDTO.toAccount(encryptedPassword));
    }

    public GetAccountDTO getAccountByUsername(String username) {
        Optional<Account> account = this.accountRepository.findByUsername(username);
        if (account.isEmpty())
            throw new AccountNotFoundByUsernameException("Account Not Found! (" + username + ")");

        return new GetAccountDTO(account.get().getId(), account.get().getUsername(), account.get().getNickname(), account.get().getEmail(), account.get().getGender(), account.get().getBirthdate(), account.get().getCreatedAt());
    }

    public Account updateAccountByUsername(String username, UpdateAccountDTO updateAccountByUsernameDTO) {
        Optional<Account> account = this.accountRepository.findByUsername(username);
        if (account.isEmpty())
            throw new AccountNotFoundByUsernameException("Account Not Found! (" + username + ")");

        if (updateAccountByUsernameDTO.nickname() != null)
            account.get().setNickname(updateAccountByUsernameDTO.nickname());

        if (updateAccountByUsernameDTO.email() != null)
            account.get().setEmail(updateAccountByUsernameDTO.email());

        if (updateAccountByUsernameDTO.gender()!= null)
            account.get().setGender(updateAccountByUsernameDTO.gender());

        return this.accountRepository.save(account.get());
    }

    @Transactional
    public void updatePasswordByUsername(UpdateAccountPasswordDTO updateAccountPasswordDTO) {
        Optional<Account> account = this.accountRepository.findByUsername(updateAccountPasswordDTO.username());
        if (account.isEmpty())
            throw new AccountNotFoundByUsernameException("Account Not Found! (" + updateAccountPasswordDTO.username() + ")");

        if (!this.bCryptPasswordEncoder.matches(updateAccountPasswordDTO.actualPassword(), account.get().getPassword()))
            throw new AccountPasswordIncorrectlyException();

        account.get().setPassword(this.bCryptPasswordEncoder.encode(updateAccountPasswordDTO.newPassword()));
        this.accountRepository.save(account.get());
    }

    @Transactional
    public void deleteAccountByUsername(String username) {
        Optional<Account> account = this.accountRepository.findByUsername(username);
        if (account.isEmpty())
            throw new AccountNotFoundByUsernameException("Account Not Found! (" + username + ")");

        this.accountRepository.deleteAccountByUsername(username);
    }
}
