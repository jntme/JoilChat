package ch.joil.joilchat.client2;

import java.io.DataInput;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * Created by bananatreedad on 07/05/16.
 */
public class ChatClientThread extends Thread {
    ChatClient2 chatClient = null;
    Socket socket = null;
    DataInputStream input = null;

    private volatile Thread blinker = null;

    public ChatClientThread(ChatClient2 chatClient2, Socket socket) {
        this.chatClient = chatClient2;
        this.socket = socket;
        open();

        blinker = new Thread(this);
        start();

    }

    @Override
    public void run() {
        while(blinker != null) {
            try {
                chatClient.handle(input.readUTF());
            } catch (IOException e) {
                System.out.println("Listening error:" + e.getMessage());
                halt();
            }
        }
    }

    private void open() {
        try {
            input = new DataInputStream(socket.getInputStream());
        } catch (IOException e) {
            System.out.println("Error opening input stream: " + e.getMessage());
            this.halt();
        }
    }

    public void close() {
        try {
            if (input != null) input.close();
            if (socket != null) socket.close();
        } catch (IOException e) {
            System.out.println("Closing error...");
        }
    }

    public void halt() {
        close();
        blinker = null;
    }
}
