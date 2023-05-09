package com.festival.back.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer{
    // # CROS (cros -Origins Resource Sharing) 정책
    // ? 다른 출처의 자원을 공유할수있도록 설정 하는 권한 정책
    @Override
    public void addCorsMappings(CorsRegistry corsregistry) {
        corsregistry.addMapping("/**")
        .allowedOrigins("*")
        .allowedMethods("*");
        
    }
    
}
