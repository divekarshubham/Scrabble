package Scrabble;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Arrays;
import java.util.Random;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

public class SL {
	 static File fl=new File("words.txt");
	    static RandomAccessFile fin;
	    static void changeLetters(boolean f)
	    {
	        int i,j;
	        Random rnd=new Random();
	        if(f)
	        {
	            for(i=0;i<7;i++)
	                GD.f[(int)(GD.a1[i])-65]++;
	            for(i=0;i<7;i++)
	                GD.a1[i]='*';
	        }
	        for(i=0;i<7;i++)
	        {
	            if(GD.a1[i]=='*')
	            {
	                j=rnd.nextInt(26);
	                if(GD.f[j]==0)
	                {
	                    i--;
	                    continue;
	                }
	                GD.a1[i]=(char)(j+65);
	                GD.f[j]--;
	            }
	        }
	    }

	    static boolean isPlaced(String w,char d,int r,int c)
	    {
	        boolean flag=false;
	        int i,j;
	        Character amap[]=new Character[7];
	        try
	        {
	            fin=new RandomAccessFile(fl,"rw");
	            String p="";
	            fin.seek(GD.pos[(int)(Character.toUpperCase(w.charAt(0)))-65]);
	            while((p=fin.readLine())!=null)
	            {
	                if(w.equalsIgnoreCase(p))
	                {
	                    flag=true;
	                    break;
	                }
	                if((int)(Character.toLowerCase(p.charAt(0)))>(int)(Character.toLowerCase(w.charAt(0))))
	                    break;
	            }
	            //fin.close();
	            p="";
	        }
	        catch(IOException ioe){}
	        if(!flag)
	            JOptionPane.showMessageDialog(new JDialog(),"That is not a valid English word.");
	        if(flag)
	        {
	            w=w.toUpperCase();
	            try{
	                if(GD.b[r][c]!=' '&&GD.b[r][c]!=w.charAt(0))
	                {
	                    JOptionPane.showMessageDialog(new JDialog(),"You can't place a word here.");
	                    flag=false;
	                }
	            }
	            catch(ArrayIndexOutOfBoundsException ai)
	            {
	                JOptionPane.showMessageDialog(new JDialog(),"You can't place a word here.");
	                flag=false;
	            }

	            if(flag)
	            {
	                try
	                {
	                    for(i=0;i<w.length();i++)
	                    {
	                        if((d=='r'&&GD.b[r][c+i]!=' '&&GD.b[r][c+i]!=w.charAt(i))||(d=='l'&&GD.b[r][c-i]!=' '&&GD.b[r][c-i]!=w.charAt(i))||(d=='u'&&GD.b[r-i][c]!=' '&&GD.b[r-i][c]!=w.charAt(i))||(d=='d'&&GD.b[r+i][c]!=' '&&GD.b[r+i][c]!=w.charAt(i)))
	                        {
	                            JOptionPane.showMessageDialog(new JDialog(),"You can't place a word here.");
	                            flag=false;
	                            break;
	                        }
	                    }
	                }
	                catch(ArrayIndexOutOfBoundsException ai)
	                {
	                    JOptionPane.showMessageDialog(new JDialog(),"You can't place a word here.");
	                    flag=false;
	                }
	                if(flag)
	                {
	                    amap=GD.a1;
	                    for(i=0;i<w.length();i++)
	                    {
	                        if((d=='r'&&GD.b[r][c+i]==' ')||(d=='l'&&GD.b[r][c-i]==' ')||(d=='u'&&GD.b[r-i][c]==' ')||(d=='d'&&GD.b[r+i][c]==' '))
	                        {
	                            if(!Arrays.asList(GD.a1).contains(w.charAt(i)))
	                            {
	                                flag=false;
	                                JOptionPane.showMessageDialog(new JDialog(),"You do not have sufficient letters for this word");
	                                break;
	                            }
	                            GD.a1[Arrays.asList(GD.a1).indexOf(w.charAt(i))]='*';
	                        }
	                    }
	                    if(!flag)
	                        GD.a1=amap;
	                    if(flag)
	                    {
	                        for(i=0;i<w.length();i++)
	                        {
	                            GD.score1+=GD.sc[(int)(w.charAt(i))-65];
	                            if(d=='r')
	                                GD.b[r][c+i]=w.charAt(i);
	                            else if(d=='l')
	                                GD.b[r][c-i]=w.charAt(i);
	                            else if(d=='u')
	                                GD.b[r-i][c]=w.charAt(i);
	                            else if(d=='d')
	                                GD.b[r+i][c]=w.charAt(i);
	                        }
	                        
	                        JOptionPane.showMessageDialog(new JDialog(),"Current Score : "+GD.score1+"\n Oponents Score: "+GD.score2);
	                       
	                    }
	                }
	            }
	        }
	        return flag;
	    }
}
