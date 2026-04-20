package com.supporttriage.service;

import com.supporttriage.dto.CreateTicketRequest; 
import com.supporttriage.dto.TicketResponse;
import com.supporttriage.entity.Ticket;
import com.supporttriage.entity.TicketPriority;
import com.supporttriage.entity.TicketStatus;
import com.supporttriage.entity.User;
import com.supporttriage.exception.ResourceNotFoundException;
import com.supporttriage.repository.TicketRepository;
import com.supporttriage.repository.UserRepository;
import com.supporttriage.security.SecurityUtil;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.stream.Collectors;



import java.util.List;

@Service
public class TicketService {

    private final TicketRepository ticketRepository;
    private final UserRepository userRepository;

    public TicketService(TicketRepository ticketRepository,
                         UserRepository userRepository) {
        this.ticketRepository = ticketRepository;
        this.userRepository = userRepository;
    }
    
    /**
     * Create ticket for logged-in user
     */
    public void createTicket(CreateTicketRequest request) {

        String email = SecurityUtil.getCurrentUserEmail();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Ticket ticket = new Ticket();
        ticket.setTitle(request.getTitle());
        ticket.setDescription(request.getDescription());
        ticket.setUser(user);
        
        TicketPriority priority = TicketPriority.LOW;
        
        if(request.getPriority() != null && !request.getPriority().isEmpty()) 
        {
        	try 
        	{
        		priority = TicketPriority.valueOf(request.getPriority().trim().toUpperCase());        		
        	}
        	catch(IllegalArgumentException e) 
        	{
        		throw new RuntimeException("Invalid priority value");
        	}
        }
        
        ticket.setPriority(priority); 
        ticketRepository.save(ticket);
    }

    /**
     * 
     * Get tickets for logged-in user	
     * 
     */
    public Page<TicketResponse> getMyTickets(
            Pageable pageable,
            List<String> statusParams,
            List<String> priorityParams
    ) {

        String email = SecurityUtil.getCurrentUserEmail();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // ✅ Convert status
        List<TicketStatus> statuses = null;

        if (statusParams != null && !statusParams.isEmpty()) {
            statuses = statusParams.stream()
                    .map(s -> TicketStatus.valueOf(s.trim().toUpperCase()))
                    .collect(Collectors.toList());
        }

        // ✅ Convert priority (YOUR STEP 4)
        List<TicketPriority> priorities = null;

        if (priorityParams != null && !priorityParams.isEmpty()) {
            priorities = priorityParams.stream()
                    .map(p -> {
                        try {
                            return TicketPriority.valueOf(p.trim().toUpperCase());
                        } catch (IllegalArgumentException e) {
                            throw new RuntimeException("Invalid priority value");
                        }
                    })
                    .collect(Collectors.toList());
        }

        Page<Ticket> ticketPage;

        // ✅ Case 1: No filters
        if (statuses == null && priorities == null) {
            ticketPage = ticketRepository.findByUser(user, pageable);
        }

        // ✅ Case 2: Only status
        else if (statuses != null && priorities == null) {
            ticketPage = ticketRepository.findByUserAndStatusIn(user, statuses, pageable);
        }

        // ✅ Case 3: Only priority
        else if (statuses == null) {
            ticketPage = ticketRepository.findByUserAndPriorityIn(user, priorities, pageable);
        }

        // ✅ Case 4: Both filters
        else {
            ticketPage = ticketRepository.findByUserAndStatusInAndPriorityIn(
                    user, statuses, priorities, pageable);
        }

        return ticketPage.map(ticket -> mapToResponse(ticket));
        
    }
    
    private TicketResponse mapToResponse(Ticket ticket) {
        TicketResponse response = new TicketResponse();
        response.setId(ticket.getId());
        response.setTitle(ticket.getTitle());
        response.setDescription(ticket.getDescription());
        response.setStatus(ticket.getStatus());
        response.setCreatedAt(ticket.getCreatedAt());
        response.setPriority(ticket.getPriority());
        return response;
    }
    
    
    
    
    /*Update ticket Status */
    public void updateTicketStatus(Long ticketId, TicketStatus status) {

        String email = SecurityUtil.getCurrentUserEmail();   

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Ticket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new RuntimeException("Ticket not found"));

        // Ensure user owns the ticket
        if (!ticket.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("Unauthorized");
        }

        ticket.setStatus(status);
        ticketRepository.save(ticket);

    }
    
    
  
   
    public void closeTicket(Long id)  
    {
    	//1.Get current user
    	String email = SecurityUtil.getCurrentUserEmail();
    	
    	User user =  userRepository.findByEmail(email)
    			.orElseThrow(() -> new ResourceNotFoundException("Ticket not found")); 
    	
    	//2. Find Ticket 
    	Ticket ticket = ticketRepository.findById(id)
    			.orElseThrow(() -> new ResourceNotFoundException("Ticket not Found"));
    	
    	//3. Ownership check (CRITICAL)
    	if(!ticket.getUser().getId().equals(user.getId()))          
    	{
    		throw new RuntimeException("Unauthorized");
    	}
    	// 4. Checking the current state  if CLOSED>>No Update required And Gives error "Already closed" if not closed then will close 🔥 ADD THIS CHECK
    	if (ticket.getStatus() == TicketStatus.CLOSED) {
    	    throw new RuntimeException("Ticket already closed");
    	}

    	
    	//Set status 
    	ticket.setStatus(TicketStatus.CLOSED); 
    	
    	
    	//Save
    	ticketRepository.save(ticket);
    	
    }
    
    
    
    
}