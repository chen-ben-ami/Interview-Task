package com.example.serviceShuffle.controllers;

import com.example.serviceShuffle.services.ShuffleService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadLocalRandom;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ShuffleController {

    private final ShuffleService shuffleService;

    @HystrixCommand(fallbackMethod = "fallback")
    @PostMapping("shuffle")
    public ResponseEntity<String> shuffle(@RequestParam int num) throws ExecutionException, InterruptedException {
        Future<String> future = shuffleService.writeToLogService(num);
        while (true) {
            if (future.isDone()) {
                log.info("Request to log server was accepted....");
                break;
            }
        }
        return shuffleService.createAndShuffleList(num);

    }

    public ResponseEntity<String> fallback(int num) {
        return ResponseEntity.status(500).body("Something went wrong with Shuffle Service!");
    }


}
