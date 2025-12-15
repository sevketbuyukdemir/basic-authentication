package com.sevketbuyukdemir.basic_authentication.constant;

import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Locale;

public enum ResponseStatusMessage {
    SUCCESS,
    FAILURE;

    @JsonValue
    public String toLower() {
        return this.name().toLowerCase(Locale.ENGLISH);
    }
}