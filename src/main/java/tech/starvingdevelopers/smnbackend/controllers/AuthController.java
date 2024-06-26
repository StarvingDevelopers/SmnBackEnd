package tech.starvingdevelopers.smnbackend.controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import tech.starvingdevelopers.smnbackend.models.dto.account.output.GetAccountDTO;
import tech.starvingdevelopers.smnbackend.models.dto.auth.input.AuthenticateAccountDTO;
import tech.starvingdevelopers.smnbackend.models.entities.Account;
import tech.starvingdevelopers.smnbackend.services.AccountService;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {
    private final AccountService accountService;

    public AuthController(AccountService accountService) {
        this.accountService = accountService;
    }

    /**
     * Rota de Autenticação de Conta.
     * <p>
     *     Este método está mapeando para a solicitação HTTP POST do endpoint "/authenticate".
     *     Validando o objeto {@link AuthenticateAccountDTO} recebido delegado a autenticação
     *     da conta para o link {@link AccountService}. Após a autenticação bem-sucedida da conta, retorna
     *     o objeto {@link Account} correspondente. Dentro de um {@link ResponseEntity} com status 200 OK.
     * </p>
     *
     * @param authenticateAccountDTO DTO contendo detalhes necessários para a autenticação da conta.
     * @return {@link ResponseEntity} contendo {@link Account} requisitada e status 200 OK.
     */
    @PostMapping("/authenticate")
    public ResponseEntity<GetAccountDTO> authenticate(HttpServletRequest request, @RequestBody @Validated AuthenticateAccountDTO authenticateAccountDTO) {
        System.out.println(getClientIp(request));
        Account account = this.accountService.authenticateByUsername(authenticateAccountDTO);
        return ResponseEntity.ok(GetAccountDTO.fromAccount(account));
    }

    private String getClientIp(HttpServletRequest request) {
        String ipAddress = request.getHeader("X-Forwarded-For");
        if (ipAddress == null || ipAddress.isEmpty() || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.isEmpty() || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.isEmpty() || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ipAddress == null || ipAddress.isEmpty() || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ipAddress == null || ipAddress.isEmpty() || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr();
        }
        return ipAddress;
    }
}
