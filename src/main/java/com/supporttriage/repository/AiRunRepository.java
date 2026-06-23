package com.supporttriage.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.supporttriage.entity.AiRun;
import com.supporttriage.entity.Ticket;
import com.supporttriage.entity.User;

public interface AiRunRepository extends JpaRepository<AiRun, Long>{
	
	/*
	 * Get all AI runs for a specific ticket
	 * */
	List<AiRun> findByTicket(Ticket ticket);
	
	
	/*
	 * Get all AI runs triggered by a specific user
	 * */
	List<AiRun> findByUser(User user);

}

/* THis meethod privided by JPA repository. NO need to create se[arately 
 *save()
findById()
findAll()
deleteById()
existsById() 
 * */

