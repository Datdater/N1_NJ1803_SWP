package com.letitbee.diamondvaluationsystem.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

  @Override
  public void addCorsMappings(CorsRegistry registry) {
    registry.addMapping("/**")
            .allowedOrigins("http://localhost:3000",
                    "https://localhost:3000",
                    "https://localhost:8080",
                    "https://hntdiamond.store",
                    "https://console.hntdiamond.store",
                    "http://localhost:5173",
                    "http://localhost:5174",
                    "http://localhost:5175")
            .allowedMethods("GET", "POST", "PUT", "DELETE")
            .allowedHeaders("*")
            .allowCredentials(true);
  }

}