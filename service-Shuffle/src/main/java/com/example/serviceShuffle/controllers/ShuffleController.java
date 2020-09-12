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
import java.util.concurrent.*;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ShuffleController {

    private final ShuffleService shuffleService;
    private final Executor executor;

    @HystrixCommand(fallbackMethod = "fallback")
    @PostMapping("shuffle")
    public ResponseEntity<String> shuffle(@RequestParam int num) throws ExecutionException, InterruptedException {
        CompletableFuture.runAsync(() -> shuffleService.writeToLogService(num));
        log.info("Async call to log service that does not blocked by the I/O");
        return shuffleService.createAndShuffleList(num);

    }
    public ResponseEntity<String> fallback(int num) {
        return ResponseEntity.status(500).body("Something went wrong with Shuffle Service!");
    }


}
