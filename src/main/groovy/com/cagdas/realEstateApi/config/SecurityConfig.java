package com.cagdas.realEstateApi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors() // CORS'u etkinleştir
                .configurationSource(corsConfigurationSource()) // CORS yapılandırmasını bağla
                .and()
                .csrf().disable() // CSRF korumasını devre dışı bırak
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.POST, "/api/**").authenticated() // POST işlemleri korunur
                        .requestMatchers(HttpMethod.PUT, "/api/**").authenticated() // PUT işlemleri korunur
                        .requestMatchers(HttpMethod.DELETE, "/api/**").authenticated() // DELETE işlemleri korunur
                        .anyRequest().permitAll() // Diğer tüm yollar (özellikle GET) serbest
                )
                .httpBasic(); // HTTP Basic Authentication kullan
        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*")); // Tüm origin'lere izin ver
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS")); // İzin verilen HTTP yöntemleri
        configuration.setAllowedHeaders(Arrays.asList("*")); // Her türlü header'a izin ver
        configuration.setAllowCredentials(false); // Kimlik bilgilerini devre dışı bırak

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration); // Tüm endpoint'lere uygula
        return source;
    }
}
