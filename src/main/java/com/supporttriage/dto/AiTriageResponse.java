package com.supporttriage.dto;


import java.util.List;

/**
 * Safe structured AI triage response
 * Must match strict JSON contract
 */
public class AiTriageResponse {

    private String category;
    private String priority;
    private List<String> tags;
    private String sentiment;
    private String summary;
    private String replyDraft;

    // No-args constructor
    public AiTriageResponse() {
    }

    // All-args constructor
    public AiTriageResponse(
            String category,
            String priority,
            List<String> tags,
            String sentiment,
            String summary,
            String replyDraft
    ) {
        this.category = category;
        this.priority = priority;
        this.tags = tags;
        this.sentiment = sentiment;
        this.summary = summary;
        this.replyDraft = replyDraft;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
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
}