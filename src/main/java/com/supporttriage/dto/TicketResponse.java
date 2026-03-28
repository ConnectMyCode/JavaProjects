package com.supporttriage.dto;

import java.time.LocalDateTime;

import com.supporttriage.entity.TicketStatus;

/**
 * Safe response DTO for tickets (no sensitive data)
 */
public class TicketResponse {

    private Long id;
    private String title;
    private String description;
    private LocalDateTime createdAt;
    private TicketStatus status;

    public TicketResponse(Long id, String title, String description, LocalDateTime createdAt, TicketStatus status) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.createdAt = createdAt;
        this.status = status;
    }

	public TicketStatus getStatus() {
		return status;
	}

	public Long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}



	public LocalDateTime getCreatedAt() {
		return createdAt;
	}


    
    
    // getters
}