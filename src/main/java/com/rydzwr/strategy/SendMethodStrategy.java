package com.rydzwr.strategy;

import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public interface SendMethodStrategy {
    void send(HttpServletResponse response, String name) throws IOException, NoSuchFieldException, IllegalAccessException;

    boolean applies(String name);
}
