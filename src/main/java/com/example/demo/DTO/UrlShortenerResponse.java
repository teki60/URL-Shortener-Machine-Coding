package com.example.demo.DTO;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class UrlShortenerResponse {

    String shortCode;

    String shortUrl;

    LocalDateTime expriryAt;

}
