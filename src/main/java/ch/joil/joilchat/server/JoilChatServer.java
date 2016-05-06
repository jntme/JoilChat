package ch.joil.joilchat.server;

import java.io.IOException;

/**
 * The Mainclass of the Chatserver.
 * <p>
 * Created by jnt on 05/05/16.
 */
public class JoilChatServer {

    public static void main(String[] args) throws IOException {
        if(args.length != 1) System.out.println("Usage: java JoilChatServer <port>");
        else {
            new ChatServer(Integer.parseInt(args[0])).start();
        }
    }
}
