package com.supporttriage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.supporttriage.dto.AuthResponse;
import com.supporttriage.dto.LoginRequest;
import com.supporttriage.dto.SignupRequest;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import org.junit.jupiter.api.Test;


@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("Test")
public class TicketControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper objectMapper;
		
		
		
	//Helper Method Exerytime it will Create a User -> Login() -> Extract the JWT -> Store it -> Then call Ticket End point 
	private String getJwtToken() throws Exception {

	    // Register a new user
	    SignupRequest signupRequest = new SignupRequest();
	    signupRequest.setEmail("ticketuser@example.com");
	    signupRequest.setPassword("password123");

	    mockMvc.perform(post("/api/auth/signup")
	            .contentType(MediaType.APPLICATION_JSON)
	            .content(objectMapper.writeValueAsString(signupRequest)));

	    // Login
	    LoginRequest loginRequest = new LoginRequest();
	    loginRequest.setEmail("ticketuser@example.com");
	    loginRequest.setPassword("password123");

	    MvcResult result = mockMvc.perform(post("/api/auth/login")
	            .contentType(MediaType.APPLICATION_JSON)
	            .content(objectMapper.writeValueAsString(loginRequest)))
	            .andExpect(status().isOk())
	            .andReturn();

	    String response = result.getResponse().getContentAsString();

	    AuthResponse authResponse =
	            objectMapper.readValue(response, AuthResponse.class);

	    return authResponse.getToken();
	}
	
	
		@Test
	    void accessProtectedEndpointWithoutJwt() throws Exception {
	        // Day 3 - Test 1
			 mockMvc.perform(get("/api/tickets"))
	            .andExpect(status().isForbidden());
                 
			
	    }

	    @Test
	    void accessProtectedEndpointWithInvalidJwt() throws Exception {
	    	 mockMvc.perform(get("/api/tickets")
	    	            .header("Authorization", "Bearer invalid.jwt.token"))
	    	            .andExpect(status().isForbidden());
	    }

	/*
	    @Test
	    void authenticatedUserCanAccessOwnResources() throws Exception {
	        // Day 3 - Test 4
	    }

	    @Test
	    void userCannotAccessAnotherUsersTicket() throws Exception {
	        // Day 3 - Test 5
	    }
	
	*/
	
	
	
}
