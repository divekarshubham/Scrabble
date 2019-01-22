package Scrabble;

public class GD {
	static Character b[][]=new Character[15][15];
    final static int sc[]={1,3,3,2,1,4,2,4,1,8,5,1,3,1,1,3,10,1,1,1,1,4,4,8,4,10};
    static int f[]={9,2,2,4,12,2,3,2,9,1,1,4,2,6,8,2,1,6,4,6,4,2,2,1,2,1};
    final static long pos[]={0,180493,286037,499436,613934,708482,774943,841359,940012,1043318,1057428,1076707,1137644,1270899,1347062,1431691,1710486,1722877,1820339,2083833,2216900,2404860,2439504,2474867,2478908,2484030};
    static int score1=0;
    static int score2=0;
    static Character a1[]={'*','*','*','*','*','*','*'};
    static Character a2[]={'*','*','*','*','*','*','*'};
    static String bg="",ml="";
    static String getBG()
    {
        bg="";
        for(int i=0;i<26;i++)
        {
            bg=bg+((char)(i+65)+"x"+f[i]+" ");
        }
        return bg;
    }

    static String getML()
    {
        ml="";
        for(int i=0;i<7;i++)
            ml=ml+" "+a1[i];
            return ml;
    }


}
