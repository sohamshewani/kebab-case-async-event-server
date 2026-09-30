package io.kebab.util;

public class Utils {
    public static void validatePort(int port) {
        if (port <= 0 || port > 65535) {
            throw new IllegalArgumentException("Invalid port number");
        }
    }
}