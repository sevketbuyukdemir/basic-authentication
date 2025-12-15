package com.sevketbuyukdemir.basic_authentication.security.repository;

import com.sevketbuyukdemir.basic_authentication.security.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {}