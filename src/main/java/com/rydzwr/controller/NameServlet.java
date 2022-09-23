package com.rydzwr.controller;

import com.rydzwr.service.ServiceFactory;
import com.rydzwr.strategy.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.*;
import org.json.JSONObject;

import java.io.IOException;
import java.util.stream.Collectors;

@WebServlet(name = "NameServlet", value = "/api/v1/validator")
public class NameServlet extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String body = request.getReader().lines().collect(Collectors.joining("\n"));
        RequestObjectWrapper r = new RequestObjectWrapper(body);

        try {
            ServiceFactory factory = new ServiceFactory();
            SendMethodStrategy service = factory.createService(r.getName());
            response.setContentType("application/json");
            service.send(response, r.getName());
        } catch (Exception e) {
            response.sendError(500, "Internal Server Error!");
        }
    }

    public static class RequestObjectWrapper {
        private String value;
        private String content;

        private RequestObjectWrapper(String stringJSON) {
            this.value = new JSONObject(stringJSON).getString("value");
        }

        public RequestObjectWrapper() {
        }

        public String getName() {
            return value;
        }

        public void setName(String name) {
            this.value = name;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getContent() {
            return content;
        }

        public String toJSON() {
            return new JSONObject().put("value", value).put("content", content).toString();
        }
    }
}
