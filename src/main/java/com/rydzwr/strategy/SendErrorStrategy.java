package com.rydzwr.strategy;

import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

public class SendErrorStrategy implements SendMethodStrategy {
    @Override
    public void send(HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        response.sendError(418, "I am a Teapot. You tried to use a teapot to brew coffee.");
    }

    @Override
    public void send(HttpServletResponse response, PrintWriter out, String name) throws IOException {

    }
}
