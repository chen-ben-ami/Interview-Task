package com.example.serviceLog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@EnableCircuitBreaker
public class ServiceLogApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceLogApplication.class, args);
		System.out.println("Log Service is running...");
	}

}
