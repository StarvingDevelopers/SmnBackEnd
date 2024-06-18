package tech.starvingdevelopers.smnbackend.models.dto.account;

import tech.starvingdevelopers.smnbackend.models.entities.Account;

import java.time.LocalDate;

public record CreateAccountDTO(String username, String email, String password, String gender, LocalDate birthdate) {

    public Account toAccount(String encryptPassword) {
        return new Account(username, email, encryptPassword, gender, birthdate);
    }
}
