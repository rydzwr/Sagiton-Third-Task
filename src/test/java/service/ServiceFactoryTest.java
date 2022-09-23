package service;

import com.rydzwr.service.ServiceFactory;
import com.rydzwr.strategy.*;
import org.junit.jupiter.api.Test;

import java.io.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

public class ServiceFactoryTest {
    ServiceFactory factory = new ServiceFactory();

    @Test
    void shouldReturnCommonNameStrategy(){
        //GIVEN
        SendMethodStrategy toTest = factory.createService("foo");

        //WHEN + THEN
        assertEquals(toTest.getClass(), CommonNameStrategy.class);
    }

    @Test
    void shouldReturnSavedNameStrategyForHalName(){
        //GIVEN
        SendMethodStrategy toTest = factory.createService("hal");

        //WHEN + THEN
        assertEquals(toTest.getClass(), SavedViewsStrategy.class);
    }

    @Test
    void shouldReturnSavedNameStrategyForDavidName(){
        //GIVEN
        SendMethodStrategy toTest = factory.createService("david");

        //WHEN + THEN
        assertEquals(toTest.getClass(), SavedViewsStrategy.class);
    }

    @Test
    void shouldReturnSendEasterEggErrorStrategy(){
        //GIVEN
        SendMethodStrategy toTest = factory.createService("Johny");

        //WHEN + THEN
        assertEquals(toTest.getClass(), SendEasterEggErrorStrategy.class);
    }

    @Test
    void shouldSendErrorStrategy() throws IOException {
        //GIVEN
        SendMethodStrategy toTest = factory.createService(null);

        //WHEN + THEN
        assertEquals(SendErrorStrategy.class, toTest.getClass());
    }
}
