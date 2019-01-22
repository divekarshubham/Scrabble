package Scrabble;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class incomming extends JFrame implements Runnable {

	 static Socket sock ;
	 static ObjectInputStream istream ;
	 static ObjectOutputStream ostream;

	@Override
	public void run() {
		try{
		     ServerSocket sersock= new ServerSocket(3001);
		     Socket sock = sersock.accept();
		 	 ObjectOutputStream ostream =new ObjectOutputStream(sock.getOutputStream());
		     ObjectInputStream istream = new ObjectInputStream(sock.getInputStream());
		     while(true)
		    {
		     String	msg=(String)istream.readObject();
		     if(msg!=null)
		     {
		    	 int i=JOptionPane.showConfirmDialog(rootPane, msg+" wants to play");
		    	 if(i==JOptionPane.YES_OPTION)
		    	 {
		    		ostream.writeObject(0);
		    		for(int i1=0;i1<15;i1++)
		            {
		                for(int j=0;j<15;j++)
		                    GD.b[i1][j]=' ';
		            }
		            SL.changeLetters(false);
		            new ScrabbleGame(GD.b,GD.getBG(),GD.getML(),msg,1);
		    		istream.close();
		    		ostream.flush();
		    		ostream.close();
		    		sock.close();
		    		
		    		break;
		    	 }
		    	 else
		    	 {
		    		 ostream.writeObject(1);
		    	 }
		     }}
		     }
		     catch (Exception e) {
				System.out.println("E hai"+e);
			}
		
	}


}
