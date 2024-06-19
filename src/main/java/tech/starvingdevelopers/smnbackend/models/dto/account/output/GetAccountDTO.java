package tech.starvingdevelopers.smnbackend.models.dto.account.output;

import java.time.LocalDate;

public record GetAccountDTO(String username, String nickname, String email, String gender, LocalDate birthdate) {
}
