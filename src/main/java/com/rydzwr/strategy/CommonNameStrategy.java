package com.rydzwr.strategy;

import com.rydzwr.controller.NameServlet;
import com.rydzwr.model.SupportedNames;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

public class CommonNameStrategy implements SendMethodStrategy {
    @Override
    public void send(HttpServletResponse response, String name) throws IOException {
        PrintWriter out = response.getWriter();
        NameServlet.RequestObjectWrapper res = new NameServlet.RequestObjectWrapper();
        res.setName(name);
        out.print(res.toJSON());
    }

    @Override
    public boolean applies(String name) {
        return false;
    }
}
