package com.ltp.gradesubmission.security.filter;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.ltp.gradesubmission.exception.EntityNotFoundException;
import com.ltp.gradesubmission.utils.ResponseHandler;
import lombok.AllArgsConstructor;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@AllArgsConstructor
public class ExceptionHandlerFilter extends OncePerRequestFilter {
    private final ResponseHandler responseHandler;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {
        try {
            filterChain.doFilter(request, response);
        } catch (EntityNotFoundException exception) {
            responseHandler.notFound(response, "Username does not exist");
        } catch (JWTVerificationException exception) {
            responseHandler.forbidden(response, "Invalid jwt token");
        } catch (RuntimeException exception) {
            responseHandler.badRequest(response, "Bad request");
        }
    }
}

