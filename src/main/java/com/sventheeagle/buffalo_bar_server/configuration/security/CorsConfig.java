package com.sventheeagle.buffalo_bar_server.configuration.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();

        //HEADERS
        config.addAllowedOrigin("http://localhost:7070");
        config.addAllowedHeader("*");

        //CREDENTIAL SETTINGS
        config.setAllowCredentials(true);

        //METHODS
        config.addAllowedMethod(HttpMethod.GET);
        config.addAllowedMethod(HttpMethod.PUT);
        config.addAllowedMethod(HttpMethod.POST);
        config.addAllowedMethod(HttpMethod.PATCH);
        config.addAllowedMethod(HttpMethod.DELETE);
        config.addAllowedMethod(HttpMethod.OPTIONS);

        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
}
