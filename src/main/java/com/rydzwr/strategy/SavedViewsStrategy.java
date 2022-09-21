package com.rydzwr.strategy;

import com.rydzwr.model.Names;
import com.rydzwr.model.Target;
import jakarta.servlet.http.HttpServletResponse;
import org.json.JSONObject;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Field;

public class SavedViewsStrategy implements SendMethodStrategy {
    @Override
    public void send(HttpServletResponse response) {

    }

    @Override
    public void send(HttpServletResponse response, PrintWriter out, String name) throws IOException {
        try {
            Field field = Names.class.getDeclaredField(name);
            Target annotation = field.getAnnotation(Target.class);
            if (annotation != null) {
                JSONObject res = new JSONObject();
                res.put("value", annotation.value());
                out.print(res);
            }
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }
}
