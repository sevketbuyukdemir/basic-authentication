package com.sevketbuyukdemir.basic_authentication.config;

import com.sevketbuyukdemir.basic_authentication.security.entity.Role;
import com.sevketbuyukdemir.basic_authentication.security.entity.User;
import com.sevketbuyukdemir.basic_authentication.security.repository.RoleRepository;
import com.sevketbuyukdemir.basic_authentication.security.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@RequiredArgsConstructor
public class ApplicationInitializationListener implements ApplicationListener<ContextRefreshedEvent> {
    private final Logger logger = LogManager.getLogger(ApplicationInitializationListener.class);

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        createRolesIfNotExist();
        createAdminIfNotExist();
        logger.info("Application is started...");
    }

    private void createRolesIfNotExist() {
        if (!roleRepository.findAll().isEmpty()) {
            logger.info("Roles are already exist.");
        } else {
            Role adminRole = new Role();
            adminRole.setAuthority("SVKTBYKDMR_ADMIN");
            Role userRole = new Role();
            userRole.setAuthority("SVKTBYKDMR_USER");
            List<Role> roles = new ArrayList<>();
            roles.add(adminRole);
            roles.add(userRole);
            roleRepository.saveAll(roles);
            logger.info("Roles are created.");
        }
    }

    private void createAdminIfNotExist() {
        if (!userRepository.findAll().isEmpty()) {
            logger.info("Admin user is already exist.");
        } else {
            String role = "SVKTBYKDMR_ADMIN";
            Role userRole = roleRepository.findByAuthority(role).orElseThrow(() -> new NoSuchElementException("Authority not present"));
            Set<Role> authorities = new HashSet<>();
            authorities.add(userRole);
            User newUser = new User();
            newUser.setEmail("admin@gmail.com");
            newUser.setPassword(passwordEncoder.encode("admin"));
            newUser.setAuthorities(authorities);
            newUser.setFirstname("Şevket");
            newUser.setSurname("Büyükdemir");
            userRepository.save(newUser);
            logger.info("Admin user is created.");
        }
    }

}
