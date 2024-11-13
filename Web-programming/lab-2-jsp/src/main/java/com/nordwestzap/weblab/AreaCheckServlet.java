package com.nordwestzap.weblab;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nordwestzap.weblab.dao.AttemptRepository;
import com.nordwestzap.weblab.model.Attempt;
import com.nordwestzap.weblab.model.AttemptMapper;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;

public class AreaCheckServlet extends HttpServlet {

    @EJB
    private AttemptRepository attemptRepository;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String xStr = (String) request.getParameter("x");
        String yStr = (String) request.getParameter("y");
        String rStr = (String) request.getParameter("r");

        if (xStr == null || yStr == null || rStr == null) {
            response.setStatus(400);
            response.getWriter().print("Request must contain x, y, r parameters");
            return;
        }

        double x = 0;
        double y = 0;
        int r = 0;

        try {
            x = Double.parseDouble(xStr);
        } catch (NumberFormatException e) {
            response.setStatus(400);
            response.getWriter().println("x must be integer value");
        }

        try {
            y = Double.parseDouble(yStr);
        } catch (NumberFormatException e) {
            response.setStatus(400);
            response.getWriter().println("y must be double value");
        }

        try {
            r = Integer.parseInt(rStr);
        } catch (NumberFormatException e) {
            response.setStatus(400);
            response.getWriter().print("r must be integer value");
        }

        if (response.getStatus() == 400)
            return;

        if (!(x>=-7 && x<=7)) {
            response.setStatus(400);
            response.getWriter().println("x must be integer value of [-4, -3, -2, -1, 0, 1, 2, 3, 4] ");
        }

        if (!(y >= -7 && y <= 7)) {
            response.setStatus(400);
            response.getWriter().println("y must be double value of (-3; 5)");
        }

        if (!(List.of(0, 1, 2, 3, 4, 5).contains(r))) {
            response.setStatus(400);
            response.getWriter().println("r must be integer value of [1, 2, 3, 4, 5]");
        }

        if (response.getStatus() == 400)
            return;

        Cookie sessionIdCookie = null;
        for (Cookie cookie : request.getCookies()) {
            if (cookie.getName().equals("JSESSIONID")) {
                sessionIdCookie = cookie;
                break;
            }
        }
        String sessionId = sessionIdCookie.getValue();
        String attemptTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("u-M-d k-m-s"));
        Instant start = Instant.now();
        boolean isHit = checkHit(x, y, r);
        Instant end = Instant.now();
        long scriptDuration = Duration.between(start, end).toNanos();

        Attempt attempt = attemptRepository.addAttempt(new Attempt(sessionId, x, y, r, isHit, attemptTime, scriptDuration));

        ObjectMapper objectMapper = new ObjectMapper();
        response.setStatus(200);
        response.getWriter().print(objectMapper.writeValueAsString(AttemptMapper.toAttemptDto(attempt)));

    }

    private boolean checkHit(double x, double y, int r) {
        if ((x >= 0 && x <= r) && (y >= 0 && y <= r))
            return Math.pow(x, 2) + Math.pow(y, 2) <= Math.pow(r, 2);
        else if ((x <= 0 && x >= -r) && (y >= 0 && y <= (double) r / 2))
            return y <= (double) r / 2;
        else if (x < 0 && y < 0)
            return false;
        else if ((x >= 0 && x <= r) && (y <= 0 && y >= -r))
            return y >= x - r;
        else
            return false;
    }

}
