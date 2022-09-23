package com.rydzwr.service;

import com.rydzwr.strategy.*;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

import static java.util.Arrays.asList;

public class ServiceFactory {
    
    private static List<SendMethodStrategy> strategyList = asList(
            new SendEasterEggErrorStrategy(),
            new SavedViewsStrategy()
    );
    
    public SendMethodStrategy createService(String name) {
        if (name == null) {
            return new SendErrorStrategy();
        }
        return strategyList
                .stream()
                .filter(s -> s.applies(name))
                .findFirst()
                .orElse(new CommonNameStrategy());
    }
}
