package com.supporttriage.service;

import com.supporttriage.dto.*;
import com.supporttriage.entity.User;
import com.supporttriage.repository.UserRepository;
import com.supporttriage.security.JwtUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder,
                       JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    public void signup(SignupRequest request) {
        if (request.getPassword().length() < 8) {
            throw new RuntimeException("Password must be at least 8 characters");
        }

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        User user = new User();
        user.setEmail(request.getEmail());
        user.setPasswordHash(passwordEncoder.encode(request.getPassword()));

        userRepository.save(user);
    }

    public AuthResponse login(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid credentials"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPasswordHash())) {
            throw new RuntimeException("Invalid credentials");
        }

        String token = jwtUtil.generateToken(user.getEmail());
        return new AuthResponse(token);
    }
}


/**
 * Service layer handling authentication logic.
 *
 * Responsibilities:
 *
 * 1. Signup:
 * - Validate password length (>= 8)
 * - Check if email already exists
 * - Hash password using BCrypt
 * - Save user to database
 *
 * 2. Login:
 * - Fetch user by email
 * - Compare raw password with stored hash
 * - Generate JWT token if valid
 *
 * Important:
 * - Business logic lives here (not in controller)
 * - Password comparison uses PasswordEncoder.matches()
 */