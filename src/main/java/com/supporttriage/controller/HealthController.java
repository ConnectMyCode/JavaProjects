package com.supporttriage.controller;

	
	import org.springframework.http.ResponseEntity; 
	import org.springframework.web.bind.annotation.GetMapping;
	import org.springframework.web.bind.annotation.RequestMapping;
	import org.springframework.web.bind.annotation.RestController;

	import javax.sql.DataSource;
	import java.util.Map;

	/**
	 * Health endpoint to verify app and DB are up.
	 */
	@RestController
	@RequestMapping("/api")
	
	
	public class HealthController{
	    private final DataSource dataSource;
	

	    public HealthController(DataSource dataSource) {
	        this.dataSource = dataSource;

	    }

	    @GetMapping("/health")
	    public ResponseEntity<Map<String, String>> health() {
	        try (var conn = dataSource.getConnection()) {
	            return ResponseEntity.ok(Map.of(
	                "status", "UP",
	                "database", "connected"
	            ));
	        } catch (Exception e) {
	            return ResponseEntity.status(503).body(Map.of(
	                "status", "DOWN",
	                "database", "error: " + e.getMessage()
	            ));
	        }
	    
	    }
	    
	}
	


