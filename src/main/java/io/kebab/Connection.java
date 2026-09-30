package io.kebab;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class Connection {
    private final SocketChannel channel;
    private final ConnectionPool connectionPool;

    public Connection(SocketChannel channel, ConnectionPool connectionPool) {
        this.channel = channel;
        this.connectionPool = connectionPool;
    }

    public void send(String message) throws IOException {
        ByteBuffer buffer = ByteBuffer.wrap(message.getBytes());
        channel.write(buffer);
    }
}