package com.ltp.gradesubmission.security.manager;

import com.ltp.gradesubmission.entity.User;
import com.ltp.gradesubmission.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CustomAuthenticationManager implements AuthenticationManager {
    private UserService userService;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String incomingPassword = authentication.getCredentials().toString();
        String username = authentication.getName();

        User user = userService.getUser(username);

        if (!bCryptPasswordEncoder.matches(incomingPassword, user.getPassword())) {
            throw new BadCredentialsException("Invalid credential. Please try again");
        }

        return new UsernamePasswordAuthenticationToken(username, incomingPassword);
    }
}
