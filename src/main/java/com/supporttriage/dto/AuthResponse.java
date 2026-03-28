package com.supporttriage.dto;

public class AuthResponse {
    private String token;

    public AuthResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}


/**
 * DTO for authentication response.
 *
 * Contains:
 * - token: JWT token returned after successful login
 *
 * This token must be sent by the client in future requests
 * via the Authorization header.
 */
