package com.sevketbuyukdemir.basic_authentication.security.response;

import com.sevketbuyukdemir.basic_authentication.constant.ResponseStatusMessage;
import lombok.Data;

@Data
public class RegisterResponse {
    private String status = ResponseStatusMessage.SUCCESS.toLower();
    private String message = "User is created successfully.";
}