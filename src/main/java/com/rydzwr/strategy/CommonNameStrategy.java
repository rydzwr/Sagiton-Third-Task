package com.rydzwr.strategy;

import jakarta.servlet.http.HttpServletResponse;
import org.json.JSONObject;

import java.io.IOException;
import java.io.PrintWriter;

public class CommonNameStrategy implements SendMethodStrategy {
    @Override
    public void send(HttpServletResponse response) {

    }

    @Override
    public void send(HttpServletResponse response, PrintWriter out, String name) throws IOException {
        JSONObject res = new JSONObject();
        res.put("value", name);
        out.print(res);
    }
}
