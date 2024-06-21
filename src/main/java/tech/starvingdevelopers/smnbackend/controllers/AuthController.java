package tech.starvingdevelopers.smnbackend.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.starvingdevelopers.smnbackend.models.dto.account.output.GetAccountDTO;
import tech.starvingdevelopers.smnbackend.models.dto.auth.input.AuthenticateAccountDTO;
import tech.starvingdevelopers.smnbackend.services.AccountService;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AccountService accountService;

    public AuthController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/authenticate")
    public ResponseEntity<GetAccountDTO> authenticate(@RequestBody @Validated AuthenticateAccountDTO authenticateAccountDTO) {
        GetAccountDTO account = this.accountService.authenticateByUsername(authenticateAccountDTO);
        return ResponseEntity.ok(account);
    }
}
