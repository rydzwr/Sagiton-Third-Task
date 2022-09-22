package com.rydzwr.strategy;

import jakarta.servlet.http.HttpServletResponse;
import org.json.JSONObject;

import java.io.IOException;
import java.io.PrintWriter;

public class CommonNameStrategy implements SendMethodStrategy {
    @Override
    public void send(HttpServletResponse response, String name) throws IOException {
        PrintWriter out = response.getWriter();
        JSONObject res = new JSONObject();
        res.put("value", name);
        out.print(res);
    }
}
