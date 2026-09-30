package io.kebab.test;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class EventHandlerTest {
    @Test
    public void testEventHandler() {
        // Test code
        assertEquals("Test Message", EventHandler.handle(new Connection()));
    }
}