package io.kebab;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class ConnectionPool {
    private final LinkedBlockingQueue<SocketChannel> pool;

    public ConnectionPool() {
        this.pool = new LinkedBlockingQueue<>();
    }

    public SocketChannel getConnection() throws InterruptedException {
        return pool.take();
    }

    public void releaseConnection(SocketChannel channel) {
        pool.offer(channel);
    }
}