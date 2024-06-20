package tech.starvingdevelopers.smnbackend.models.dto.account.input;

public record UpdateAccountPasswordDTO(String username, String actualPassword, String newPassword) {
}
