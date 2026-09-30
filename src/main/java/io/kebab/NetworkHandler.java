package io.kebab;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.nio.channels.WritableByteChannel;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NetworkHandler implements Runnable {
    private final SelectionKey key;
    private final ConnectionManager connectionManager;

    public NetworkHandler(SelectionKey key, ConnectionManager connectionManager) {
        this.key = key;
        this.connectionManager = connectionManager;
    }

    @Override
    public void run() {
        try {
            SocketChannel channel = (SocketChannel) key.channel();
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            WritableByteChannel writableChannel = channel;
            writableChannel.write(buffer);
            // Handle data
            // This is a placeholder for the actual business logic
            System.out.println("[NetworkHandler] Data written to channel");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}