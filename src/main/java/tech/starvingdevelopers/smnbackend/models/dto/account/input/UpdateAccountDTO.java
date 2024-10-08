package tech.starvingdevelopers.smnbackend.models.dto.account.input;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record UpdateAccountDTO(String username, String nickname, String email, String gender) {}
