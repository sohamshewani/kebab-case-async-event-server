package io.kebab;

public class ServerConfig {
    private int port;
    private int maxConnections;

    public ServerConfig(int port, int maxConnections) {
        this.port = port;
        this.maxConnections = maxConnections;
    }

    public int getPort() {
        return port;
    }

    public int getMaxConnections() {
        return maxConnections;
    }
}