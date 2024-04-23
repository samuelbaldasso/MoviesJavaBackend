package com.sbaldass.movies_backend.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JwtToken {
    public String email;
    public String token;

}
