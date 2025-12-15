package com.sevketbuyukdemir.basic_authentication.security.response;

import com.sevketbuyukdemir.basic_authentication.constant.ResponseStatusMessage;
import lombok.Data;

@Data
public class InvalidSessionResponse {
    String status = ResponseStatusMessage.FAILURE.toLower();
    String message = "Invalid session please login again.";
}