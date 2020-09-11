package com.example.gateway.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FallbackController {
	
	@GetMapping("fallback/service-shuffle")
	public ResponseEntity<String> shuffleServiceFallback(Throwable throwable) {
		return ResponseEntity.status(500).body("Gateway : Shuffle Service is not available");
	}
	
	@GetMapping("fallback/service-log")
	public ResponseEntity<String> logServiceFallback(Throwable throwable) {
		return ResponseEntity.status(500).body("Gateway : Log Service is not available");
	}
	
}
