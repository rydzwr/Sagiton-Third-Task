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

        String name = extractNameFromJson(request);

        response.setContentType("application/json");
        PrintWriter out = response.getWriter();

        if (name.equals("Johny")) {
            SendingService service = createService(new SendErrorStrategy());
            service.send(response);
        }

        if (name.equals("hal") || name.equals("david")) {
            SendingService service = createService(new SavedViewsStrategy());
            service.send(out, response, name);

        } else {
            SendingService service = createService(new CommonNameStrategy());
            service.send(out, response, name);
        }
    }

    private String extractNameFromJson(HttpServletRequest request) throws IOException {
        String body = request.getReader().lines().collect(Collectors.joining("\n"));
        JSONObject jsonObj = new JSONObject(body);
        return jsonObj.getString("value");
    }

    private SendingService createService(SendMethodStrategy strategy) {
        return new SendingService(strategy);
    }
}
