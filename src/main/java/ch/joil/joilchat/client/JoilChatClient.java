package ch.joil.joilchat.client;
/**
 * Created by ilian on 05/05/16.
 */

import java.io.*;
import java.net.*;

public class JoilChatClient {

	public static void main(String[] args) {
		
		//create socket connection
			try{
				Socket socket=new Socket("jnt-mb.local", 7777);
				DataOutputStream out= new DataOutputStream(socket.getOutputStream());
				DataInputStream in= new DataInputStream(socket.getInputStream());
				

		//initialization
				String username = new String();
				System.out.print("Username: ");
				username = cmd_read();
				out.writeUTF("<"+username+">");
				

		//messaging
				String input = new String();
				boolean done = false;
				
				while(!done){
					System.out.print("<"+username+"> ");
					input = cmd_read();
					out.writeUTF(input);
					out.flush();

					String response = in.readUTF();
					System.out.println(response);

					done = response.equals(".bye");
				}

				out.close();
				in.close();
				socket.close();
			}
			catch (UnknownHostException e) {
				e.printStackTrace();
			}
			catch (IOException e) {
				e.printStackTrace();
			}

	}

	public static String cmd_read(){

   
			try{
	  		  BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
	  		  String s = bufferRead.readLine();
	      
	  		 return s;
			}
			catch(IOException e){
				e.printStackTrace();
				return "error";
			}
	}
}
