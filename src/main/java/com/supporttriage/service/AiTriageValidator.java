package com.supporttriage.service;

import com.supporttriage.dto.AiTriageResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * Validates AI triage response safely
 */
@Service
public class AiTriageValidator {

    private static final Set<String> ALLOWED_CATEGORIES = Set.of(
            "BILLING",
            "TECHNICAL",
            "ACCOUNT",
            "FEATURE_REQUEST",
            "GENERAL"
    );

    private static final Set<String> ALLOWED_PRIORITIES = Set.of(
            "LOW",
            "MEDIUM",
            "HIGH"
    );

    private static final Set<String> ALLOWED_SENTIMENTS = Set.of(
            "NEGATIVE",
            "NEUTRAL",
            "POSITIVE"
    );

    /**
     * Validate structured AI response
     */
    public void validate(AiTriageResponse response) {

        // Category validation
        if (response.getCategory() == null
                || !ALLOWED_CATEGORIES.contains(response.getCategory())) {

            throw new RuntimeException("Invalid AI category");
        }

        // Priority validation
        if (response.getPriority() == null
                || !ALLOWED_PRIORITIES.contains(response.getPriority())) {

            throw new RuntimeException("Invalid AI priority");
        }

        // Sentiment validation
        if (response.getSentiment() == null
                || !ALLOWED_SENTIMENTS.contains(response.getSentiment())) {

            throw new RuntimeException("Invalid AI sentiment");
        }

        // Summary validation
        if (response.getSummary() == null
                || response.getSummary().trim().isEmpty()) {

            throw new RuntimeException("AI summary missing");
        }

        // ReplyDraft validation
        if (response.getReplyDraft() == null
                || response.getReplyDraft().trim().isEmpty()) {

            throw new RuntimeException("AI replyDraft missing");
        }

        // Tags validation
        List<String> tags = response.getTags();

        if (tags == null || tags.isEmpty()) {
            throw new RuntimeException("AI tags missing");
        }

        if (tags.size() > 5) {
            throw new RuntimeException("Too many AI tags");
        }

        for (String tag : tags) {

            if (tag == null || tag.trim().isEmpty()) {
                throw new RuntimeException("Invalid AI tag");
            }

            if (tag.length() > 30) {
                throw new RuntimeException("AI tag too long");
            }

            if (tag.contains(" ")) {
                throw new RuntimeException("AI tag format invalid");
            }
        }
    }

}
