package com.desirArman.tickets.config;

import com.desirArman.tickets.filters.UserProvisioningFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.server.resource.web.authentication.BearerTokenAuthenticationFilter;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(
            HttpSecurity http,
            UserProvisioningFilter userProvisioningFilter
    ) throws Exception{
        http       // Every endpoint requirest authentication
                .authorizeHttpRequests(
                        authorize -> authorize.anyRequest().authenticated())

                // Usually disabled for REST APIs, as they are stateless and uses JWT instead of cookies
                .csrf(csrf -> csrf.disable())

                // Tells Spring security not to create HTTP Sessions as eavery req will use its own JWT
                .sessionManagement( session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                // It tells SPRING BOOT that this application is an Oauth2 resource server. Expects Bearer JWT token, validate then adn authenticate users
                //When a request arrives, Spring Security automatically:
                //
                //Extracts the token.
                //Downloads Keycloak's public key (JWKS).
                //Verifies the JWT signature.
                //Checks expiration.
                //Creates an authenticated Authentication object.
                //Stores it in the SecurityContext.
                .oauth2ResourceServer( oauth2 -> oauth2.jwt(Customizer.withDefaults()))

                // This adds your custon filter after the JWT has already been validated
                .addFilterAfter(userProvisioningFilter, BearerTokenAuthenticationFilter.class);

        return http.build();
    }
}
