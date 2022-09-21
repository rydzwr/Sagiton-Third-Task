package com.rydzwr.strategy;

import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

public interface SendMethodStrategy {
    public void send(HttpServletResponse response) throws IOException;

    public void send(PrintWriter out, String name) throws IOException, NoSuchFieldException, IllegalAccessException;
}
