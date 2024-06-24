package tech.starvingdevelopers.smnbackend.models.dto.account.output;

import lombok.Getter;
import lombok.Setter;
import tech.starvingdevelopers.smnbackend.models.entities.Account;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
public class GetAccountDTO implements Serializable {
    private int id;
    private String username;
    private String nickname;
    private String email;
    private String gender;
    private LocalDate birthdate;
    private LocalDate createdAt;

    public GetAccountDTO(Account account) {
        this.id = account.getId();
        this.username = account.getUsername();
        this.nickname = account.getNickname();
        this.email = account.getEmail();
        this.gender = account.getGender();
        this.birthdate = account.getBirthdate();
        this.createdAt = account.getCreatedAt();
    }
}
