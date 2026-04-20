package com.supporttriage.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

/**
 * Represents a support ticket created by a user.
 */
@Entity
@Table(name = "tickets")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Title of the ticket (required)
     */
    @Column(nullable = false)
    private String title;

    /**
     * Description of the issue (required)
     */
    @Column(nullable = false)
    private String description;

    /**
     * Status of the ticket.
     * Default value is OPEN.
     * Stored as STRING in DB (OPEN, IN_PROGRESS, CLOSED)
     */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TicketStatus status = TicketStatus.OPEN;
    
    
    @Enumerated(EnumType.STRING)
    @Column(nullable =false)
    private TicketPriority priority = TicketPriority.LOW;
    
    /**
     * Link ticket to the user who created it
     */
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    /**
     * Timestamp when ticket is created
     */
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    /**
     * Automatically sets createdAt before inserting into DB
     */
    @PrePersist
    public void onCreate() {
        createdAt = LocalDateTime.now();
    }

    // ===== Getters and Setters =====

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TicketStatus getStatus() {
        return status;
    }

    public void setStatus(TicketStatus status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

	public TicketPriority getPriority() {
		return priority;
	}

	public void setPriority(TicketPriority priority) {
		this.priority = priority;
	}
    
    
    
}