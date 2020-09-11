package com.example.serviceLog.controllers;

import com.example.serviceLog.beans.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class LogController {

    @PostMapping("log")
    public ResponseEntity<String> handleB(@RequestBody Log requestLog) {
        log.info("Shuffle array requested from micro-service Shuffle");
        log.info(requestLog.toString());
        return ResponseEntity.ok(requestLog.toString());
    }
}
