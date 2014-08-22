package HelperClasses;

import java.util.Random;

public class passGen 
{
    private static final String lCase = "abcdefghijklmnopqrstuvwxyz";
    private static final String uCase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String sChar = "@!#*=";
    private static final String intChar = "0123456789";
    private static Random r = new Random();
    private static String pass = "";          

    /**
     * @param aPass the pass to set
     */
    public static void setPass(String aPass) {
        pass = aPass;
    }
    public passGen()
    {
        while(pass.length()!= 10)
        {
            int rPick=r.nextInt(4); //the number in brackets is the bound on the random number that shall be generated
            if(rPick==0)
            {
                int spot = r.nextInt(25); //a new random number
                pass += lCase.charAt(spot); //the character present at that spot in the string
            }
            else if (rPick == 1) 
            {
                int spot = r.nextInt (25);
                pass += uCase.charAt(spot);
            } 
            else if (rPick == 2) 
            {
                int spot = r.nextInt (4);
                pass += sChar.charAt(spot);
            } 
            else if (rPick == 3)
            {
                int spot = r.nextInt (9);
                pass += intChar.charAt (spot);
            }
        } 
    }
    public String getPass()
    {
        return pass;
    }
}