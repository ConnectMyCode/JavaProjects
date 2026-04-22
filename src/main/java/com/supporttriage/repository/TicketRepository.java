package com.supporttriage.repository;


import com.supporttriage.entity.Ticket;
import com.supporttriage.entity.TicketPriority;
import com.supporttriage.entity.TicketStatus;
import com.supporttriage.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Long> {

	
    // Get tickets for a specific user
    List<Ticket> findByUser(User user);
    
    Page<Ticket> findByUser(User user, Pageable pageable);
    Page<Ticket> findByUserAndStatusIn(User user, List<TicketStatus> statuses, Pageable pageable);
    Page<Ticket> findByUserAndPriorityIn(User user, List<TicketPriority> priorities , Pageable pageable); 
    
    Page<Ticket> findByUserAndStatusInAndPriorityIn(
    		User user,
    		List<TicketStatus> statuses,
    		List<TicketPriority> priorities,
    		Pageable pageable
    );
    
    
    //Get tickets for Admin
    Page<Ticket> findByStatusIn(List<TicketStatus> statuses, Pageable pageable);

    Page<Ticket> findByPriorityIn(List<TicketPriority> priorities, Pageable pageable);

    Page<Ticket> findByStatusInAndPriorityIn(
            List<TicketStatus> statuses,
            List<TicketPriority> priorities,
            Pageable pageable
    );
    
}
