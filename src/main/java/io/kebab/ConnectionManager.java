package io.kebab;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.locks.ReentrantLock;

public class ConnectionManager {
    private final ConcurrentHashMap<SocketChannel, Connection> connections;
    private final CopyOnWriteArraySet<Connection> connectionSet;
    private final ReentrantLock lock;

    public ConnectionManager(Selector selector, ConnectionPool connectionPool) {
        this.connections = new ConcurrentHashMap<>();
        this.connectionSet = new CopyOnWriteArraySet<>();
        this.lock = new ReentrantLock();
    }

    public void addConnection(SocketChannel channel) {
        lock.lock();
        try {
            Connection connection = new Connection(channel, connectionPool);
            connections.put(channel, connection);
            connectionSet.add(connection);
        } finally {
            lock.unlock();
        }
    }

    public void removeConnection(SocketChannel channel) {
        lock.lock();
        try {
            connections.remove(channel);
            connectionSet.remove(connections.get(channel));
        } finally {
            lock.unlock();
        }
    }

    public Set<Connection> getConnectionSet() {
        return connectionSet;
    }
}