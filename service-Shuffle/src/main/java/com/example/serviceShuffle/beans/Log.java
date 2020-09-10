package com.example.serviceShuffle.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Log {

    private String message;
    private int input;
    private LocalDateTime date;

    @Override
    public String toString() {
        return "Log{" +
                "message='" + message + '\'' +
                ", input=" + input +
                ", date=" + date +
                '}';
    }
}
