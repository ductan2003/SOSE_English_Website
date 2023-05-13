package com.elearningweb.admin.config;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
        import org.springframework.context.annotation.Bean;
        import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
        import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
    @Value("${allowed.origin}")
    private String allowedOrigin;
    @Bean
    public WebMvcConfigurer getCorsConfiguration(){
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins(allowedOrigin)
                        .allowedMethods("GET","POST", "PUT", "DELETE")
                        .allowedHeaders("*");
            }
        };
    }
}