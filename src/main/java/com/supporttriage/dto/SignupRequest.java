package com.supporttriage.dto;

public class SignupRequest {
    private String email;
    private String password;
	public String getEmail() {
		return email;
	}
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
	 * DTO for user signup request.
	 *
	 * Receives data from client (Postman):
	 * - email
	 * - password
	 *
	 * This class is used to transfer data from the request body
	 * to the service layer.
	 *
	 * No business logic here.
	 */
    
    // getters & setters
}