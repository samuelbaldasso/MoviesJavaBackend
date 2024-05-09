package com.sbaldass.movies_backend.models;

import lombok.Data;

@Data
public class LoginResponse {
    private String token;
    private long expiresIn;
}
