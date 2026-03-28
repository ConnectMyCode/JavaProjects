package com.supporttriage.repository;


import com.supporttriage.entity.Ticket;
import com.supporttriage.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;




public interface TicketRepository extends JpaRepository<Ticket, Long> {

	
    // Get tickets for a specific user
    List<Ticket> findByUser(User user);
}
