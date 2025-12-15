package com.sevketbuyukdemir.basic_authentication.security.controller;

import com.sevketbuyukdemir.basic_authentication.security.request.LoginRequest;
import com.sevketbuyukdemir.basic_authentication.security.response.ExpiredSessionResponse;
import com.sevketbuyukdemir.basic_authentication.security.response.InvalidSessionResponse;
import com.sevketbuyukdemir.basic_authentication.security.response.LoginResponse;
import com.sevketbuyukdemir.basic_authentication.security.service.LoginService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class LoginController {
    private final LoginService loginService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(HttpServletRequest request, HttpServletResponse response, @RequestBody LoginRequest loginRequest) {
        LoginResponse loginResponse = loginService.login(request, response, loginRequest);
        return new ResponseEntity<>(loginResponse, HttpStatus.OK);
    }

    @GetMapping("/invalid-session")
    public ResponseEntity<InvalidSessionResponse> invalidSession(Authentication authentication, HttpServletRequest request) {
        return new ResponseEntity<>(new InvalidSessionResponse(), HttpStatus.FORBIDDEN);
    }

    @GetMapping("/expired-session")
    public ResponseEntity<ExpiredSessionResponse> expiredSession(Authentication authentication, HttpServletRequest request) {
        return new ResponseEntity<>(new ExpiredSessionResponse(), HttpStatus.FORBIDDEN);
    }
}

