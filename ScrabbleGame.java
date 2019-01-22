package Scrabble;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class ScrabbleGame extends JFrame{
	static JTable board;
    JPanel panel,panel1,j;
    static JButton pass,change;
    static JLabel bag,myletters,turn;
    boolean check=false;
    static boolean myTurn=false;
    static String playerip;

    static void stopPlaying(){
    	pass.setVisible(false);
	    change.setVisible(false);
	    turn.setVisible(true);
	    myTurn=false;
    }
    
    static void startPlaying(){
    	pass.setVisible(true);
	    change.setVisible(true);
	    turn.setVisible(false);
	    myTurn=true;
    }
    
    static void Sender(){
    	try{
	    	 int i;
	    	 Socket sock = new Socket(playerip,3002);
		     ObjectOutputStream ostream = new ObjectOutputStream(sock.getOutputStream());
		     ObjectInputStream istream = new ObjectInputStream(sock.getInputStream());
		     	 	ostream.writeObject(GD.b);
				    if((i=(int)istream.readObject())==1)
				     {
				    	 ostream.writeObject(GD.f);
				     	 if((i=(int)istream.readObject())==1)
				     		 ostream.writeObject(GD.score1);
			     }
				
				 ostream.close();
				 sock.close();
				 stopPlaying();
    	}
    	catch (Exception e) {
			System.out.println("id1: "+e);
		}
    }
    
    static void changeIt(Character b[][],String bg,String ml){
        bag.setText(bg);
        myletters.setText(ml);
        
        if(ml.equals(""))
        	myletters.setText(GD.getML());
        
        for(int i=0;i<15;i++)
            for(int j=0;j<15;j++)
                    board.setValueAt(b[i][j],i,j);
        
    }
       
    
    ScrabbleGame(Character b[][],String bg,String ml,String ip,int p)
    {
    	 GameServer gs=new GameServer();
         Thread t=new Thread(gs);
         t.start();
         
    	playerip=ip;
        pass=new JButton("Pass");
        change=new JButton("Change");
        panel=new JPanel();
        panel1=new JPanel();
        bag=new JLabel(bg);
        myletters=new JLabel(ml);
        turn=new JLabel("Waiting for player to play");
        String col[]={"","","","","","","","","","","","","","",""};
        board=new JTable(b,col);
        board.setRowHeight(board.getRowHeight()*2);
        
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        board.setDefaultRenderer(Character.class, centerRenderer);
        
        j=new JPanel();
        j.setLayout(new BoxLayout(j,BoxLayout.Y_AXIS));
        
        panel1.setLayout(new FlowLayout(FlowLayout.CENTER));
        panel1.add(new JLabel("Your Letters: "));
        panel1.add(myletters);
        
        panel.setLayout(new FlowLayout(FlowLayout.CENTER));
        panel.add(pass);
        panel.add(change);
        panel.add(turn);
        turn.setVisible(false);
        
        j.add(panel1);
        j.add(panel);
        add(bag,BorderLayout.NORTH);
        add(board,BorderLayout.CENTER);
        add(j,BorderLayout.SOUTH);
        setSize(700,600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        turn.setVisible(false);
       if(p==2)
       { stopPlaying();
       		myletters.setText("");
       }
       else
    	   startPlaying();
        
        change.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent ae)
                {
                    SL.changeLetters(true);
                    changeIt(GD.b,GD.getBG(),GD.getML());
                    Sender();
                }
            });
        board.addMouseListener(new MouseAdapter(){
                public void mouseClicked(MouseEvent e1){
                	if(myTurn)
                    new Word(board.rowAtPoint(e1.getPoint()),board.columnAtPoint(e1.getPoint()));
                }
            });
        pass.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Sender();				
			}
		});
        
    }
}
