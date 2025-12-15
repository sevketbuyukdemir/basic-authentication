package com.sevketbuyukdemir.basic_authentication.exception;

public class PasswordIsNotValidException  extends RuntimeException {
    public PasswordIsNotValidException(String message) {
        super(message);
    }
}
