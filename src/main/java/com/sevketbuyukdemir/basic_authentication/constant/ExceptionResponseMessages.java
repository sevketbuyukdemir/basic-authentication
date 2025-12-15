package com.sevketbuyukdemir.basic_authentication.constant;

public enum ExceptionResponseMessages {
    GENERIC_ERROR {
        public String toString() {
            return "Internal server error.";
        }
    },
    BAD_REQUEST {
        public String toString() {
            return "Bad request.";
        }
    },
    AUTHENTICATION_EXCEPTION {
        public String toString() {
            return "Authentication failure.";
        }
    },
    AUTHORIZATION_DENIED_EXCEPTION {
        public String toString() {
            return "Authorization failure.";
        }
    },
    SQL_EXCEPTION {
        public String toString() {
            return "Persistence layer related error. Please try again later.";
        }
    },
    REJECTED_EXECUTION_EXCEPTION {
        public String toString() {
            return "Bandwidth limit exceeded. Please try again later.";
        }
    },
    PASSWORD_IS_NOT_VALID_EXCEPTION {
        public String toString() {
            return "Password is not valid.";
        }
    }
}