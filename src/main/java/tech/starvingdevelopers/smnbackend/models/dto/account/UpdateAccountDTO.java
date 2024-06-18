package tech.starvingdevelopers.smnbackend.models.dto.account;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record UpdateAccountDTO(String nickname, String email, String gender, String password) {}
