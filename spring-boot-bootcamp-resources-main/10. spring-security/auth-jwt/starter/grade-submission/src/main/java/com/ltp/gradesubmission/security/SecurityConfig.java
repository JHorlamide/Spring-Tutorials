package com.ltp.gradesubmission.security;


import com.ltp.gradesubmission.security.filter.AuthenticationFilter;
import com.ltp.gradesubmission.security.filter.ExceptionHandlerFilter;
import com.ltp.gradesubmission.security.filter.JWTAuthorizationFilter;
import com.ltp.gradesubmission.security.manager.CustomAuthenticationManager;
import com.ltp.gradesubmission.utils.ResponseHandler;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@AllArgsConstructor
public class SecurityConfig {
    private CustomAuthenticationManager authenticationManager;
    private ResponseHandler responseHandler;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        var exceptionHandlerFilter = new ExceptionHandlerFilter(responseHandler);
        var authenticationFilter = new AuthenticationFilter(authenticationManager, responseHandler);
        authenticationFilter.setFilterProcessesUrl(SecurityConstants.AUTH_ENDPOINT);

        http
                .headers().frameOptions().disable() // New Line: the h2 console runs on a "frame". By default, Spring Security prevents rendering within an iframe. This line disables its prevention.
                .and()
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/h2/**").permitAll() // New Line: allows us to access the h2 console without the need to authenticate. ' ** '  instead of ' * ' because multiple path levels will follow /h2.
                .antMatchers(HttpMethod.POST, SecurityConstants.REGISTER_PATH).permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilterBefore(exceptionHandlerFilter, AuthenticationFilter.class)
                .addFilter(authenticationFilter)
                .addFilterAfter(new JWTAuthorizationFilter(), AuthenticationFilter.class)
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        return http.build();
    }
}