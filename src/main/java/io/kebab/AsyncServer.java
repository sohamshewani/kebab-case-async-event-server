package io.kebab;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class AsyncServer {
    private static final int PORT = 8080;
    private final Selector selector;
    private final ServerSocketChannel serverChannel;
    private final ConnectionManager connectionManager;
    private final ThreadPoolExecutor executor;

    public AsyncServer() throws IOException {
        this.selector = Selector.open();
        this.serverChannel = ServerSocketChannel.open();
        this.serverChannel.socket().bind(new InetSocketAddress(PORT));
        this.serverChannel.configureBlocking(false);
        this.serverChannel.register(selector, SelectionKey.OP_ACCEPT);
        this.connectionManager = new ConnectionManager(selector, connectionPool());
        this.executor = Executors.newFixedThreadPool(10);
    }

    public void start() throws IOException {
        while (true) {
            selector.select();
            Set<SelectionKey> selectedKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectedKeys.iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                iterator.remove();
                if (key.isAcceptable()) {
                    SocketChannel clientChannel = serverChannel.accept();
                    clientChannel.configureBlocking(false);
                    clientChannel.register(selector, SelectionKey.OP_READ);
                    connectionManager.addConnection(clientChannel);
                } else if (key.isReadable()) {
                    executor.execute(new EventHandler(key, connectionManager));
                }
            }
        }
    }

    public static ConnectionPool connectionPool() {
        return new ConnectionPool();
    }

    public static void main(String[] args) throws IOException {
        try (AsyncServer server = new AsyncServer()) {
            server.start();
        }
    }
}