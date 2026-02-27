package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Url {

    private String shortCode;

    private String longUrl;

    private LocalDateTime createdAt;

    private LocalDateTime expiryAt;

    private int clickCount;

}
