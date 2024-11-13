package com.nordwestzap.weblab;

import com.nordwestzap.weblab.dao.AttemptRepository;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class ControllerServlet extends HttpServlet {

    @EJB
    private AttemptRepository attemptRepository;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        System.out.println("get req");
        request.setAttribute("Attempt-Repository", attemptRepository);
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        System.out.println("post req");
        request.getRequestDispatcher("/check").forward(request, response);
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Cookie sessionIdCookie = null;
        for (Cookie cookie : request.getCookies()) {
            if (cookie.getName().equals("JSESSIONID")) {
                sessionIdCookie = cookie;
                break;
            }
        }

        //TODO check cookie
        String sessionId = sessionIdCookie.getValue();

        attemptRepository.deleteUserAttempts(sessionId);

        response.setStatus(HttpServletResponse.SC_OK);
//        response.getWriter().write("Запрос DELETE успешно обработан.");
    }
}
