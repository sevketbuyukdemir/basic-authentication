package com.sevketbuyukdemir.basic_authentication.exception;

import com.sevketbuyukdemir.basic_authentication.constant.ExceptionResponseMessages;
import com.sevketbuyukdemir.basic_authentication.constant.ResponseStatusMessage;
import com.sevketbuyukdemir.basic_authentication.response.BaseResponse;
import jakarta.persistence.PersistenceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AuthorizationServiceException;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.naming.AuthenticationException;
import javax.naming.AuthenticationNotSupportedException;
import java.sql.SQLException;
import java.util.concurrent.RejectedExecutionException;

@ControllerAdvice
public class ExceptionResponseHandler {
    private final Logger logger = LogManager.getLogger(ExceptionResponseHandler.class);

    @ExceptionHandler(value = { Exception.class, ClassNotFoundException.class })
    public ResponseEntity<BaseResponse> exceptionHandler(Exception exception) {
        logger.info("{}: {}", exception.getClass(), exception.getMessage());
        return new ResponseEntity<>(prepareExceptionResponse(ExceptionResponseMessages.GENERIC_ERROR.toString()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = { IllegalArgumentException.class })
    public ResponseEntity<BaseResponse> exceptionBadRequestHandler(IllegalArgumentException illegalArgumentException) {
        logger.info(illegalArgumentException.getMessage());
        return new ResponseEntity<>(prepareExceptionResponse(ExceptionResponseMessages.BAD_REQUEST.toString()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = { DataIntegrityViolationException.class, PersistenceException.class, SQLException.class })
    public ResponseEntity<BaseResponse> exceptionPersistenceExceptionHandler(SQLException sqlException) {
        logger.info(sqlException.getMessage());
        return new ResponseEntity<>(prepareExceptionResponse(ExceptionResponseMessages.SQL_EXCEPTION.toString()), HttpStatus.SERVICE_UNAVAILABLE);
    }

    @ExceptionHandler(value = { AuthenticationException.class, AuthenticationServiceException.class, AuthenticationNotSupportedException.class })
    public ResponseEntity<BaseResponse> exceptionAuthenticationExceptionHandler(AuthenticationException authenticationException) {
        logger.info(authenticationException.getMessage());
        return new ResponseEntity<>(prepareExceptionResponse(ExceptionResponseMessages.AUTHENTICATION_EXCEPTION.toString()), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(value = { AuthorizationDeniedException.class, AuthorizationServiceException.class})
    public ResponseEntity<BaseResponse> exceptionAuthorizationDeniedExceptionHandler(AuthorizationDeniedException authorizationDeniedException) {
        logger.info(authorizationDeniedException.getMessage());
        return new ResponseEntity<>(prepareExceptionResponse(ExceptionResponseMessages.AUTHORIZATION_DENIED_EXCEPTION.toString()), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(value = { RejectedExecutionException.class })
    public ResponseEntity<BaseResponse> rejectedExecutionExceptionHandler(RejectedExecutionException rejectedExecutionException) {
        logger.info(rejectedExecutionException.getMessage());
        return new ResponseEntity<>(prepareExceptionResponse(ExceptionResponseMessages.REJECTED_EXECUTION_EXCEPTION.toString()), HttpStatus.BANDWIDTH_LIMIT_EXCEEDED);
    }

    @ExceptionHandler(value = { PasswordIsNotValidException.class })
    public ResponseEntity<BaseResponse> passwordIsNotValidExceptionHandler(PasswordIsNotValidException passwordIsNotValidException) {
        logger.info(passwordIsNotValidException.getMessage());
        return new ResponseEntity<>(prepareExceptionResponse(ExceptionResponseMessages.PASSWORD_IS_NOT_VALID_EXCEPTION.toString()), HttpStatus.BAD_REQUEST);
    }

    private static ExceptionResponse prepareExceptionResponse(String message) {
        ExceptionResponse exceptionResponse = new ExceptionResponse();
        exceptionResponse.setStatus(ResponseStatusMessage.FAILURE.toLower());
        exceptionResponse.setMessage(message);
        return exceptionResponse;
    }
}
