package com.rydzwr.controller;

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
        //TODO
        // implement NameValidator to avoid digits and other shit in name

        String name = extractNameFromJson(request);
        response.setContentType("application/json");

        try {
            SendMethodStrategy service = createService(name);
            PrintWriter out = response.getWriter();
            service.send(out, name);
        } catch (Exception e) {
            response.sendError(500, "Internal Server Error!");
        }
    }

    private String extractNameFromJson(HttpServletRequest request) throws IOException {
        String body = request.getReader().lines().collect(Collectors.joining("\n"));
        JSONObject jsonObj = new JSONObject(body);
        return jsonObj.getString("value");
    }

    private SendMethodStrategy createService(String name) {
        if (name.equals("Johny")) {
            return new SendErrorStrategy();
        } else if (name.equals("hal") || name.equals("david")) {
            return new SavedViewsStrategy();
        } else {
            return new CommonNameStrategy();
        }
    }
}
