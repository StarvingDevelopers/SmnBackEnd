package tech.starvingdevelopers.smnbackend.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import tech.starvingdevelopers.smnbackend.models.dto.account.input.CreateAccountDTO;
import tech.starvingdevelopers.smnbackend.models.dto.account.input.UpdateAccountDTO;
import tech.starvingdevelopers.smnbackend.models.dto.account.input.UpdateAccountPasswordDTO;
import tech.starvingdevelopers.smnbackend.models.dto.account.output.GetAccountDTO;
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
        Account account = this.accountService.createAccount(createAccountDTO);
        return ResponseEntity.ok(account);
    }

    @GetMapping("/{username}")
    public ResponseEntity<GetAccountDTO> getAccount(@PathVariable String username) {
        GetAccountDTO account = this.accountService.getAccountByUsername(username);
        return ResponseEntity.ok(account);
    }

    @PostMapping("/update/{username}")
    public ResponseEntity<Account> updateAccount(@PathVariable String username, @RequestBody @Validated UpdateAccountDTO updateAccountDTO) {
        Account account = this.accountService.updateAccountByUsername(username, updateAccountDTO);
        return ResponseEntity.ok(account);
    }

    @PostMapping("/password")
    public ResponseEntity<Void> updateAccountPassword(@RequestBody @Validated UpdateAccountPasswordDTO updateAccountPasswordDTO) {
        this.accountService.updatePasswordByUsername(updateAccountPasswordDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete/{username}")
    public ResponseEntity<Void> deleteAccount(@PathVariable String username) {
        this.accountService.deleteAccountByUsername(username);
        return ResponseEntity.ok().build();
    }
}
