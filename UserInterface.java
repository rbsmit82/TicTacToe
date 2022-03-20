import java.util.Scanner;
/*
Ryan Smit

a Class designed to display the menu, asking where the user wants to put their symbol (1-9)

checks that input is is valid type and number

Oct 30 2021 - created
Mar 19 2022 - comments and debug mode displays
*/


public class UserInterface{

    private boolean done;                                   // done = true if a correct input is received
    private final int FIRST = 0;

    public UserInterface(){
    	done = false;
    }

    /* for player to choose symbol
    ** returns players choice 
    */
    public char chooseSymbol(){
        done = false;
        char selection = ' ';
        char type = ' ';
	    Scanner in = new Scanner(System.in);
        String line;
	    

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

    /* processes player input as parameter
    ** acceptable inputs are o, O, x, X, d, D
    */ 
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
				if (GameStatus.debugModeOn == false){
                    GameStatus.debugModeOn = true;
                    System.out.println("Debug mode on");
				    break;
                }
				else if (GameStatus.debugModeOn == true){
                    GameStatus.debugModeOn = false;
                    System.out.println("Debug mode off");
				    break;
                }

			default:
				System.out.println("Please enter 'X' or 'O'");
		}
		return(type);
    }

    /* starts players turn, takes player's symbol
    ** returns where the player wants to place symbol
    */
    public char start(char symbol){                                             
        done = false;
        char selection = ' ';
        char type = ' ';
	    Scanner in = new Scanner(System.in);
        String line;

    	while (done == false)
    	{
    		System.out.format("Choose where to place your %c: \n", symbol);
    		line = in.nextLine();
    		selection = line.charAt(FIRST);
    		type = processMenu(selection);
    	}
    	in.close();
        if (GameStatus.debugModeOn == true){
            System.out.format("player chose %c\n", type);
        }

		return(type);
    }


    /* processes player input as parameter
    ** acceptable inputs are 1,2,3,4,5,6,7,8,9, d, D
    */ 
    public char processMenu(char selection){
        char type = ' ';
		switch(selection){
			case '1':
				type = '1';
				done = true;
				break;

            case '2':
				type = '2';
				done = true;
				break;

            case '3':
				type = '3';
				done = true;
				break;

            case '4':
				type = '4';
				done = true;
				break;

            case '5':
				type = '5';
				done = true;
				break;

            case '6':
				type = '6';
				done = true;
				break;

            case '7':
				type = '7';
				done = true;
				break;

            case '8':
				type = '8';
				done = true;
				break;

            case '9':
				type = '9';
				done = true;
				break;

			case 'd':
			case 'D':
                if (GameStatus.debugModeOn == false){
                    GameStatus.debugModeOn = true;
                    System.out.println("Debug mode on");
                    break;
                }
                else if (GameStatus.debugModeOn == true){
                    GameStatus.debugModeOn = false;
                    System.out.println("Debug mode off");
                    break;
                }

			default:
				System.out.println("Please enter a number between 1 and 9");
		}
		return(type);
    }

    /* asks player to chose heads or tails
    ** returns 0 (heads) or 1 (tails)
    */
    public int flipCoin(){
        char selection = ' ';
        int coin = -1;
        done = false;
	    Scanner in = new Scanner(System.in);
        String line;

        System.out.println("Flip a coin to see who goes first");
    	while (done == false)
    	{
    		System.out.println("Choose (h)eads or (t)ails: ");
    		line = in.nextLine();
    		selection = line.charAt(FIRST);
    		coin = processFlip(selection);
    	}
    	in.close();
        if (GameStatus.debugModeOn == true){
            System.out.format("player chose %d\n", coin);
        }

		return(coin);
    }

    /* processes player input as parameter
    ** acceptable inputs are h, H, t, T, d, D
    ** heads = 0, tails = 1
    */ 
    public int processFlip(char selection){
        int coin = -1;
		switch(selection){
			case 'h':
			case 'H':
				coin = 0;
				done = true;
				break;

			case 't':
			case 'T':
				coin = 1;
				done = true;
				break;

			case 'd':
			case 'D':
				if (GameStatus.debugModeOn == false){
                    GameStatus.debugModeOn = true;
                    System.out.println("Debug mode on");
				    break;
                }
				else if (GameStatus.debugModeOn == true){
                    GameStatus.debugModeOn = false;
                    System.out.println("Debug mode off");
				    break;
                }

			default:
				System.out.println("Please enter 'X' or 'O'");
		}
		return(coin);
    }
}