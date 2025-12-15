package com.sevketbuyukdemir.basic_authentication.security.request;

import lombok.Data;

@Data
public class ChangeMyPasswordRequest {
    private String newPassword;
}
