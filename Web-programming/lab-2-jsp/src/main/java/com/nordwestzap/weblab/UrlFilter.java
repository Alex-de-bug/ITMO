package com.nordwestzap.weblab;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class UrlFilter extends HttpFilter {

    @Override
    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String uri = request.getRequestURI();
        if (uri.endsWith("/lab") || uri.endsWith("/index.js")) {
            request.getServletContext().getNamedDispatcher("ControllerServlet").forward(request, response);
        } else if(uri.endsWith("/")){
            response.sendRedirect(uri+"lab");
        }
        else  {
//            System.out.println("доступ ограничен "+request.getRequestURI());
            response.setStatus(404);
        }
    }

}
