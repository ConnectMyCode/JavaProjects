	package com.supporttriage.dto;

import java.time.LocalDateTime;

import com.supporttriage.entity.TicketPriority;
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
    private TicketPriority priority;
    

    public TicketResponse(Long id, String title, String description, LocalDateTime createdAt, TicketStatus status, TicketPriority priority) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.createdAt = createdAt;
        this.status = status;
        this.priority = priority;
        
    }

    //Explicit No Arguments Constructor added.
	public TicketResponse() {
		// TODO Auto-generated constructor stub
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

	public void setId(Long id) {
		this.id = id;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	/*Priority*/
	public TicketPriority getPriority() {
		return priority;
	}

	public void setPriority(TicketPriority priority) {
		this.priority = priority;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public void setStatus(TicketStatus status) {
		this.status = status;
	}


    
    
    // getters
}