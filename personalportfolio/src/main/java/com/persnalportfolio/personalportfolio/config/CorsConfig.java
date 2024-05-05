package com.persnalportfolio.personalportfolio.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:3000") // Update with your frontend URL
                .allowedMethods("GET", "POST", "PUT", "DELETE") // Add any other HTTP methods you need
                .allowedHeaders("*") // Allow any headers
                .allowCredentials(true); // Allow credentials (cookies, authorization headers, etc.)
    }
}
