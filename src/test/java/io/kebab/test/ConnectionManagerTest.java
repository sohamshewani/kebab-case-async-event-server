package io.kebab.test;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class ConnectionManagerTest {
    @Test
    public void testConnectionManager() {
        // Test code
        assertEquals(10, ConnectionManager.MAX_CONNECTIONS);
    }
}