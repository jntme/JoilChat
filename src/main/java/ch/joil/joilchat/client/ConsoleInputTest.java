
import java.io.*;
import java.net.*;

public class ConsoleInputTest {

	public static string(){

		System.out.println("Your Message: ");
   
		try{
	  	  BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
	  	  String s = bufferRead.readLine();
	      
	  	 return s;
		}
		catch(IOException e){

			e.printStackTrace();
		}
	}
}