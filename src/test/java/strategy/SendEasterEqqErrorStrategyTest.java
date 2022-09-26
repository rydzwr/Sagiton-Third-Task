package strategy;

import com.rydzwr.strategy.SendEasterEggErrorStrategy;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import static org.mockito.Mockito.*;

public class SendEasterEqqErrorStrategyTest {
    @Test
    void shouldSend418Error() throws IOException {
        //GIVEN
        SendEasterEggErrorStrategy strategy = new SendEasterEggErrorStrategy();
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        HttpServletResponse response = mock(HttpServletResponse.class);

        //WHEN
        when(response.getWriter()).thenReturn(writer);
        strategy.send(response, "Johny");

        //THEN
        verify(response).sendError(418, "I am a Teapot. You tried to use a teapot to brew coffee.");
    }
}
