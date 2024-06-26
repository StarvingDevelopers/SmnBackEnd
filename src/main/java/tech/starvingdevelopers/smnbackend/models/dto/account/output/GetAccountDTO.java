package tech.starvingdevelopers.smnbackend.models.dto.account.output;

import tech.starvingdevelopers.smnbackend.models.entities.Account;

import java.io.Serializable;
import java.time.LocalDate;

public record GetAccountDTO(String username, String email, String gender, LocalDate birthdate, LocalDate createdAt) implements Serializable {

    public static GetAccountDTO fromAccount(Account account) {
        return new GetAccountDTO(account.getUsername(), account.getEmail(), account.getGender(), account.getBirthdate(), account.getCreatedAt());
    }
}
