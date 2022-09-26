package controller;

import com.rydzwr.controller.NameServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.mockito.Mockito.*;

public class NameServletTest {
    private HttpServletRequest request;
    private HttpServletResponse response;

    @BeforeEach
    void init() {
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
    }

    @Test
    void shouldSendGivenNameAsJson() throws IOException {
        //GIVEN
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);

        String inputJson = "{\"value\":\"Foo\"}";
        Reader inputString = new StringReader(inputJson);
        BufferedReader reader = new BufferedReader(inputString);

        //WHEN
        when(response.getWriter()).thenReturn(writer);
        when(request.getReader()).thenReturn(reader);

        String fooJsonResponse = "{\"value\":\"Foo\"}";
        new NameServlet().doPost(request, response);

        //THEN
        assertThat(stringWriter.toString(), equalTo(fooJsonResponse));
    }

    @Test
    void shouldSendHalViewAndContent() throws IOException {
        //GIVEN
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);

        String inputJson = "{\"value\":\"hal\"}";
        Reader inputString = new StringReader(inputJson);
        BufferedReader reader = new BufferedReader(inputString);

        //WHEN
        when(response.getWriter()).thenReturn(writer);
        when(request.getReader()).thenReturn(reader);

        String fooJsonResponse = "{\"value\":\"halView\",\"content\":\"My mind is going. I can feel it\"}";
        new NameServlet().doPost(request, response);

        //THEN
        assertThat(stringWriter.toString(), equalTo(fooJsonResponse));
    }

    @Test
    void shouldSendDavidViewAndContent() throws IOException {
        //GIVEN
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);

        String inputJson = "{\"value\":\"david\"}";
        Reader inputString = new StringReader(inputJson);
        BufferedReader reader = new BufferedReader(inputString);

        //WHEN
        when(response.getWriter()).thenReturn(writer);
        when(request.getReader()).thenReturn(reader);

        String fooJsonResponse = "{\"value\":\"davidView\",\"content\":\"David here\"}";
        new NameServlet().doPost(request, response);

        //THEN
        assertThat(stringWriter.toString(), equalTo(fooJsonResponse));
    }

    @Test
    void shouldSendEasterEggError() throws IOException {
        //GIVEN
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);

        String inputJson = "{\"value\":\"Johny\"}";
        Reader inputString = new StringReader(inputJson);
        BufferedReader reader = new BufferedReader(inputString);

        //WHEN
        when(response.getWriter()).thenReturn(writer);
        when(request.getReader()).thenReturn(reader);

        new NameServlet().doPost(request, response);

        //THEN
        verify(response).sendError(418, "I am a Teapot. You tried to use a teapot to brew coffee.");
    }
}
