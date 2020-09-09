package com.example.gateway.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FallbackController {
	
	@GetMapping("fallback/service-shuffle")
	public String shuffleServiceFallback(Throwable throwable) {
		return "Gateway : Fallback Message - Shuffle Service is not available";
	}
	
	@GetMapping("fallback/service-log")
	public String logServiceFallback(Throwable throwable) {
		return "Gateway : Fallback Message - Log Service is not available";
	}
	
}
