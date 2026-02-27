package com.example.demo.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UrlShortenerRequest {

    String longUrl;

    int expiryMinutes;

}
