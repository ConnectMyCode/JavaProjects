package com.supporttriage.dto;

import com.supporttriage.entity.TicketPriority;

public class TriageSaveRequest {
	
	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	private String category; 
	private String sentiment;
	private TicketPriority priority;
	private String summary;
	private String replyDraft; 
	
	public TicketPriority getPriority() {
		return priority;
	}

	public void setPriority(TicketPriority priority) {
		this.priority = priority;
	}

	public String getReplyDraft() {
		return replyDraft;
	}

	public void setReplyDraft(String replyDraft) {
		this.replyDraft = replyDraft;
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
}