package com.elearningweb.admin.security;


import com.elearningweb.admin.jwt.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
@EnableGlobalAuthentication
@EnableMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
public class SecurityConfig {
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .cors()
                .and()
                .csrf().disable().sessionManagement().sessionCreationPolicy(STATELESS)
                .and()
                .authorizeHttpRequests((authorize) ->
                        authorize
                                .requestMatchers(HttpMethod.OPTIONS, "/admin/exams/*").hasAnyAuthority("ROLE_ADMIN")
                                .requestMatchers(HttpMethod.OPTIONS, "/account/*").hasAnyAuthority("ROLE_ADMIN")
                                .requestMatchers(HttpMethod.OPTIONS, "/tips/post/*").hasAnyAuthority("ROLE_ADMIN")
                                .requestMatchers(HttpMethod.OPTIONS, "/tips/comments/*").permitAll()
                                .requestMatchers(HttpMethod.OPTIONS, "/tips/file/*").permitAll()
                                .requestMatchers(HttpMethod.GET, "/user/exams/*").permitAll()
                                .requestMatchers("/api/auth/*").permitAll()
                                .anyRequest().permitAll()
                                .and()
                                .authenticationProvider(authenticationProvider)
                                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                );
        return http.build();
    }
}
