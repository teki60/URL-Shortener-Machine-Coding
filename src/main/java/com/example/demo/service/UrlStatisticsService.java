package com.example.demo.service;

import com.example.demo.DTO.UrlStatisticsResponse;
import com.example.demo.entity.Url;
import com.example.demo.helper.Helper;
import org.springframework.stereotype.Service;
import com.example.demo.repository.UrlRepository;

import java.util.Map;

@Service
public class UrlStatisticsService {

    private final Map<String, Url> urlRepository = UrlRepository.urlRepository;

    public UrlStatisticsResponse getUrlStatistics(String shortCode){
        
        UrlStatisticsResponse urlStatisticsResponse = null;
        
        if(urlRepository.containsKey(shortCode)){
            Url url = urlRepository.get(shortCode);
            urlStatisticsResponse = Helper.convertToUrlStatisticsResponse(url);
        }

        return urlStatisticsResponse;
    }

}
