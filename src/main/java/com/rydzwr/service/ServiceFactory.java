package com.rydzwr.service;

import com.rydzwr.strategy.CommonNameStrategy;
import com.rydzwr.strategy.SavedViewsStrategy;
import com.rydzwr.strategy.SendErrorStrategy;
import com.rydzwr.strategy.SendMethodStrategy;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

import static java.util.Arrays.asList;

public class ServiceFactory {
    
    private static List<SendMethodStrategy> strategyList = asList(
            new SendErrorStrategy(),
            new SavedViewsStrategy()
    );
    
    public SendMethodStrategy createService(String name) {
        if (name == null) {
            return new SendErrorStrategy() {
                public void send(HttpServletResponse response, String name) throws IOException {
                    response.sendError(400, "Missing name parameter");
                }

                public boolean applies(String name) {
                    return true;
                }
            };
        }
        return strategyList
                .stream()
                .filter(s -> s.applies(name))
                .findFirst()
                .orElse(new CommonNameStrategy());
    }
}
