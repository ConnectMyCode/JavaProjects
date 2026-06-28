package com.supporttriage.dto;

public class LoginRequest {
    private String email;
    private String password;
    
	public String getEmail() {
		return email;
	}
	// getters & setters
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}


	/**
	 * DTO for login request.
	 *
	 * Contains:
	 * - email
	 * - password
	 *
	 * Used by the login endpoint to authenticate user.
	 */
	
	
}