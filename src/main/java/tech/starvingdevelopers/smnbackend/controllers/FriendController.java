package tech.starvingdevelopers.smnbackend.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.starvingdevelopers.smnbackend.models.dto.friend.output.GetFriendListDTO;
import tech.starvingdevelopers.smnbackend.models.dto.friend.output.GetFriendListWithDetailsDTO;
import tech.starvingdevelopers.smnbackend.models.entities.Account;
import tech.starvingdevelopers.smnbackend.models.entities.Friend;
import tech.starvingdevelopers.smnbackend.models.entities.Profile;
import tech.starvingdevelopers.smnbackend.services.FriendService;

import java.util.List;

@RestController
@RequestMapping("friend")
@CrossOrigin
public class FriendController {
    private final FriendService friendService;

    public FriendController(FriendService friendService) {
        this.friendService = friendService;
    }

    /**
     * Rota de Recuperação de Lista de Amigos.
     * <p>
     *     Este método está mapeando para a solicitação HTTP GET no endpoint "/friend-list/{username}".
     *     Busca informações da lista de amigos de um usuário. Delegando a tarefa para
     *     o {@link FriendService}. Após a recuperação bem sucedida, retorna as informações
     *     da lista em um objeto {@link GetFriendListDTO} dentro de um {@link ResponseEntity} com status 200 OK
     * </p>
     * @param username nome do usuário para a recuperação da lista
     * @return {@link ResponseEntity} contendo {@link GetFriendListDTO} recuperado e status 200 OK
     */
    @GetMapping("/friend-list/{username}")
    public ResponseEntity<GetFriendListDTO> getFriendListRequests(@PathVariable String username) {
        List<Friend> friends = this.friendService.getFriendList(username);
        return ResponseEntity.ok(new GetFriendListDTO(friends));
    }

    @GetMapping("/friend-list-details/{username}")
    public ResponseEntity<GetFriendListWithDetailsDTO> getFriendListWithDetails(@PathVariable String username) {
        List<Profile> friendsProfile = this.friendService.getFriendListWithDetails(username);
        return ResponseEntity.ok(new GetFriendListWithDetailsDTO(friendsProfile));
    }

    /**
     * Roda de Exclusão de Amigo.
     * <p>
     *     Esse método mapeado para a solicitação HTTP DELETE no endpoint "/delete/{id}".
     *     Recebe o id do usuário como uma variável de caminho delega a exclusão do id da lista
     *     de amigos para o {@link FriendService}. Após a exclusão bem-sucedia do id, retorna uma resposta
     *     sem conteúdo e status 200 OK
     * </p>
     * @param id código para a exclusão da amizade
     * @return {@link ResponseEntity} sem conteúdo com status 200 OK
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteFriend(@PathVariable Long id) {
        this.friendService.deleteFriend(id);
        return ResponseEntity.ok().build();
    }
}
