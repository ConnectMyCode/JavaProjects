package com.supporttriage.controller;

import com.supporttriage.dto.CreateTicketRequest;
import com.supporttriage.dto.TicketResponse;
import com.supporttriage.dto.UpdateTicketStatusRequest;
import com.supporttriage.entity.Ticket;
import com.supporttriage.service.TicketService;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

import java.util.List;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {

    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    /**
     * Create ticket
     */
    @PostMapping
    public void createTicket(@RequestBody CreateTicketRequest request) {
        ticketService.createTicket(request);
    }

    /**
     * Get my tickets
     */
    @GetMapping
    public List<TicketResponse> getMyTickets() {
        return ticketService.getMyTickets();
    }
    
    
    /*
     * Update my Tickets
     * */
    @PutMapping("/{id}/status")
    public void updateStatus(@PathVariable Long id,
                             @RequestBody UpdateTicketStatusRequest request) {
        ticketService.updateTicketStatus(id, request.getStatus());
    }
}