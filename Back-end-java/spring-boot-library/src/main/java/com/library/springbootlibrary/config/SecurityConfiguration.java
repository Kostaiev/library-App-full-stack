package com.library.springbootlibrary.config;
import com.okta.spring.boot.oauth.Okta;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.accept.ContentNegotiationStrategy;
import org.springframework.web.accept.HeaderContentNegotiationStrategy;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@CrossOrigin("http://localhost:3000")
@Configuration
@EnableMethodSecurity
public class SecurityConfiguration {

        @Bean
        public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {


                // Disable cross site request forgery
                http.csrf(csrf -> csrf.disable())

                        .cors(cors -> {
                                cors.configurationSource(corsConfigurationSource());
                        })

                        // Protect endpoints at /api/<type>/secure
                        .authorizeHttpRequests(auth -> auth
                                .requestMatchers(HttpMethod.GET, "/api/books/**").permitAll()
                                .requestMatchers(HttpMethod.GET, "/api/reviews/**").permitAll()
                                .requestMatchers(HttpMethod.OPTIONS,"/api/books/secure/**").authenticated())
                        .oauth2ResourceServer((oauth2) -> oauth2
                                .jwt(Customizer.withDefaults())
                        )

                        // Add content negotiation strategy
                        .setSharedObject(ContentNegotiationStrategy.class,
                                new HeaderContentNegotiationStrategy());

                Okta.configureResourceServer401ResponseBody(http);
                return http.build();
        }

        @Bean
        public CorsConfigurationSource corsConfigurationSource() {
                CorsConfiguration configuration = new CorsConfiguration();
                configuration.addAllowedOrigin("http://localhost:3000");
                configuration.addAllowedMethod("*");
                configuration.addAllowedMethod("GET");
                configuration.addAllowedHeader("*");
                configuration.setAllowCredentials(true);
                UrlBasedCorsConfigurationSource source = new
                        UrlBasedCorsConfigurationSource();
                source.registerCorsConfiguration("/**", configuration);
                return source;
        }


}