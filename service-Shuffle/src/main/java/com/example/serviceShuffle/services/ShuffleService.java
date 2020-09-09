package com.example.serviceShuffle.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadLocalRandom;

@Service
@Slf4j
@RequiredArgsConstructor
public class ShuffleService {

    private final RestTemplate client;

    @Async
    public CompletableFuture<String> writeToLogService() {
        log.info("sending request to Log Service");
        String message = client.getForObject("http://service-log/log", String.class);
        return CompletableFuture.completedFuture(message);
    }

    public String createAndShuffleList(int max) {
        if (max <= 1000 && max >= 1) {
            int[] result = new int[max];
            for (int i = 0; i < result.length; i++) {
                result[i] = i + 1;
            }
            Random random = ThreadLocalRandom.current();
            for (int i = result.length - 1; i > 0; i--) {
                int index = random.nextInt(i + 1);
                int a = result[index];
                result[index] = result[i];
                result[i] = a;
            }
            return Arrays.toString(result);
        } else return "Please pick a number between 1 to 1000";
    }
}
