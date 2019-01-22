package Scrabble;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class mainWala extends JFrame{
	JPanel jp=new JPanel();
	static JTextField jt=new JTextField(20);
	
	mainWala()
	{
		super("Configure");
		setSize(500,250);
	    setResizable(true);
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JLabel l=new JLabel("Enter server ip address");
		JButton b=new JButton("Submit");
		jp.add(l);jp.add(jt);jp.add(b);
		add(jp);
	    setLocation(300,200);
	    setVisible(true);
	    b.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new OnlineUsers(getList(jt.getText()));
				dispose();
			}
		});
		
		
	}
	public static String[] getList(String ip){
		
		String[] ipList=new String[10];
		   
		try{
	    Socket sock = new Socket(ip,3000);
	    ObjectOutputStream ostream =new ObjectOutputStream(sock.getOutputStream());
	    ObjectInputStream istream = new ObjectInputStream(sock.getInputStream());
	    
	    System.out.print("Sending ip ");
		InetAddress ipp=InetAddress.getLocalHost();	
		String sendmessage=ipp.getHostAddress();
	            
		ostream.writeObject(sendmessage);
	    while(true)
	    {
	    		ipList=(String[])istream.readObject();
	            if(ipList[0]!=null)
	            {
	                for(String a:ipList)
	                System.out.println(a);
	                break;
	            }
	            
	     }}
	
	        catch(Exception e)
	        {System.out.println("Exceptionwa hai"+e);}
		return ipList;
		
		}

	
public static void main(String[] args) {

	new mainWala();
	 
}}
