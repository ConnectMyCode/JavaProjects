package com.supporttriage.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

/*
 * * Tracks each AI execution for ticket triage.
 * Separate from Ticket lifecycle.
 * 
 * 
 * */



@Entity
@Table(name = "ai_runs")
public class AiRun {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	
	/**
     * Ticket linked to this AI run
     */
	@ManyToOne
	@JoinColumn(name = "ticket_id", nullable = false)
	private Ticket ticket;
	
	
	
    /**
     * User who triggered AI run
     */
	@ManyToOne
	@JoinColumn(name ="user_id", nullable = false)
	private User user;
	
	
	/*
	 * Purpose of AI run
	 * Example: TRIAGE
	 * */
	@Column(nullable =false)
	private String provider = "GROQ"; 


	/**
     * Model used
     * Example: gpt-4o-mini
     */
	@Column(nullable = false)
	private String model;


	/**
     * Prompt version for tracking prompt evolution
     */
	@Column(name = "prompt_version", nullable = false)
	private String promptVersion;
	
	
	/**
     * AI execution lifecycle
     * PENDING / SUCCESS / FAILED
     */
	@Column(nullable = false)
	private String status = "PENDING";

	
	/**
     * Stores failure reason if AI call fails
     */
	@Column(name = "error_message", columnDefinition="TEXT")
	private String errorMessage;
	
	/*
	 * Raw request sent to AI
	 * */ 
	@Column(name = "raw_request", columnDefinition = "TEXT")
	private String rawRequest;

	
	/*
	 * Raw AI Response
	 * */
	@Column(name = "raw_response", columnDefinition= "TEXT")
	private String rawResponse;
	
	
	/*
	 * Created timestamp 
	 * */
	@Column(name = "created_at" , nullable = false, updatable = false )
	private LocalDateTime createdAt;

	
	/*
	 * Updated timestamp
	 * */
	@Column(name = "updated_at")
	private LocalDateTime updatedAt;

	
	/*
	 * Automatically sets createdAt before insert
	 * */
	@PrePersist
	public void onCreate() { this.createdAt = LocalDateTime.now(); }
	
	
	/* Automatically updates updatedAt before update
	 *  */
	@PreUpdate
	public void onUpdate() { this.updatedAt = LocalDateTime.now(); }
	
	
	@Column(nullable = false)
	private String purpose = "TRIAGE";
	
	
	
	
	//==============Getter and Setters===================//

	
	public String getPurpose() {
	    return purpose;
	}

	public void setPurpose(String purpose) {
	    this.purpose = purpose;
	}
	
	
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Ticket getTicket() {
		return ticket;
	}


	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public String getProvider() {
		return provider;
	}


	public void setProvider(String provider) {
		this.provider = provider;
	}


	public String getModel() {
		return model;
	}


	public void setModel(String model) {
		this.model = model;
	}


	public String getPromptVersion() {
		return promptVersion;
	}


	public void setPromptVersion(String promptVersion) {
		this.promptVersion = promptVersion;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public String getErrorMessage() {
		return errorMessage;
	}


	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}


	public String getRawRequest() {
		return rawRequest;
	}


	public void setRawRequest(String rawRequest) {
		this.rawRequest = rawRequest;
	}


	public String getRawResponse() {
		return rawResponse;
	}


	public void setRawResponse(String rawResponse) {
		this.rawResponse = rawResponse;
	}


	public LocalDateTime getCreatedAt() {
		return createdAt;
	}


	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}


	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}


	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

}



