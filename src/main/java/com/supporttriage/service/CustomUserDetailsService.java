package com.supporttriage.service;

import com.supporttriage.entity.User;
import com.supporttriage.repository.UserRepository;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return org.springframework.security.core.userdetails.User
                .withUsername(user.getEmail())
                .password(user.getPasswordHash())
                .authorities("USER")
                .build();
    }
}


/**
 * Loads user details from the database for authentication.
 *
 * Required by Spring Security.
 *
 * Method:
 * - loadUserByUsername(email)
 *   → fetches user from DB
 *   → converts it into Spring Security UserDetails object
 *
 * Used by:
 * - JwtFilter (to validate user from token)
 *
 * Note:
 * Even though we don’t use roles now, Spring requires authorities,
 * so "USER" is assigned by default.
 */