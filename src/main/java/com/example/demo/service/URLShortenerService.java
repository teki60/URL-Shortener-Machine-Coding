package com.example.demo.service;

import com.example.demo.DTO.RedirectResponse;
import com.example.demo.DTO.UrlShortenerRequest;
import com.example.demo.DTO.UrlShortenerResponse;
import com.example.demo.entity.Url;
import com.example.demo.exceptions.ShortCodeNotFoundException;
import com.example.demo.exceptions.ShortUrlExpiredException;
import com.example.demo.helper.Helper;
import lombok.Data;
import org.springframework.stereotype.Service;
import com.example.demo.repository.UrlRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Data
@Service
public class URLShortenerService {

    int shortCodeLength = 5;

    private Map<String,Url> urlRepository = UrlRepository.urlRepository;

    public String generateShortCode(){
        Random random = new Random();
        String charSet="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder generatedString= new StringBuilder();
        for(int i=0;i<shortCodeLength;i++){
            int randomNumber = random.nextInt(62);
            char ch = charSet.charAt(randomNumber);
            generatedString.append(ch);
        }
        return generatedString.toString();
    }

    private boolean validateShortCode(String shortCode) {

        LocalDateTime expiryAt = null;
        urlRepository = UrlRepository.urlRepository;
        if(urlRepository.containsKey(shortCode)){
            expiryAt = urlRepository.get(shortCode).getExpiryAt();
        }

        if(!urlRepository.containsKey(shortCode)){
            throw new ShortCodeNotFoundException("Short Code not found");
        }
        else if(expiryAt != null && expiryAt.isBefore(LocalDateTime.now())){
            throw new ShortUrlExpiredException("Short URL expired");
        }
        return true;
    }


    public UrlShortenerResponse generateShortUrl(UrlShortenerRequest urlShortenerRequest){

        String longUrl = urlShortenerRequest.getLongUrl();
        UrlShortenerResponse urlShortenerResponse;
        for(Url url: UrlRepository.getUrlRepository()){
            if(url.getLongUrl().equals(longUrl)){
                int newExpiryMinutes= urlShortenerRequest.getExpiryMinutes();
                LocalDateTime presentExpiryTime = url.getExpiryAt();
                url.setExpiryAt(presentExpiryTime.plusMinutes(newExpiryMinutes));
                urlShortenerResponse = Helper.convertToUrlShortenerResponse(url);
                return urlShortenerResponse;
            }
        }

        boolean found= true;

        String generatedShortCode="";
        while(found){
            generatedShortCode = generateShortCode();
            if(!UrlRepository.urlRepository.containsKey(generatedShortCode)){
                Url url = new Url(generatedShortCode,longUrl,LocalDateTime.now(),LocalDateTime.now().plusMinutes(urlShortenerRequest.getExpiryMinutes()),0);
                if(urlShortenerRequest.getExpiryMinutes()==0){
                    url.setExpiryAt(null);
                }
                UrlRepository.save(generatedShortCode,url);
                found=false;
            }
        }
        Url url = urlRepository.get(generatedShortCode);
        urlShortenerResponse = Helper.convertToUrlShortenerResponse(url);

        return urlShortenerResponse;
    }

    public RedirectResponse redirectShortUrl(String shortCode){

        RedirectResponse redirectResponse = new RedirectResponse();
        boolean isValid = validateShortCode(shortCode);
        if(isValid){
            Url url = urlRepository.get(shortCode);
            String longUrl = url.getLongUrl();
            int clickCount = url.getClickCount();
            url.setClickCount(clickCount+1);
            urlRepository.put(shortCode,url);
            redirectResponse.setLongUrl(longUrl);
        }

        return redirectResponse;
    }

    public List<Url> getAllUrls(){
        return UrlRepository.getUrlRepository();
    }


}
