package ch.joil.joilchat.client2;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Created by bananatreedad on 07/05/16.
 */
public class ChatClient2 implements Runnable {

    private Socket socket = null;
    private Thread thread = null;
    private DataInputStream in = null;
    private DataOutputStream out = null;
    private ChatClientThread clientThread = null;

    public ChatClient2(String serverName, int serverPort) {
        System.out.println("Try to establish connection...");

        try {
            socket = new Socket(serverName, serverPort);
            System.out.println("Connected: " + socket);

            start();


        } catch (UnknownHostException e) {
            System.out.println("Unknown host: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Could not connect to server: " + e.getMessage());
        }

    }

    public void handle(String s) {
        if(s.equals("/bye")) {
            System.out.println("GOOD BYE. Press RETURN to exit...");
            stop();
        }
        else {
            System.out.println(s);
        }
    }

    @Override
    public void run() {
        while (thread != null) {
            try {
                // see https://docs.oracle.com/javase/7/docs/api/java/io/DataInputStream.html
                BufferedReader d
                        = new BufferedReader(new InputStreamReader(in));

                out.writeUTF(d.readLine());
                out.flush();
            } catch (IOException e) {
                System.out.println("Sending error: " + e.getMessage());
                stop();
            }

        }
    }

    private void start() throws IOException {
        in = new DataInputStream(new BufferedInputStream(System.in));
        out = new DataOutputStream(socket.getOutputStream());

        if (thread == null) {
            clientThread = new ChatClientThread(this, socket);

            thread = new Thread(this);
            thread.start();
        }
    }

    private void stop() {
        if (thread != null) {
            thread = null;
        }
        try {
            if (in != null) in.close();
            if (out != null) out.close();
            if (socket != null) socket.close();

        } catch (IOException e) {
            System.out.println("Closing error...");
        }

        clientThread.close();
        clientThread.halt();
    }

}
