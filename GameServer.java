package Scrabble;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class GameServer extends Thread{
	 static Socket sock ;
	 static ObjectInputStream istream ;
	 static ObjectOutputStream ostream;
	
	@Override
	public void run() {
		try{
		     ServerSocket sersock= new ServerSocket(3002);
		     while(true)
		     {
			     Socket sock = sersock.accept();
			 	 ostream =new ObjectOutputStream(sock.getOutputStream());
			     istream = new ObjectInputStream(sock.getInputStream());
			      	 GD.b=(Character[][])istream.readObject();
				     ostream.writeObject(1);
				     GD.f=(int[])istream.readObject();
				     ostream.writeObject(1);
				     GD.score2=(int)istream.readObject();
				     SL.changeLetters(false);
                     ScrabbleGame.changeIt(GD.b,GD.getBG(),ScrabbleGame.myletters.getText());
				
				 ScrabbleGame.startPlaying();
				 sock.close();
				 ostream.close();
				 istream.close();
				 
		     }}
		catch(Exception e){
			e.printStackTrace();
		}
	}
}
