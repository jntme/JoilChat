package ch.joil.joilchat.server2;

import java.io.*;
import java.net.Socket;

/**
 * Created by bananatreedad on 06/05/16.
 * <p>
 * This Class is responsible to handle the single chat session.
 */
public class ChatServerThread extends Thread {
    private ChatServer2 chatServer = null;
    private Socket socket = null;
    private int ID = -1;
    private DataInputStream in = null;
    private DataOutputStream out = null;

    private volatile Thread blinker = null;

    public ChatServerThread(ChatServer2 chatServer2, Socket socket) {
        this.chatServer = chatServer2;
        this.socket = socket;
        this.ID = socket.getPort();
    }

    public int getID() {
        return this.ID;
    }

    @Override
    public void run() {
        blinker = currentThread();
        System.out.println("Server Thread " + ID + "running.");

        while (blinker != null) {
            try {
                chatServer.handle(ID, in.readUTF());
            } catch (IOException e) {
                System.err.println("Could not write to client #" + this.ID);
                halt();
            }
        }

    }

    public void halt() {
        close();
        this.blinker = null;
    }

    public void send(String s) {
        try {
            out.writeUTF(s);
            out.flush();

        } catch (IOException e) {
            System.out.println("Error while sending: " + e.getMessage());
            chatServer.remove(this.ID);
            halt();
        }
    }

    public void close() {
        try {
            if (socket != null) socket.close();
            if (in != null) in.close();
            if (out != null) out.close();
        } catch (IOException e) {
            System.out.println("Closing error...");
        }
    }

    public void open() {
        try {
            in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
            out = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
        } catch (IOException e) {
            System.out.println("Error on opening Streams: " + e.getMessage());
        }
    }
}
