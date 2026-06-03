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
	    public String buildTriagePrompt(Ticket ticket) {

	        return """
	You are an AI support ticket triage assistant.

	Analyze the ticket title and description.

	Return ONLY valid JSON.
	Do NOT use markdown.
	Do NOT include explanation.
	Do NOT include extra text.

	Allowed category values:
	BILLING, TECHNICAL, ACCOUNT, FEATURE_REQUEST, GENERAL

	Allowed priority values:
	LOW, MEDIUM, HIGH

	Allowed sentiment values:
	NEGATIVE, NEUTRAL, POSITIVE

	Tags:
	- 1 to 5 tags
	- lowercase only
	- underscore format
	- max 30 chars each

	Summary:
	- max 300 chars

	ReplyDraft:
	- max 500 chars

	Ticket Title:
	%s

	Ticket Description:
	%s

	Output JSON:
	{
	  "category": "",
	  "priority": "",
	  "tags": [],
	  "sentiment": "",
	  "summary": "",
	  "replyDraft": ""
	}
	""".formatted(
	                ticket.getTitle(),
	                ticket.getDescription()
	        );
	    }
	}


