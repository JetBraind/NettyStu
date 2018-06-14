package com.nio.Server;

public class Server {
    private static int DEFAULT_PORT = 12345;
    private static Serverhandler handle;

    public static void start() {
        start(DEFAULT_PORT);
    }

    private synchronized static void start(int defaultPort) {
        if (handle != null) {
            handle.stop();
            handle = new Serverhandler(defaultPort);
            new Thread(handle, "Server").start();
        }
    }

    public static void main(String[] args) {
        start();
    }
}
