package com.supporttriage.controller;

import com.supporttriage.dto.TicketResponse;
import com.supporttriage.service.TicketService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
	
	
	private final TicketService ticketService;
	
	public AdminController(TicketService ticketService) 
	{
		this.ticketService = ticketService;
	}
	
	
	  
	/*
	 * ADMIN gets all ticket  with optional filtering
	 * 
	 * */
	@GetMapping("/tickets")
	public Page<TicketResponse> getAllTickets( 
			Pageable pageable, 
			@RequestParam(required = false) List<String> status, 
			@RequestParam(required = false) List<String> priority) 
	{
		return ticketService.getAllTicketsForAdmin(pageable, status, priority);
	}
	
}
