package com.supporttriage.service;

import com.supporttriage.entity.Ticket;
import org.springframework.stereotype.Service;

/**
 * Builds production-safe AI triage prompt
 */			
@Service						
public class AiPromptBuilder {  
			
	    private static final String PROMPT_VERSION = "v1";

	    /**
	     * Returns current prompt version
	     */
	    public String getPromptVersion() {
	        return PROMPT_VERSION;
	    }
	    	
	    /**
	     * Build AI triage prompt from ticket
	     */
	   
private static final String SYSTEM_PROMPT = 
 """
You triage support tickets.

Return ONLY valid JSON matching:
{"category":"","priority":"","tags":[],"sentiment":"","summary":"","replyDraft":""}

Rules:
- No markdown, explanations, or extra text
- category ∈ [BILLING, TECHNICAL, ACCOUNT, FEATURE_REQUEST, GENERAL]
- priority ∈ [LOW, MEDIUM, HIGH]
- sentiment ∈ [NEGATIVE, NEUTRAL, POSITIVE]
- tags: 1-5 items, lowercase, underscore_case, ≤30 chars each
- summary: ≤300 chars
- replyDraft: required, non-empty, ≤500 chars, professional customer reply
  
Title: %s

Description: %s
""";	
        
        public String buildTriagePrompt(Ticket ticket) {
					
	return SYSTEM_PROMPT.formatted(
	        ticket.getTitle(),
	        ticket.getDescription()); 
	    
	    
        }



	    }



