package com.example.demo.controller;

import com.example.demo.DTO.RedirectResponse;
import com.example.demo.DTO.UrlShortenerRequest;
import com.example.demo.DTO.UrlShortenerResponse;
import com.example.demo.DTO.UrlStatisticsResponse;
import com.example.demo.entity.Url;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import com.example.demo.service.URLShortenerService;
import com.example.demo.service.UrlStatisticsService;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class URLShortenerController {

    private final URLShortenerService urlShortenerService;

    private final UrlStatisticsService urlStatisticsService;

    @PostMapping("/shorten")
    public UrlShortenerResponse generateShortUrl(@RequestBody UrlShortenerRequest urlShortenerRequest){
        return urlShortenerService.generateShortUrl(urlShortenerRequest);
    }

    @GetMapping("/{shortCode}")
    public RedirectResponse redirectShortUrl(@PathVariable String shortCode){
        return urlShortenerService.redirectShortUrl(shortCode);
    }

    @GetMapping("/stats/{shortCode}")
    public UrlStatisticsResponse getUrlStatistics(@PathVariable String shortCode){
        return urlStatisticsService.getUrlStatistics(shortCode);
    }

    @GetMapping("/getAll")
        public List<Url> getAllUrls() {
        return urlShortenerService.getAllUrls();
    }

}
