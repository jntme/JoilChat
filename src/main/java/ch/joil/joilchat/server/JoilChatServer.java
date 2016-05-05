package ch.joil.joilchat.server;

import java.io.IOException;

/**
 * The Mainclass of the Chatserver.
 * <p>
 * Created by jnt on 05/05/16.
 */
public class JoilChatServer {

    public static void main(String[] args) throws IOException {
        System.out.println("Server started.");

        GreetingServer server = new GreetingServer(7777);
        server.run();

    }

}
