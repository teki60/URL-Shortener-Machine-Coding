package com.example.demo.helper;

import com.example.demo.DTO.UrlShortenerResponse;
import com.example.demo.DTO.UrlStatisticsResponse;
import com.example.demo.entity.Url;

import java.time.LocalDateTime;

public class Helper {

    public static UrlShortenerResponse convertToUrlShortenerResponse(Url url){
        UrlShortenerResponse urlShortenerResponse = new UrlShortenerResponse();
        String shortUrl="http://localhost:8080/"+url.getShortCode();
        urlShortenerResponse.setShortCode(url.getShortCode());
        urlShortenerResponse.setShortUrl(shortUrl);
        urlShortenerResponse.setExpriryAt(url.getExpiryAt());
        return urlShortenerResponse;
    }

    public static UrlStatisticsResponse convertToUrlStatisticsResponse(Url url){

        LocalDateTime urlExpiryTime = url.getExpiryAt();

        UrlStatisticsResponse urlStatisticsResponse = new UrlStatisticsResponse();
        urlStatisticsResponse.setShortCode(url.getShortCode());
        urlStatisticsResponse.setLongUrl(url.getLongUrl());
        urlStatisticsResponse.setCreatedAt(url.getCreatedAt());
        urlStatisticsResponse.setExpiryAt(url.getExpiryAt());
        urlStatisticsResponse.setClickCount(url.getClickCount());
        urlStatisticsResponse.setExpired(urlExpiryTime.isBefore(LocalDateTime.now()));
        return urlStatisticsResponse;
    }

}
