package com.iconpln.kompor_induksi_Backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig {
    public static final String[] allowedHeaders = {
            "X-Requested-With",
            "Content-Type",
            "Authorization",
            "App-Source",
            "Origin",
            "Accept",
            "Access-Control-Request-Method",
            "Access-Control-Request-Headers"
    };

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedMethods("*")
                        .allowedOrigins("*")
                        .allowCredentials(true)
                        .maxAge(3600)
                        .allowedHeaders(allowedHeaders);
            }
        };
    }
}
