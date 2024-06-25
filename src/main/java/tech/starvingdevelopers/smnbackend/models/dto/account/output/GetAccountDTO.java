package tech.starvingdevelopers.smnbackend.models.dto.account.output;

import lombok.Getter;
import lombok.Setter;
import tech.starvingdevelopers.smnbackend.models.entities.Account;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
public record GetAccountDTO(String username, String nickname, String email, String gender, LocalDate birthdate, LocalDate createdAt) implements Serializable {

    public static GetAccountDTO fromAccount(Account account) {
        return new GetAccountDTO(account.getUsername(), account.getNickname(), account.getEmail(), account.getGender(), account.getBirthdate(), account.getCreatedAt());
    }
}
