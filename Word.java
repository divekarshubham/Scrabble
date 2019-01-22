package Scrabble;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class Word extends JFrame{
	 JLabel label;
	    JTextField t;
	    JButton b;
	    JRadioButton l,r,d,u;
	    ButtonGroup bggroup;
	    JPanel radioPanel;
	    Word(int row,int col)
	    {
	        label=new JLabel("Enter Word : ");
	        b=new JButton("Place Word");
	        t=new JTextField(50);
	        t.setBounds(20,50,80,30);
	        l = new JRadioButton("Left", true);
	        r = new JRadioButton("Right");
	        d = new JRadioButton("Down");
	        u = new JRadioButton("Up");
	        l.setActionCommand("left");
	        r.setActionCommand("right");
	        d.setActionCommand("down");
	        u.setActionCommand("up");

	        bggroup = new ButtonGroup();
	        bggroup.add(l);
	        bggroup.add(r);
	        bggroup.add(d);
	        bggroup.add(u);
	        radioPanel = new JPanel();
	        radioPanel.setLayout(new GridLayout(3, 1));
	        radioPanel.add(l);
	        radioPanel.add(r);
	        radioPanel.add(d);
	        radioPanel.add(u);
	        radioPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Direction?"));
	        getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
	        add(label);
	        add(t);
	        add(radioPanel);
	        add(b);
	        setSize(200,200);
	        setLocationRelativeTo(null);
	        setVisible(true);
	        b.addActionListener(new ActionListener(){
	                public void actionPerformed(ActionEvent ae)
	                {
	                    if(SL.isPlaced(t.getText().trim(),bggroup.getSelection().getActionCommand().charAt(0),row,col))
	                    {
	                        dispose();
	                        SL.changeLetters(false);
	                        ScrabbleGame.changeIt(GD.b,GD.getBG(),GD.getML());
	                        ScrabbleGame.Sender();
	                    }
	                }
	            });
	    }

}
