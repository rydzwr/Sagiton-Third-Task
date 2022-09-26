package strategy;

import com.rydzwr.strategy.CommonNameStrategy;

import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CommonNameStrategyTest {

    @Test
    void shouldSendGivenName() throws IOException {
        //GIVEN
        CommonNameStrategy strategy = new CommonNameStrategy();
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        HttpServletResponse response = mock(HttpServletResponse.class);

        //WHEN
        when(response.getWriter()).thenReturn(writer);
        strategy.send(response, "Foo");
        String fooJsonResponse = "{\"value\":\"Foo\"}";

        //THEN
        assertThat(stringWriter.toString(), equalTo(fooJsonResponse));
    }
}
