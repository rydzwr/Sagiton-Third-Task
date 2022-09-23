package com.rydzwr.strategy;

import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class SendErrorStrategy implements SendMethodStrategy {
    @Override
    public void send(HttpServletResponse response, String name) throws IOException, NoSuchFieldException, IllegalAccessException {
        response.sendError(400, "Missing name parameter");
    }

    @Override
    public boolean applies(String name) {
        return name == null;
    }
}
