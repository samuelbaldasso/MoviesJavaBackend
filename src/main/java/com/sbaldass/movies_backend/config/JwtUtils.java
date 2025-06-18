package com.sbaldass.movies_backend.config;

import com.sbaldass.movies_backend.domain.User;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.naming.AuthenticationException;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Component
public class JwtUtils {
    @Autowired
    private Environment environment;

    private final JwtParser jwtParser;

    private final String TOKEN_HEADER = "Authorization";
    private final String TOKEN_PREFIX = "Bearer ";

    @Autowired
    public JwtUtils(Environment environment) {
        this.environment = environment;
        this.jwtParser = Jwts.parser().setSigningKey(environment.getProperty("security.jwt.secret-key"));
    }

    public String createToken(User user) {
        Claims claims = Jwts.claims().setSubject(user.getEmail());
        claims.put("username", user.getUsername());
        Date tokenCreateTime = new Date();
        long accessTokenValidity = 60 * 60 * 1000;
        Date tokenValidity = new Date(tokenCreateTime.getTime() + TimeUnit.MINUTES.toMillis(accessTokenValidity));
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(tokenValidity)
                .signWith(SignatureAlgorithm.HS256, environment.getProperty("security.jwt.secret-key"))
                .compact();
    }

    private Claims parseJwtClaims(String token) {
        return jwtParser.parseClaimsJws(token).getBody();
    }

    public Claims resolveClaims(HttpServletRequest req) {
        try {
            String token = resolveToken(req);
            if (token != null) {
                return parseJwtClaims(token);
            }
            return null;
        } catch (ExpiredJwtException ex) {
            req.setAttribute("expired", ex.getMessage());
            throw ex;
        } catch (Exception ex) {
            req.setAttribute("invalid", ex.getMessage());
            throw ex;
        }
    }

    public String resolveToken(HttpServletRequest request) {

        String bearerToken = request.getHeader(TOKEN_HEADER);
        if (bearerToken != null && bearerToken.startsWith(TOKEN_PREFIX)) {
            return bearerToken.substring(TOKEN_PREFIX.length());
        }
        return null;
    }

    public boolean validateClaims(Claims claims) throws AuthenticationException {
        return claims.getExpiration().after(new Date());
    }


}
