package com.supporttriage.service;

import com.supporttriage.dto.CreateTicketRequest;
import com.supporttriage.dto.TicketResponse;
import com.supporttriage.entity.Ticket;
import com.supporttriage.entity.TicketStatus;
import com.supporttriage.entity.User;
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
     * Get tickets for logged-in user
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
    
    
    
    
}