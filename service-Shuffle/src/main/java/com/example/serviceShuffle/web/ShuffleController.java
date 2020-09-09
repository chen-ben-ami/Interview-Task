package com.example.serviceShuffle.web;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

@RestController
@RequiredArgsConstructor
public class ShuffleController {

    private final RestTemplate client;

    @HystrixCommand(fallbackMethod = "fallback")
    @GetMapping("shuffle")
    public String shuffle(@RequestParam int num) {
//        client.getForObject("http://service-log/log", String.class);
        return createAndShuffleList(num);
//        return "hey";

    }

    public String createAndShuffleList(int max) {
        if (max <=1000 && max >=1) {
            int[] result = new int[max];
            for (int i = 0; i < result.length; i++) {
                result[i] = i+1;
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

    public String fallback(int num) {
        return num+" is not a valid number. Please pick a number between 1 to 1000";
    }

}
