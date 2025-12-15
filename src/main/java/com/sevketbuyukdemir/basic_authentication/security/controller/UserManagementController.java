package com.sevketbuyukdemir.basic_authentication.security.controller;

import com.sevketbuyukdemir.basic_authentication.security.request.RegisterRequest;
import com.sevketbuyukdemir.basic_authentication.security.response.RegisterResponse;
import com.sevketbuyukdemir.basic_authentication.security.service.UserManagementService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/user-management", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class UserManagementController {
    private final UserManagementService userManagementService;

    @PostMapping
    public ResponseEntity<RegisterResponse> registerUser(@RequestBody RegisterRequest registerRequest) {
        RegisterResponse registerResponse = userManagementService.registerUser(registerRequest);
        return new ResponseEntity<>(registerResponse, HttpStatus.OK);
    }

}
