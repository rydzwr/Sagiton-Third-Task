package strategy;

import com.rydzwr.strategy.SavedViewsStrategy;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SavedViewsStrategyTest {

    @Test
    void shouldSendDavidView() throws IOException, NoSuchFieldException {
        //GIVEN
        SavedViewsStrategy strategy = new SavedViewsStrategy();
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        HttpServletResponse response = mock(HttpServletResponse.class);

        //WHEN
        when(response.getWriter()).thenReturn(writer);
        strategy.send(response, "david");
        String fooJsonResponse = "{\"value\":\"davidView\",\"content\":\"David here\"}";

        //THEN
        assertThat(stringWriter.toString(), equalTo(fooJsonResponse));
    }

    @Test
    void shouldSendHalView() throws IOException, NoSuchFieldException {
        //GIVEN
        SavedViewsStrategy strategy = new SavedViewsStrategy();
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        HttpServletResponse response = mock(HttpServletResponse.class);

        //WHEN
        when(response.getWriter()).thenReturn(writer);
        strategy.send(response, "hal");
        String fooJsonResponse = "{\"value\":\"halView\",\"content\":\"My mind is going. I can feel it\"}";

        //THEN
        assertThat(stringWriter.toString(), equalTo(fooJsonResponse));
    }
}
