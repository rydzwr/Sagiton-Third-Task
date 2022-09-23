package com.rydzwr.strategy;

import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class SendErrorStrategy implements SendMethodStrategy {
    @Override
    public void send(HttpServletResponse response, String name) throws IOException {
        response.sendError(418, "I am a Teapot. You tried to use a teapot to brew coffee.");
    }

    @Override
    public boolean applies(String name) {
        return name.equals("Johny");
    }
}
