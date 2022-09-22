package com.rydzwr.controller;

import com.rydzwr.service.ServiceFactory;
import com.rydzwr.strategy.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.*;
import org.json.JSONObject;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.stream.Collectors;

@WebServlet(name = "NameServlet", value = "/api/v1/validator")
public class NameServlet extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String body = request.getReader().lines().collect(Collectors.joining("\n"));
        String name = new JSONObject(body).getString("value");

        try {
            ServiceFactory factory = new ServiceFactory();
            SendMethodStrategy service = factory.createService(name);
            response.setContentType("application/json");
            service.send(response, name);
        } catch (Exception e) {
            response.sendError(500, "Internal Server Error!");
        }
    }
}
