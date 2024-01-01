package com.sbaldass.movies_backend.config;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JwtToken {
    public String email;
    public String token;
}
