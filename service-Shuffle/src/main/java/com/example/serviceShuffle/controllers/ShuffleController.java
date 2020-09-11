package com.example.serviceShuffle.controllers;

import com.example.serviceShuffle.services.ShuffleService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadLocalRandom;

@RestController
@RequiredArgsConstructor
public class ShuffleController {

    private final ShuffleService shuffleService;

    @HystrixCommand(fallbackMethod = "fallback")
    @PostMapping("shuffle")
    public ResponseEntity<String> shuffle(@RequestParam int num) {
        CompletableFuture<String> message = shuffleService.writeToLogService(num);
        CompletableFuture.allOf(message);
        return ResponseEntity.ok(shuffleService.createAndShuffleList(num));
    }

    public ResponseEntity<String> fallback(int num) {
        return ResponseEntity.badRequest().body(num + "is not a valid number. Please pick a number between 1 to 1000\"");
    }


}
