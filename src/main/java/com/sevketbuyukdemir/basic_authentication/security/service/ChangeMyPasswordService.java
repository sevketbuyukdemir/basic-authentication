package com.sevketbuyukdemir.basic_authentication.security.service;

import com.sevketbuyukdemir.basic_authentication.constant.ExceptionResponseMessages;
import com.sevketbuyukdemir.basic_authentication.security.entity.User;
import com.sevketbuyukdemir.basic_authentication.exception.PasswordIsNotValidException;
import com.sevketbuyukdemir.basic_authentication.security.repository.UserRepository;
import com.sevketbuyukdemir.basic_authentication.security.request.ChangeMyPasswordRequest;
import com.sevketbuyukdemir.basic_authentication.security.response.ChangeMyPasswordResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChangeMyPasswordService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private static void validateNewPassword(String password) {
        int numChars = password.replaceAll("(?i)[^A-Z]+", "").length();
        int numDigits = password.replaceAll("\\D+", "").length();
        int numSpecial = password.replaceAll("[^!@#$%^&*()_+.-]", "").length();
        if (numChars < 8 || numDigits < 1 || numSpecial < 1) {
            throw new PasswordIsNotValidException(ExceptionResponseMessages.PASSWORD_IS_NOT_VALID_EXCEPTION.toString());
        }
    }

    @Transactional
    @PreAuthorize("hasRole('USER')")
    public ChangeMyPasswordResponse changeMyPassword(ChangeMyPasswordRequest changeMyPasswordRequest) {
        validateNewPassword(changeMyPasswordRequest.getNewPassword());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByEmail(authentication.getName()).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        user.setPassword(passwordEncoder.encode(changeMyPasswordRequest.getNewPassword()));
        userRepository.save(user);
        return getChangeMyPasswordSuccessResponse();
    }

    private ChangeMyPasswordResponse getChangeMyPasswordSuccessResponse() {
        ChangeMyPasswordResponse response = new ChangeMyPasswordResponse();
        response.setMessage("Password is updated successfully.");
        return response;
    }
}

