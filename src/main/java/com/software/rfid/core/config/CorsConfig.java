package com.software.rfid.core.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Daniel Nacher
 * 2023-04-25
 */

@Configuration
public class CorsConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer(){
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/login")
                        .allowedOrigins("*")
                        .exposedHeaders("*");

                registry.addMapping("/api/**")
                        .allowedOrigins("http://localhost:4200")
                        .allowedOrigins("*")
                        .allowedMethods("*")
                        .exposedHeaders("*");
            }
        };
    }
}
