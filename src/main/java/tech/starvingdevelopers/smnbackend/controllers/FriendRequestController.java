package tech.starvingdevelopers.smnbackend.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import tech.starvingdevelopers.smnbackend.models.dto.friend.input.FriendRequestDTO;
import tech.starvingdevelopers.smnbackend.models.dto.friend.output.GetPendingRequestsDTO;
import tech.starvingdevelopers.smnbackend.models.entities.FriendRequest;
import tech.starvingdevelopers.smnbackend.services.FriendRequestService;

import java.util.List;

@RestController
@RequestMapping("/friend-request")
public class FriendRequestController {
    private final FriendRequestService friendService;

    public FriendRequestController(FriendRequestService friendService) {
        this.friendService = friendService;
    }

    /**
     *Rota de Criação de Pedido de Amizade.
     * <p>
     *    Este método esta mapeando para a solicitação HTTP POST no endpoint "/create".
     *    Validando o objeto {@link FriendRequestDTO} recebido delegando a criação do pedido
     *    para o {@link FriendRequestService}. Após a criação bem-sucedida, retorna o objeto
     *    {@link FriendRequest} criado. Dentro de um {@link ResponseEntity} com status 200 OK
     *
     * </p>
     * @param friendRequestDTO DTO com os detalhes necessários para a criação de um pedido de amizade
     * @return {@link ResponseEntity} contendo {@link FriendRequest} criado e status 200 OK
     */
    @PostMapping("/create")
    public ResponseEntity<FriendRequest> sendFriendRequest(@RequestBody @Validated FriendRequestDTO friendRequestDTO) {
        FriendRequest friendRequest = this.friendService.createFriendRequest(friendRequestDTO);
        return ResponseEntity.ok(friendRequest);
    }

    /**
     * Rota de Recuperação de Pedido de Amizade
     * <p>
     *     Este método mapedado para a solicitação HTTP GET no endpoint "/pending-request/{username}".
     *     Busca detalhes de pedidos de amizade da conta associada ao nome de usuário fornecido. Delegando
     *     para o {@link FriendRequestService}. Após a recuperação bem-sucedida, retorna os detalhes da lista
     *     para em um objeto {@link GetPendingRequestsDTO} dentro de um {@link ResponseEntity} com um status 200 OK
     * </p>
     * @param username nome do usuário para a recuperação dos pedidos pendentes
     * @return {@link ResponseEntity} contendo {@link GetPendingRequestsDTO} recuperado e status 200 OK
     */
    @GetMapping("/pending-requests/{username}")
    public ResponseEntity<GetPendingRequestsDTO> getPendingFriendRequests(@PathVariable String username) {
        List<FriendRequest> pendingRequests = this.friendService.getPendingRequests(username);
        return ResponseEntity.ok(new GetPendingRequestsDTO(pendingRequests));
    }

    /**
     * Rota para a Exclusão de um Pedido de Amizade
     * <p>
     *     Esse método mapeado para a solicitação HTTP DELETE no endpoint "/delete/{id}".
     *     Recebe um id de um pedido de amizade como uma variável de caminho e delega a exclusão do pedido
     *     para o {@link FriendRequestService}. Após a exclusão bem-sucedida do pedido, retorna uma resposta
     *     sem conteúdo com status 200 OK.
     * </p>
     * @param id código do pedido para a exclusão.
     * @return {@link ResponseEntity} sem conteúdo com um status 200 OK
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteFriendRequest(@PathVariable Long id) {
        this.friendService.deleteFriendRequest(id);
        return ResponseEntity.ok().build();
    }

    /**
     * Rota para aceitar um pedido de amizade
     * <p>
     *     Este método mapeado para a solicitação HTTP POST do endpoint "/accept/{id}".
     *     Recebe um id de um pedido de amizade como variavel de caminho e delega a adição a lista de amigos
     *     para o {@link FriendRequestService}. Após a adição bem-sucedida, retorna um respota sem conteúdo
     *     com status 200 Ok.
     * </p>
     * @param id código do pedido de amizade.
     * @return {@link ResponseEntity} sem conteúdo com um status 200 OK
     */
    @PostMapping("/accept/{id}")
    public ResponseEntity<Void> acceptFriendRequest(@PathVariable Long id) {
        this.friendService.acceptFriendRequest(id);
        return ResponseEntity.ok().build();
    }
}
