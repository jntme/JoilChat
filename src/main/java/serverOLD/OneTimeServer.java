package serverOLD;

import serverOLD.StringUtil;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by bananatreedad on 05/05/16.
 */
public class OneTimeServer {

    private Socket socket = null;
    private ServerSocket server = null;
    private DataInputStream streamIn = null;
    private DataOutputStream streamOut = null;

    public OneTimeServer(int port) throws IOException {
        System.out.println("Started OneTimeServer.");

        try {
            server = new ServerSocket(port);
            System.out.println("Listening on host " + server.getLocalSocketAddress() + "and port " + port);

            socket = server.accept();

            System.out.println("Accepted connection from " + socket.getRemoteSocketAddress() + ".");

            open();

            boolean done = false;

            while (!done) {
                try {

                    String s = streamIn.readUTF();

                    System.out.println(s);
                    streamOut.writeUTF(StringUtil.reverseString(s));

                    done = s.equals(".bye");

                } catch (IOException ex) {
                    done = true;
                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        close();

        System.out.println("Stopped OneTimeServer.");
    }

    private void close() throws IOException {
        if (socket != null) socket.close();
    }

    private void open() throws IOException {
        streamIn = new DataInputStream(socket.getInputStream());
        streamOut = new DataOutputStream(socket.getOutputStream());
    }

}
