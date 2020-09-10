package com.example.serviceShuffle.services;

import com.example.serviceShuffle.beans.Log;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadLocalRandom;

@Service
@Slf4j
@RequiredArgsConstructor
public class ShuffleService {

    private final RestTemplate client;
    @Value("com.cloud.hostname")
    private final String HOSTNAME;
    @Value("com.cloud.uri")
    private final String URI;

    @Async
    public CompletableFuture<String> writeToLogService(int input) {
        log.info("sending request to Log Service");
        Log log = new Log("Requested shuffle array method ", input, LocalDateTime.now());
        String respond = client.postForObject("http://" + HOSTNAME + "/" + URI,log , String.class);
        return CompletableFuture.completedFuture(respond);
    }

    public String createAndShuffleList(int input) {
        if (input <= 1000 && input >= 1) {
            int[] result = new int[input];
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
