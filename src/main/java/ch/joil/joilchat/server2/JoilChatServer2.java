package ch.joil.joilchat.server2;

/**
 * Created by bananatreedad on 06/05/16.
 */
public class JoilChatServer2 {
    public static void main(String[] args) {
        if(args.length != 1) System.out.println("Usage: java JoilChatServer2 <port>");
        else new ChatServer2(Integer.parseInt(args[0]));
    }
}
