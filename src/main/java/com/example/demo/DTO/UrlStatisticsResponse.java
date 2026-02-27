package com.example.demo.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UrlStatisticsResponse {

    private String shortCode;

    private String longUrl;

    private LocalDateTime createdAt;

    private LocalDateTime expiryAt;

    private int clickCount;

    private boolean isExpired;

}
