package ch.joil.joilchat.server2;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by bananatreedad on 06/05/16.
 */
public class ChatServer2 implements Runnable {

    private ChatServerThread[] clients = new ChatServerThread[50];
    private int clientCount = 0;

    private Thread thread = null;
    private ServerSocket server = null;

    public ChatServer2(int port) {
        try {
            server = new ServerSocket(port);
            System.out.println("Binding port " + port + " on " + server.getLocalSocketAddress());
            System.out.println("Server started.");
            start();

        } catch (IOException e) {
            System.out.println("Error opening Socket: " + e.getMessage());
        }

    }

    @Override
    public void run() {
        while (thread != null) {
            try {
                System.out.println("Waiting for connection on port " + server.getLocalPort());
                addThread(server.accept());

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void addThread(Socket socket) {
        if(clientCount < clients.length) {
            System.out.println("Connection accepted: " + socket);
            clients[clientCount] = new ChatServerThread(this, socket);

            clients[clientCount].open();
            clients[clientCount].start();
            clientCount++;
        }
        else {
            System.out.println("Client Refused: Maximum count of connections reached (" + clients.length + ").");
            //// TODO: 06/05/16 add client informtion about not connection
        }
        System.out.println("Client accepted: " + socket.getRemoteSocketAddress() + ", handling on port " + socket.getLocalPort());
        ChatServerThread client = new ChatServerThread(this, socket);

        client.open();
        client.start();
    }

    private void start() {
        if (thread == null) {
            thread = new Thread(this);
            thread.start();
        }
    }

    private void stop() {
        if (thread != null) {
            thread = null;
        }
    }

    /**
     * Finds the clientCount[i] index matching the chatServerThread ID.
     *
     * @param ID
     * @return clientCount[i] index
     */
    private int findClient(int ID) {
        for (int i = 0; i < clientCount; i++)
            if (clients[i].getID() == ID)
                return i;
        return -1;
    }

    public synchronized void handle(int id, String s) {
        if (s.equals("/bye")) {
            clients[findClient(id)].send("/bye");
            remove(id);
        } else {
            for (int i = 0; i < clientCount; i++) {
                clients[i].send(id + ": " + s);
            }
        }
    }

    public synchronized void remove(int id) {
        int pos = findClient(id);
        if (pos >= 0) {
            ChatServerThread toTerminate = clients[pos];

            System.out.println("Terminating the client with the id " + id + ".");

            //// TODO: 06/05/16 study this complicated shit - atm I'm too tired
            if (pos < clientCount - 1)
                for (int i = pos + 1; i < clientCount; i++)
                    clients[i - 1] = clients[i];

            clientCount--;

            try {
                toTerminate.close();
            } catch (IOException e) {
                System.out.println("Could not close ClientServerThread: " + e.getMessage());
            }

            toTerminate.halt();
        }
    }
}
