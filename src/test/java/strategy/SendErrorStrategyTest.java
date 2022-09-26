package strategy;

import com.rydzwr.strategy.SendEasterEggErrorStrategy;
import com.rydzwr.strategy.SendErrorStrategy;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import static org.mockito.Mockito.*;

public class SendErrorStrategyTest {
    @Test
    void shouldSend418Error() throws IOException, NoSuchFieldException, IllegalAccessException {
        //GIVEN
        SendErrorStrategy strategy = new SendErrorStrategy();
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        HttpServletResponse response = mock(HttpServletResponse.class);

        //WHEN
        when(response.getWriter()).thenReturn(writer);
        strategy.send(response, null);

        //THEN
        verify(response).sendError(400, "Missing name parameter");
    }
}
