package com.supporttriage.controller;

import com.supporttriage.dto.*;
import com.supporttriage.service.AuthService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/signup")
    public void signup(@RequestBody SignupRequest request) {
        authService.signup(request);
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody LoginRequest request) {
        return authService.login(request);
    }
}
/**
 * REST controller for authentication APIs.
 *
 * Base path: /api/auth
 *
 * Endpoints:
 *
 * POST /signup
 * - Registers a new user
 *
 * POST /login
 * - Authenticates user
 * - Returns JWT token
 *
 * Notes:
 * - Accepts JSON request body
 * - Delegates logic to AuthService
 * - Does not contain business logic
 */

