package com.sevketbuyukdemir.basic_authentication.exception;

import com.sevketbuyukdemir.basic_authentication.response.BaseResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ExceptionResponse extends BaseResponse {
    private String status;
    private String message;
}
