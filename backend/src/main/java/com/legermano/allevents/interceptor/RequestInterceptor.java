package com.legermano.allevents.interceptor;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;

public class RequestInterceptor implements HandlerInterceptor{
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //TODO: Carregar os valores de uma config
        String[] validAuths = {"frontend", "mobile"};

        // Quando é uma chamada preflight, não precisa validar o header de autorização
        if(request.getMethod().equals("OPTIONS")) {
            return true;
        }

        String authHeader = request.getHeader("Authorization");
        System.out.println(authHeader);

        if(authHeader == null) {
            response.setContentType("application/json");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("{\"Error\":\"HEADER Auth not present\"}");

            return false;
        }

        if(!Arrays.stream(validAuths).anyMatch(authHeader::equals)) {
            response.setContentType("application/json");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("{\"Error\":\"HEADER Auth not valid\"}");

            return false;
        }

        return true;
    }
}
