package ch.joil.joilchat.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by bananatreedad on 06/05/16.
 */
public class ChatServer implements Runnable {

    ServerSocket server = null;
    Thread thread = null;
    boolean running = false;

    public ChatServer(int port) {

        try {
            System.out.println("Listening on port " + port + " for connections.");
            server = new ServerSocket(port);
            start();

        } catch (IOException e) {
            System.out.println(e);
        }
    }

    @Override
    public void run() {
        running = true;
        while (running) {
            try {
                System.out.println("Waiting for a connection...");
                Socket socket = server.accept();
                addThread(socket);

            } catch (IOException e) {
                System.out.println("Acceptance error: " + e.getMessage());
            }
        }
    }

    private void addThread(Socket socket) {
        ClientThread newThread = new ClientThread(socket);

        try {
            newThread.open();
            newThread.start();

        } catch (IOException e) {
            System.out.println("Error opening Thread: " + e.getMessage());
        }
    }

    public void start() {
        if (thread == null) {
            thread = new Thread(this);
            thread.start();
        }

    }

    public void stop() {
        running = false;
    }
}
