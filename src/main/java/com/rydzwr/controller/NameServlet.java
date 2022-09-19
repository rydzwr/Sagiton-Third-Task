package com.rydzwr.controller;

import com.rydzwr.model.Names;
import com.rydzwr.model.Target;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.*;
import org.json.JSONObject;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.util.stream.Collectors;

@WebServlet(name = "NameServlet", value = "/api/v1/validator")
public class NameServlet extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String body = request.getReader().lines().collect(Collectors.joining("\n"));
        JSONObject jsonObj = new JSONObject(body);
        PrintWriter out = response.getWriter();
        String name = jsonObj.getString("name");
        response.setContentType("application/json");

        if (name.equals("Johny")) {
            response.sendError(418, "I am a Teapot. You tried to use a teapot to brew coffee.");
            return;
        }

        if (name.equals("hal")) {
            try {
                Field field = Names.class.getDeclaredField("hal");
                Target annotation = field.getAnnotation(Target.class);
                if (annotation != null) {
                    JSONObject res = new JSONObject();
                    res.put("name", annotation.value());
                    out.print(res.toString());
                }
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }
        } else if (name.equals("david")) {
            try {
                Field field = Names.class.getDeclaredField("david");
                Target annotation = field.getAnnotation(Target.class);
                if (annotation != null) {
                    JSONObject res = new JSONObject();
                    res.put("name", annotation.value());
                    out.print(res.toString());
                }
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }
        } else {
            JSONObject res = new JSONObject();
            res.put("name", name);
            out.print(res.toString());
        }
    }
}
