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
    
   // Category (AI will fill later)
    @Column(name = "category")
    private String category;
    
    
 // Sentiment (AI will fill later)
   @Column(name="sentiment")
    private String sentiment;
   
   
 // AI generated summary...
   	@Column (name = "summary")
   	private String summary;
   	
 // AI generated reply suggestion...	
   	@Column(name = "reply_draft")
   	private String replyDraft; 
   	
   	
   
 // Sentiment (AI will fill later)

   
   
   //👉 Keep them as String (DO NOT create enums now — AI phase will decide structure)
    
    
    
    /**
     * Link ticket to the user who created it
     */
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    /**
     * TimeStamp when ticket is created			
     */
    
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    
    @Column(name = "responded_at")
    private LocalDateTime respondedAt;
    
    @Column(name = "triaged_at")
    private LocalDateTime triagedAt;
        
    @Column(name = "closed_at")
    private LocalDateTime closedAt;
    
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;
   
    @Column(name = "final_response")
    private String finalResponse;
    
    @Column(name = "resolution_note")
    private String resolutionNote;
    
    /**
     * Automatically sets createdAt before inserting into DB
     * LifeCycle Hook
     */
   
    
    
    
    @PreUpdate
    public void onUpdate() 
    {
    	this.updatedAt = LocalDateTime.now();
    }
    
    public String getFinalResponse() {
		return finalResponse;
	}

	public void setFinalResponse(String finalResponse) {
		this.finalResponse = finalResponse;
	}

	public String getResolutionNote() {
		return resolutionNote;
	}

	public void setResolutionNote(String resolutionNote) {
		this.resolutionNote = resolutionNote;
	}

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

    public LocalDateTime getRespondedAt() {
		return respondedAt;
	}

	public void setRespondedAt(LocalDateTime respondedAt) {
		this.respondedAt = respondedAt;
	}

	public LocalDateTime getTriagedAt() {
		return triagedAt;
	}

	public void setTriagedAt(LocalDateTime triagedAt) {
		this.triagedAt = triagedAt;
	}

	public LocalDateTime getClosedAt() {
		return closedAt;
	}

	public void setClosedAt(LocalDateTime closedAt) {
		this.closedAt = closedAt;
	}

	public void setUser(User user) {
        this.user = user;
    }

    public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getSentiment() {
		return sentiment;
	}

	public void setSentiment(String sentiment) {
		this.sentiment = sentiment;
	}
				
	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getReplyDraft() {
		return replyDraft;
	}

	public void setReplyDraft(String replyDraft) {
		this.replyDraft = replyDraft;
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