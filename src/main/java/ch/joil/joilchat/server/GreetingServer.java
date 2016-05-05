package ch.joil.joilchat.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by bananatreedad on 05/05/16.
 */
class GreetingServer extends Thread {

    private ServerSocket serverSocket;
    private Socket socket;

    public GreetingServer(int port) throws IOException {
        serverSocket = new ServerSocket(port);
    }

    @Override
    public void run() {

        while (true) {
            DataOutputStream streamOut;
            DataInputStream streamIn;

            try {
                System.out.println("Waiting for client on port " + serverSocket.getLocalPort() + "...");
                socket = serverSocket.accept();

                System.out.println("Just connected to " + socket.getRemoteSocketAddress() + ".");

                streamOut = new DataOutputStream(socket.getOutputStream());
                streamIn = new DataInputStream(socket.getInputStream());

                streamOut.writeUTF("Write something and hit enter.\n");
                String input = streamIn.readUTF();

                String reversedInput = StringUtil.reverseString(input);

                streamOut.writeUTF("Reversed version of what you have written:");
                streamOut.writeUTF(reversedInput);

            } catch (IOException ex) {
                ex.printStackTrace();

            } finally {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
