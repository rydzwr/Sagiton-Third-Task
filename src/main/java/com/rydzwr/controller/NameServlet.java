package com.rydzwr.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.stream.Collectors;

@WebServlet(name = "NameServlet", value = "/api/v1/validator")
public class NameServlet extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String body = request.getReader().lines().collect(Collectors.joining("\n"));
        JSONObject jsonObj = new JSONObject(body);
        String name = jsonObj.getString("name");

        if (name.equals("Johny")) {
            response.sendError(418, "I am a Teapot. You tried to use a teapot to brew coffee.");
            return;
        }

        PrintWriter out = response.getWriter();
        response.setContentType("application/json");

        JSONObject res = new JSONObject();
        res.put("name", name);
        out.print(res.toString());
    }
}
