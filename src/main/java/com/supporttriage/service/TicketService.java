package com.supporttriage.service;

import com.supporttriage.dto.CreateTicketRequest;
import com.supporttriage.dto.TicketResponse;
import com.supporttriage.entity.Ticket;
import com.supporttriage.entity.TicketStatus;
import com.supporttriage.entity.User;
import com.supporttriage.exception.ResourceNotFoundException;
import com.supporttriage.repository.TicketRepository;
import com.supporttriage.repository.UserRepository;
import com.supporttriage.security.SecurityUtil;
import org.springframework.stereotype.Service;

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

        ticketRepository.save(ticket);
    }

    /**
     * 
     * Get tickets for logged-in user
     * 
     */
    public List<TicketResponse> getMyTickets() {

        String email = SecurityUtil.getCurrentUserEmail();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return ticketRepository.findByUser(user)
                .stream()
                .map(ticket -> new TicketResponse(
                        ticket.getId(),
                        ticket.getTitle(),
                        ticket.getDescription(),
                        ticket.getCreatedAt(),
                        ticket.getStatus()
                ))
                .collect(Collectors.toList());
        		
        		
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
    
    
    
    //Pagenation: 
    public org.springframework.data.domain.Page<TicketResponse> getUserTickets(
            org.springframework.data.domain.Pageable pageable,
            List<String> statusList   
    ) {

        // 🔐 Get current user
        String email = com.supporttriage.security.SecurityUtil.getCurrentUserEmail();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        org.springframework.data.domain.Page<Ticket> ticketPage;

        // 📌 No filter case
        if (statusList == null || statusList.isEmpty()) {
            ticketPage = ticketRepository.findByUser(user, pageable);
        } else {
            // 🔄 Convert String → Enum (IMPORTANT)
            List<TicketStatus> statuses = statusList.stream()
                    .map(s -> TicketStatus.valueOf(s.trim().toUpperCase()))
                    .collect(java.util.stream.Collectors.toList());

            ticketPage = ticketRepository.findByUserAndStatusIn(user, statuses, pageable);
        }

        // 📦 Convert Page<Ticket> → Page<TicketResponse>
        return ticketPage.map(ticket -> mapToResponse(ticket));
    }
    
    //Mapping method 
    private TicketResponse mapToResponse(Ticket ticket) {
        TicketResponse response = new TicketResponse();
        response.setId(ticket.getId());
        response.setTitle(ticket.getTitle());
        response.setDescription(ticket.getDescription());
        response.setStatus(ticket.getStatus());
        response.setCreatedAt(ticket.getCreatedAt());
        return response;
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