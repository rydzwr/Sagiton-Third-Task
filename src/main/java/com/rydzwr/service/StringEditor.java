package com.rydzwr.service;

public class StringEditor {
    public String buildOutput(String name) {
        if (!NameValidator.validate(name)) {
            throw new IllegalArgumentException("Johny is not allowed name value");
        }
        else return "Hello " + name;
    }
}
