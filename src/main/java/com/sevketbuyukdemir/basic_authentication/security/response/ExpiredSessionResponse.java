package com.sevketbuyukdemir.basic_authentication.security.response;

import com.sevketbuyukdemir.basic_authentication.constant.ResponseStatusMessage;
import lombok.Data;

@Data
public class ExpiredSessionResponse {
    String status = ResponseStatusMessage.FAILURE.toLower();
    String message = "Session is expired please login again.";
}