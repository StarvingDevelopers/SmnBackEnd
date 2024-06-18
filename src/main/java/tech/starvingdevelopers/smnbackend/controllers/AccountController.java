package tech.starvingdevelopers.smnbackend.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import tech.starvingdevelopers.smnbackend.models.dto.account.CreateAccountDTO;
import tech.starvingdevelopers.smnbackend.models.entities.Account;
import tech.starvingdevelopers.smnbackend.services.AccountService;

@RestController()
@RequestMapping("/account")
public class AccountController {
    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/create")
    public ResponseEntity<Account> createAccount(@RequestBody @Validated CreateAccountDTO createAccountDTO) {
        Account account = accountService.createAccount(createAccountDTO);
        return ResponseEntity.ok(account);
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<Account> getAccount(@PathVariable String username) {
        Account account = this.accountService.getAccountByUsername(username);
        return ResponseEntity.ok(account);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<Account> getAccountByEmail(@PathVariable String email) {
        Account account = this.accountService.getAccountByEmail(email);
        return ResponseEntity.ok(account);
    }
}
