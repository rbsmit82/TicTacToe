/*
Ryan Smit

main Object for simulated players
X or O

Oct 30 2021 - created
*/


public class Player{
    public static final char DEFAULT_APPEARANCE = ' ';
    private char appearance;  

    public Player (){
        setAppearance(DEFAULT_APPEARANCE);
    }

    public Player(char ch){
        setAppearance(ch);
    }

    public char getAppearance(){
        return appearance;
    } 

    public void setAppearance(char newAppearance){
        appearance = newAppearance;
    } 
}

