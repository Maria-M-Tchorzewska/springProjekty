package com.example.BookStoreFinalProject.exceptions;

import com.example.BookStoreFinalProject.dtos.Response;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.ServletException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class UnauthorizedAccessEntryPoint implements AuthenticationEntryPoint {

    private final ObjectMapper jsonMapper;

    @Override
    public void commence(HttpServletRequest req,
                         HttpServletResponse res,
                         AuthenticationException authEx)
            throws IOException, ServletException {

        Response errorPayload = Response.builder()
                .status(HttpStatus.UNAUTHORIZED.value())
                .message("Nieautoryzowane żądanie: " + authEx.getMessage())
                .build();

        res.setContentType("application/json");
        res.setStatus(HttpStatus.UNAUTHORIZED.value());
        res.getWriter().write(jsonMapper.writeValueAsString(errorPayload));
    }
}
