package com.example.demo.exceptions;

public class ShortUrlExpiredException extends RuntimeException {
    public ShortUrlExpiredException(String message) {
        super(message);
    }
}
