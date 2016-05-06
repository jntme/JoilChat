package ch.joil.joilchat.server;

import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * Created by bananatreedad on 06/05/16.
 */
public class ClientThread extends Thread {

    private Socket socket = null;
    private int socketPort = 0;
    private DataInputStream inputStream = null;
    private DataOutputStream outputStream = null;

    private String username = null;

    public ClientThread(Socket socket) {
        this.socket = socket;
        this.socketPort = socket.getPort();
    }

    @Override
    public void run() {
        System.out.printf("Started ClientThread with port " + socketPort);

        try {
            username = inputStream.readUTF();
            System.out.println("User named " + username + ".");

            while (true) {
                String input = inputStream.readUTF();
                System.out.printf(input);

                outputStream.writeUTF(username + " " + input);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void open() throws IOException {
        inputStream = new DataInputStream(socket.getInputStream());
        outputStream = new DataOutputStream(socket.getOutputStream());
    }

    public void close() throws IOException {
        if (socket != null) socket.close();
        if (inputStream != null) inputStream.close();
    }
}
