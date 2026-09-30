package io.kebab;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EventHandler implements Runnable {
    private final SelectionKey key;
    private final ConnectionManager connectionManager;

    public EventHandler(SelectionKey key, ConnectionManager connectionManager) {
        this.key = key;
        this.connectionManager = connectionManager;
    }

    @Override
    public void run() {
        try {
            SocketChannel channel = (SocketChannel) key.channel();
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            int bytesRead = channel.read(buffer);
            if (bytesRead == -1) {
                connectionManager.removeConnection(channel);
                return;
            }
            buffer.flip();
            byte[] data = new byte[buffer.remaining()];
            buffer.get(data);
            // Handle data
            // This is a placeholder for the actual business logic
            System.out.println(new String(data));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}