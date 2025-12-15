package com.sevketbuyukdemir.basic_authentication.security.response;

import com.sevketbuyukdemir.basic_authentication.constant.ResponseStatusMessage;
import lombok.Data;

@Data
public class LoginResponse {
    String status = ResponseStatusMessage.SUCCESS.toLower();
    String message;
}
