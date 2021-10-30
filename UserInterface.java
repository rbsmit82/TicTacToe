import java.util.Scanner;
/*
Ryan Smit

a Class designed to display the menu, asking where the user wants to put their symbol (1-9)
////returns either 'r' or 'p' to the Driver (GameOfLife)/////
checks that input is is valid type and number

Oct 30 2021- created
*/


public class UserInterface
{

    private boolean done;

    public UserInterface() 
    {
    	done = false;
    }

    public void displayMenu() 
    {
        System.out.println("Choose birth and death rules");
        System.out.println("(p)rosperous biosphere\n(r)egular biosphere");
        System.out.print("Biosphere type: ");
    }

    public char processMenu(char selection) 
    /*
    */
    {
    	char type = ' ';

		switch(selection)
		{
			case 'p':
			case 'P':
				type = 'p';
				done = true;
				break;

			case 'r':
			case 'R':
				type = 'r';
				done = true;
				break;

			case 'd':
			case 'D':
				GameStatus.debugModeOn = true;
				System.out.println("Debug mode on");
				break;

			default:
				System.out.println("Please enter 'p' or 'r'");
		}
		return(type);
    }

    public char start() 
    /* 
    */
    {
		final int FIRST = 0;
	    Scanner in = new Scanner(System.in);
	    String line;
	    char selection;
	    char type = ' ';

    	while (done == false)
    	{
    		displayMenu();
    		line = in.nextLine();
    		selection = line.charAt(FIRST);
    		type = processMenu(selection);
    	}
    	in.close();
		return(type);
    }

}