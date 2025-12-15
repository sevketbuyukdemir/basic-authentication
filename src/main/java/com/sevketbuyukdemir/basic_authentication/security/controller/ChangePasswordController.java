package com.sevketbuyukdemir.basic_authentication.security.controller;

import com.sevketbuyukdemir.basic_authentication.security.request.ChangeMyPasswordRequest;
import com.sevketbuyukdemir.basic_authentication.security.response.ChangeMyPasswordResponse;
import com.sevketbuyukdemir.basic_authentication.security.service.ChangeMyPasswordService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class ChangePasswordController {
    private final ChangeMyPasswordService changeMyPasswordService;

    @PutMapping("/change-my-password")
    public ResponseEntity<ChangeMyPasswordResponse> changeMyPassword(HttpServletRequest request, HttpServletResponse response, @RequestBody ChangeMyPasswordRequest changeMyPasswordRequest) {
        ChangeMyPasswordResponse changeMyPasswordResponse = changeMyPasswordService.changeMyPassword(changeMyPasswordRequest);
        return new ResponseEntity<>(changeMyPasswordResponse, HttpStatus.OK);
    }
}
