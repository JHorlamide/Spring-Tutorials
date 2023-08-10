package com.ltp.gradesubmission.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@AllArgsConstructor
public class ResponseHandler {
    private final ObjectMapper objectMapper;

    public void successResponse(HttpServletResponse response, String message) throws IOException {
        Response responseBody = new Response(message, "Success");
        sendResponse(response, responseBody, HttpStatus.OK);
    }

    public void badRequest(HttpServletResponse response, String message) throws IOException {
        Response responseBody = new Response(message, "Failure");
        sendResponse(response, responseBody, HttpStatus.BAD_REQUEST);
    }

    public void notFound(HttpServletResponse response, String message) throws IOException {
        Response responseBody = new Response(message, "Failure");
        sendResponse(response, responseBody, HttpStatus.NOT_FOUND);
    }

    public void forbidden(HttpServletResponse response, String message) throws IOException {
        Response responseBody = new Response(message, "Failure");
        sendResponse(response, responseBody, HttpStatus.FORBIDDEN);
    }

    public void unAuthorize(HttpServletResponse response, String message) throws IOException {
        Response responseBody = new Response(message, "Failure");
        sendResponse(response, responseBody, HttpStatus.UNAUTHORIZED);
    }

    private void sendResponse(HttpServletResponse response, Response responseBody, HttpStatus httpStatus) throws IOException {
        response.setStatus(httpStatus.value());
        response.setContentType("application/json");
        response.getWriter().write(convertObjectToJson(responseBody));
        response.getWriter().flush();
    }

    private String convertObjectToJson(Object object) throws JsonProcessingException {
        return objectMapper.writeValueAsString(object);
    }
}

@Getter
@Setter
class Response {
    private String message;
    private String status;

    public Response(String message, String status) {
        this.message = message;
        this.status = status;
    }
}