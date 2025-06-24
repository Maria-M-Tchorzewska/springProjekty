package com.example.BookStoreFinalProject.exceptions;

import com.example.BookStoreFinalProject.dtos.Response;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.ServletException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class AccessRefusalHandler implements AccessDeniedHandler {

    private final ObjectMapper mapper;

    @Override
    public void handle(HttpServletRequest req,
                       HttpServletResponse res,
                       AccessDeniedException ex)
            throws IOException, ServletException {

        Response responseBody = Response.builder()
                .status(HttpStatus.FORBIDDEN.value())
                .message("Dostęp zabroniony: " + ex.getMessage())
                .build();

        res.setContentType("application/json");
        res.setStatus(HttpStatus.FORBIDDEN.value());
        res.getWriter().write(mapper.writeValueAsString(responseBody));
    }
}
