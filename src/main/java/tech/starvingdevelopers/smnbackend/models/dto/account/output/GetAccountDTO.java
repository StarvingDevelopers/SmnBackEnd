package tech.starvingdevelopers.smnbackend.models.dto.account.output;

import tech.starvingdevelopers.smnbackend.models.entities.Account;

import java.time.LocalDate;

public record GetAccountDTO(int id, String username, String nickname, String email, String gender, LocalDate birthdate, LocalDate createdAt) {

    public static GetAccountDTO createDTO(Account account) {
        return new GetAccountDTO(account.getId(), account.getUsername(), account.getNickname(), account.getEmail(), account.getGender(), account.getBirthdate(), account.getCreatedAt());
    }
}
