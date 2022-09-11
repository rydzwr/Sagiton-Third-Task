package com.rydzwr.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "NameServlet", value = "/validator")
public class NameServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        doPost(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String name = request.getParameter("name");
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");

        if (name == null) {
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        } else if (name.equals("hal")) {
            request.getRequestDispatcher("/halView.jsp").forward(request, response);
        } else if (name.equals("david")) {
            request.getRequestDispatcher("/davidView.jsp").forward(request, response);
        }
        else {
            if (name.equals("Johny")) {
                response.sendError(418, "Johny is not allowed name value");
            } else {
                out.println("Hello " + name);
            }
        }
    }
}
