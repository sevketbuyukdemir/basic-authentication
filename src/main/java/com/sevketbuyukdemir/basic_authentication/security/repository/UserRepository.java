package com.sevketbuyukdemir.basic_authentication.security.repository;

import com.sevketbuyukdemir.basic_authentication.security.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
