package com.sbaldass.movies_backend.security;


import com.sbaldass.movies_backend.config.BcryptEncoder;
import com.sbaldass.movies_backend.config.JwtAuthFilter;
import com.sbaldass.movies_backend.services.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private final CustomUserDetailsService userDetailsService;

    @Autowired
    private final JwtAuthFilter jwtAuthFilter;

    @Autowired
    private final BcryptEncoder bcryptEncoder;

    public SecurityConfig(CustomUserDetailsService userDetailsService, JwtAuthFilter jwtAuthFilter, BcryptEncoder bcryptEncoder) {
        this.userDetailsService = userDetailsService;
        this.jwtAuthFilter = jwtAuthFilter;
        this.bcryptEncoder = bcryptEncoder;
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeRequests((authorize) ->
                        authorize
                                .antMatchers(HttpMethod.GET, "/users").hasAuthority("ADMIN")
                                .antMatchers(HttpMethod.GET, "/users/{id}").permitAll()
                                .antMatchers(HttpMethod.POST, "/users").permitAll()
                                .antMatchers(HttpMethod.PUT, "/users/**").permitAll()
                                .antMatchers(HttpMethod.DELETE, "/users/**").permitAll()
                                .antMatchers(HttpMethod.POST, "/movies").hasAuthority("ADMIN")
                                .antMatchers(HttpMethod.PUT, "/movies/**").hasAuthority("ADMIN")
                                .antMatchers(HttpMethod.DELETE, "/movies/**").hasAuthority("ADMIN")
                                .antMatchers(HttpMethod.GET, "/movies/**").permitAll()
                                .antMatchers(HttpMethod.POST, "/tags").hasAuthority("ADMIN")
                                .antMatchers(HttpMethod.PUT, "/tags/**").hasAuthority("ADMIN")
                                .antMatchers(HttpMethod.DELETE, "/tags/**").hasAuthority("ADMIN")
                                .antMatchers(HttpMethod.GET, "/tags/**").permitAll()
                                .antMatchers("/auth/**").permitAll()
                                .antMatchers( "/v3/api-docs/**",
                                        "/swagger-ui/**",
                                        "/v2/api-docs/**",
                                        "/swagger-resources/**").permitAll()
                                .anyRequest().authenticated())
                .sessionManagement(httpSecuritySessionManagementConfigurer -> httpSecuritySessionManagementConfigurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .cors(httpSecurityCorsConfigurer -> new CorsConfiguration().applyPermitDefaultValues())
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http)
            throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(bcryptEncoder.passwordEncoder());
        return authenticationManagerBuilder.build();
    }
}
