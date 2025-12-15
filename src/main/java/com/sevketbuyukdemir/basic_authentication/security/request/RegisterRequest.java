package com.sevketbuyukdemir.basic_authentication.security.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class RegisterRequest {
    private String email;
    private String password;
    private String firstname;
    private String surname;
    @JsonProperty
    private boolean isUserAdmin;
}
