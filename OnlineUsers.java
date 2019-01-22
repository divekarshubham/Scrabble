package Scrabble;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;

public class OnlineUsers extends JFrame{
	JPanel[] p=new JPanel[20];
	JButton[] b=new JButton[20];
	JLabel[] l=new JLabel[20];
	JPanel j=new JPanel();
	static ObjectOutputStream ostream;
	String[] ips;
	private void requestGame(String s){
		
       try{
    	
		Socket sock = new Socket(s,3001);
        ostream =new ObjectOutputStream(sock.getOutputStream());
        ObjectInputStream istream = new ObjectInputStream(sock.getInputStream());
        InetAddress ip=InetAddress.getLocalHost();	
		String sendmessage=ip.getHostAddress();
		ostream.writeObject(sendmessage);
		while(true)
        {
			int i=(int)istream.readObject();
                if(i==1)
                	{JOptionPane.showMessageDialog(rootPane, "Player Refused");
                	new OnlineUsers(ips);}
                else
                	{
                		JOptionPane.showMessageDialog(rootPane, "Player Accepted");
                		for(int i1=0;i1<15;i1++)
    		            {
    		                for(int j=0;j<15;j++)
    		                    GD.b[i1][j]=' ';
    		            }
    		            SL.changeLetters(false);
    		            new ScrabbleGame(GD.b,GD.getBG(),GD.getML(),s,2);
    		            sock.close();
    		            istream.close();
    		            ostream.close();    		            
                	}
                	
                	
               }
       }
       catch (Exception e) {
		e.printStackTrace();
	}
	}
	
OnlineUsers(String[] ipList)
{
	 super("Online Users");
	 ips=ipList;
	 int i=0;
     setSize(500,250);
     setResizable(true);
     setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     j.setLayout(new BoxLayout(j,BoxLayout.Y_AXIS));
     while(ipList[i]!=null)
     {
    	 p[i]=new JPanel();
    	 b[i]=new JButton("Play");
    	 l[i]=new JLabel(ipList[i]);
    	 p[i].add(l[i]);p[i].add(b[i]);
    	 j.add(p[i]);
    	 i++; 
     }
     add(j);
     setLocation(300,200);
     setVisible(true);
     incomming in=new incomming();
     Thread t=new Thread(in);
     t.start();
     
     b[0].addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				requestGame(ipList[0]);
				dispose();
			}
		});
}
}
