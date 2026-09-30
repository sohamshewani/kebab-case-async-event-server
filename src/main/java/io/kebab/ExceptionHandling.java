package io.kebab;

import java.io.IOException;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;

public class ExceptionHandling {
    public static void handleException(SelectionKey key, SocketChannel channel, ExceptionHandling exceptionHandling) {
        try {
            if (key.isValid() && key.channel() instanceof SocketChannel) {
                if (channel != null && channel != null && channel.isConnected()) {
                    channel.close();
                } else if (channel != null) {
                    channel.close();
                }
            } else {
                if (key.channel() != null) {
                    key.channel().close();
                }
            }
        } catch (IOException e) {
            if (e instanceof ClosedChannelException) {
                return;
            }
            e.printStackTrace();
        }
    }
}