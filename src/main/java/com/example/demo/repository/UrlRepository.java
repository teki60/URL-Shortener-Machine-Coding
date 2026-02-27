package com.example.demo.repository;

import com.example.demo.entity.Url;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class UrlRepository {

    public static Map<String, Url> urlRepository = new HashMap<>();

    public static void save(String shortCode, Url url){
        urlRepository.put(shortCode,url);
    }

    public static List<Url> getUrlRepository() {
        return new ArrayList<>(urlRepository.values());
    }
}
