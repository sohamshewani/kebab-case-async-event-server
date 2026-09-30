package io.kebab;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ConnectionHandler {
    private final ExecutorService executor;

    public ConnectionHandler() {
        this.executor = Executors.newFixedThreadPool(10);
    }

    public void handle(Connection connection) {
        executor.submit(() -> {
            // handle connection
        });
    }
}