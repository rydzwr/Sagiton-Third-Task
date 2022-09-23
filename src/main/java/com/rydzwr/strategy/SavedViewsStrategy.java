package com.rydzwr.strategy;

import com.rydzwr.controller.NameServlet;
import com.rydzwr.model.Names;
import com.rydzwr.model.SupportedNames;
import com.rydzwr.model.Target;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@SupportedNames(value = {"david", "hal"})
public class SavedViewsStrategy implements SendMethodStrategy {
    @Override
    public void send(HttpServletResponse response, String name) throws NoSuchFieldException, IOException {
            PrintWriter out = response.getWriter();
            Field field = Names.class.getDeclaredField(name);
            Target annotation = field.getAnnotation(Target.class);

            try {
                NameServlet.RequestObjectWrapper res = new NameServlet.RequestObjectWrapper();

                res.setName(annotation.value());
                res.setContent((String) field.get(null));
                out.print(res.toJSON());
            } catch (Exception e) {
                response.sendError(404, "Missing metadata!");
            }
    }

    @Override
    public boolean applies(String name) {
        SupportedNames annotation = this.getClass().getAnnotation(SupportedNames.class);
        List<String> savedViews = Arrays.stream(annotation.value()).collect(Collectors.toList());
        return savedViews.contains(name);
    }
}
