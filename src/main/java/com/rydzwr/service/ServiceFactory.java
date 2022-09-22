package com.rydzwr.service;

import com.rydzwr.strategy.CommonNameStrategy;
import com.rydzwr.strategy.SavedViewsStrategy;
import com.rydzwr.strategy.SendErrorStrategy;
import com.rydzwr.strategy.SendMethodStrategy;

public class ServiceFactory {
    public SendMethodStrategy createService(String name) {
        if (name.equals("Johny")) {
            return new SendErrorStrategy();
        } else if (name.equals("hal") || name.equals("david")) {
            return new SavedViewsStrategy();
        } else {
            return new CommonNameStrategy();
        }
    }
}
