package com.coinpurse.web.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests().requestMatchers("/login", "/register", "/purses", "/css/**", "/js/**")
                .permitAll()
                .and()
                .formLogin(form -> form
                                .loginPage("/login")
                                .defaultSuccessUrl("/purses")
                                .loginProcessingUrl("/login")
                                .failureUrl("/login?error=true")
                                .permitAll()
                        //TODO: Implement logout handling
                        //.and().logout(logout -> logout
                        //        .logoutSuccessUrl("/logout").permitAll())
                );
        return http.build();
    }
}
