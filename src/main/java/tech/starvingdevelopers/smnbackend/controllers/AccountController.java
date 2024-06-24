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

    /**
     * Rota de Criação Conta.
     * <p>
     *     Este método está mapeando para a solicitação HTTP POST no endpoint "/create".
     *     Validando o objeto {@link CreateAccountDTO} recebido delegando a criação da conta
     *     para o {@link AccountService}. Após a criação bem-sucedida, retorna o objeto {@link Account} criado.
     *     Dentro de um {@link ResponseEntity} com status 200 OK.
     * </p>
     *
     * @param createAccountDTO DTO contendo detalhes necessários para criação de uma nova conta.
     * @return {@link ResponseEntity} contendo {@link Account} criado e status 200 OK.
     */
    @PostMapping("/create")
    public ResponseEntity<GetAccountDTO> createAccount(@RequestBody @Validated CreateAccountDTO createAccountDTO) {
        Account account = this.accountService.createAccount(createAccountDTO);
        return ResponseEntity.ok(new GetAccountDTO(account));
    }

    /**
     * Rota de Recuperação de Detalhes.
     * <p>
     *     Este método mapeado para a solicitação HTTP GET no endpoint "/{username}".
     *     Busca detalhes da conta associada ao nome de usuário fornecido. delegando a tarefa
     *     para o {@link AccountService}. Após a recuperação bem-sucedida, retorna os detalhes
     *     da conta em um objeto {@link GetAccountDTO} dentro de um {@link ResponseEntity} com um status 200 OK.
     * </p>
     *
     * @param username nome do usuário para recuperação de detalhes.
     * @return {@link ResponseEntity} contendo {@link GetAccountDTO} recuperado e status 200 OK.
     */
    @GetMapping("/{username}")
    public ResponseEntity<GetAccountDTO> getAccount(@PathVariable String username) {
        GetAccountDTO account = this.accountService.getAccountByUsername(username);
        return ResponseEntity.ok(account);
    }

    /**
     * Rota de Atualização de Informações.
     * <p>
     *     Este método mapeado para a solicitação HTTP POST no endpoint "/update".
     *     Validando o objeto {@link UpdateAccountDTO} recebido delegando a criação da conta
     *     para o {@link AccountService}. Após a atualização bem-sucedida, retorna o objeto {@link Account} atualizado.
     *     Dentro de um {@link ResponseEntity} com o status 200 OK.
     * </p>
     *
     * @param updateAccountDTO DTO contendo detalhes necessários para atualização da conta.
     * @return {@link ResponseEntity} contendo {@link Account} atualizado e status 200 OK.
     */
    @PostMapping("/update")
    public ResponseEntity<GetAccountDTO> updateAccount(@RequestBody @Validated UpdateAccountDTO updateAccountDTO) {
        Account account = this.accountService.updateAccountByUsername(updateAccountDTO);
        return ResponseEntity.ok(new GetAccountDTO(account));
    }

    /**
     * Rota de Atualização de Senha.
     * <p>
     *     Esse método mapeado para a solicitação HTTP POST no endpoint "/password".
     *     Valida o objeto {@link UpdateAccountPasswordDTO} recebido delegando a atualização de senha
     *     para o {@link AccountService}. Após a atualização bem-sucedida da senha,
     *     retorna uma resposta sem conteúdo com um status 200 OK.
     * </p>
     *
     * @param updateAccountPasswordDTO DTO contendo detalhes necessários para atualização da senha.
     * @return {@link ResponseEntity} sem conteúdo com um status 200 OK.
     */
    @PostMapping("/password")
    public ResponseEntity<Void> updateAccountPassword(@RequestBody @Validated UpdateAccountPasswordDTO updateAccountPasswordDTO) {
        this.accountService.updatePasswordByUsername(updateAccountPasswordDTO);
        return ResponseEntity.ok().build();
    }

    /**
     * Rota de Exclusão de Conta.
     * <p>
     *     Esse método mapeado para a solicitação HTTP DELETE no endpoint "/delete/{username}".
     *     Recebe o nome de usuário como uma variável de caminho e delega a exclusão da conta
     *     para o {@link AccountService}. Após a exclusão bem-sucedida da conta,
     *     retorna uma resposta sem conteúdo com um status 200 OK.
     * </p>
     *
     * @param username nome do usuário para exclusão da conta.
     * @return {@link ResponseEntity} sem conteúdo com um status 200 OK.
     */
    @DeleteMapping("/delete/{username}")
    public ResponseEntity<Void> deleteAccount(@PathVariable String username) {
        this.accountService.deleteAccountByUsername(username);
        return ResponseEntity.ok().build();
    }
}
