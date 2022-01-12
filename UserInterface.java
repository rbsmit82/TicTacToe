import java.util.Scanner;
/*
Ryan Smit

a Class designed to display the menu, asking where the user wants to put their symbol (1-9)
////returns either 'r' or 'p' to the Driver (GameOfLife)/////
checks that input is is valid type and number

Oct 30 2021- created
*/


public class UserInterface{

    private boolean done;

    public UserInterface(){
    	done = false;
    }

    public char chooseSymbol(){
        final int FIRST = 0;
	    Scanner in = new Scanner(System.in);
	    String line;
	    char selection;
	    char type = ' ';

    	while (done == false)
    	{
    		System.out.println("Choose to play 'X' or 'O': ");
    		line = in.nextLine();
    		selection = line.charAt(FIRST);
    		type = processChoice(selection);
    	}
    	in.close();
		return(type);
    }

    public char processChoice(char selection){
    	char type = ' ';

		switch(selection){
			case 'x':
			case 'X':
				type = 'X';
				done = true;
				break;

			case 'o':
			case 'O':
				type = 'O';
				done = true;
				break;

			case 'd':
			case 'D':
				GameStatus.debugModeOn = true;
				System.out.println("Debug mode on");
				break;

			default:
				System.out.println("Please enter 'X' or 'O'");
		}
		return(type);
    }

    public int start(char symbol){
        done = false;
		final int FIRST = 0;
	    Scanner in = new Scanner(System.in);
	    String line;
	    char selection;
	    int type = 0;

    	while (done == false)
    	{
    		displayMenu(symbol);
    		line = in.nextLine();
    		selection = line.charAt(FIRST);
    		type = processMenu(selection);
    	}
    	in.close();
		return(type);
    }

    public void displayMenu(char symbol){
        System.out.format("Choose where to place your %c: \n", symbol);
    }

    public int processMenu(char selection){
    	int type = 0;

		switch(selection){
			case '1':
				type = 1;
				done = true;
				break;

            case '2':
				type = 2;
				done = true;
				break;

            case '3':
				type = 3;
				done = true;
				break;

            case '4':
				type = 4;
				done = true;
				break;

            case '5':
				type = 5;
				done = true;
				break;

            case '6':
				type = 6;
				done = true;
				break;

            case '7':
				type = 7;
				done = true;
				break;

            case '8':
				type = 8;
				done = true;
				break;

            case '9':
				type = 9;
				done = true;
				break;

			case 'd':
			case 'D':
				GameStatus.debugModeOn = true;
				System.out.println("Debug mode on");
				break;

			default:
				System.out.println("Please enter a number between 1 and 9");
		}
		return(type);
    }

}