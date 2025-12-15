package com.sevketbuyukdemir.basic_authentication.security.service;

import com.sevketbuyukdemir.basic_authentication.security.entity.Role;
import com.sevketbuyukdemir.basic_authentication.security.entity.User;
import com.sevketbuyukdemir.basic_authentication.security.repository.RoleRepository;
import com.sevketbuyukdemir.basic_authentication.security.repository.UserRepository;
import com.sevketbuyukdemir.basic_authentication.security.request.RegisterRequest;
import com.sevketbuyukdemir.basic_authentication.security.response.RegisterResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserManagementService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;


    @Transactional
    @PreAuthorize("hasRole('ADMIN')")
    public RegisterResponse registerUser(RegisterRequest registerRequest) {
        String role = registerRequest.isUserAdmin() ? "SVKTBYKDMR_ADMIN" : "SVKTBYKDMR_USER";
        Role userRole = roleRepository.findByAuthority(role).orElseThrow(() -> new NoSuchElementException("Authority not present"));
        Set<Role> authorities = new HashSet<>();
        authorities.add(userRole);
        User newUser = new User();
        newUser.setEmail(registerRequest.getEmail());
        newUser.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        newUser.setAuthorities(authorities);
        newUser.setFirstname(registerRequest.getFirstname());
        newUser.setSurname(registerRequest.getSurname());
        userRepository.save(newUser);
        return new RegisterResponse();
    }

}

