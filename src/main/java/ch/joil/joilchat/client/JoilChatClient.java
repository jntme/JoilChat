
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
				//System.out.println("Username: ");
				

		//messaging
				String input = new String();
				boolean done = false;
				
				while(!done){
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

		System.out.println("Your Message: ");
   
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
