import java.util.Scanner;
/*
Ryan Smit

creates and stores the Critter[][] of simulated creatures
each round, displays the current [][], the projected [][] after births/deaths and the [][] after the Taminator operations
continues until the user quits
identifies how many neighbours a Critter has in order to...
handles the "regular" rules for births/deaths
carries out the functions of the Taminator, "kills" its neighbours before it randomly moves, checks that the move is valid

assumes all Taminators have appearance 'T' and Critters are either '*' or ' '

Oct 30 2021 - created
*/


public class World{
    public static final int ROWS = 3;
    public static final int COLUMNS = 3;
    public static final String HORIZONTAL_LINE = " _".repeat(COLUMNS);
    public char [][] current;
    private Player aPlayer = new Player();
    private UserInterface anInterface = new UserInterface();
    public boolean done = false;
    public boolean winner = false;

    public World(){
        current = new char[ROWS][COLUMNS];
        int count = 1;
        for (int r = 0; r < ROWS; r++){
            for (int c = 0; c < COLUMNS; c++){
                current[r][c] = (char)count;
                count ++;
            }
        }
        aPlayer.setAppearance(anInterface.chooseSymbol());
    }


    public void display(){ 
        for (int r = 0; r < ROWS; r++){
            System.out.println(HORIZONTAL_LINE);
            for (int c = 0; c < COLUMNS; c++){
                System.out.print("|" + current[r][c]);
            }
            System.out.println("|");
        }
        System.out.println(HORIZONTAL_LINE);
    } 


    public char [][] getCurrent(){
        return(current);
    }

    
    public void runTurn(){                            //debugModeOn
        while (done == false){
            display();
            boolean valid = false;
            while (valid == false){
                int entry = anInterface.start(aPlayer.getAppearance());
                valid = checkEmpty(entry);
                if (valid == false)
                    System.out.println("Please choose an empty space");
            }
            //change entry to aPlayer.getAppearance()
            checkDone();
            if (done == true)
                break;
            //do AI's turn
            checkDone();
        }

        if (winner = true)
            System.out.println("Congratulations! You won!");
        else
            System.out.println("You lost...");
    }

    public boolean checkEmpty(int position){
        boolean result = false;

        switch(position){
			case 1:
                if (current[0][0] == '1')
                    result = true;
				break;

            case 2:
                if (current[0][1] == '2')
                    result = true;
				break;

            case 3:
                if (current[0][2] == '3')
                    result = true;
				break;

            case 4:
                if (current[1][0] == '4')
                    result = true;
				break;

            case 5:
                if (current[1][1] == '5')
                    result = true;
				break;

            case 6:
                if (current[1][2] == '6')
                    result = true;
				break;

            case 7:
                if (current[2][0] == '7')
                    result = true;
				break;

            case 8:
                if (current[2][1] == '8')
                    result = true;
				break;

            case 9:
                if (current[2][2] == '9')
                    result = true;
				break;

            default:
                break;
        }
        return(result);
    }

    public void checkDone(){

    }
    
}
