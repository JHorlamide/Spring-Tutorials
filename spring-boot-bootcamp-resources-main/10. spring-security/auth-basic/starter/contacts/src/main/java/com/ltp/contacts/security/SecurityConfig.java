package com.ltp.contacts.security;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@AllArgsConstructor
public class SecurityConfig {

    BCryptPasswordEncoder passwordEncoder;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // any http request that goes through our security filter chain
                // does not have the authority to perform whatever operation it's trying to
                // perform
                .csrf().disable() // Disables CSRF token
                .authorizeRequests()
                .antMatchers(HttpMethod.DELETE).hasRole("ADMIN")
                .antMatchers(HttpMethod.POST).hasAnyRole("ADMIN", "USER")
                .antMatchers(HttpMethod.GET).permitAll()
                .anyRequest().authenticated() // Any request needs to be authenticated
                .and()
                .httpBasic()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        
        return http.build();
    }

    @Bean
    public UserDetailsService userDetails() {
        UserDetails admin = User.builder()
                .username("admin")
                .roles("ADMIN")
                .password(passwordEncoder.encode("admin-pass"))
                .build();

        UserDetails user = User.builder()
                .username("user")
                .roles("USER")
                .password(passwordEncoder.encode("user-pass"))
                .build();

        return new InMemoryUserDetailsManager(admin, user);
    }
}
