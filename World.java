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


public class World
{
    //Constant declarations and attributes by James Tam, don't change.
    public static final int ROWS = 10;
    public static final int COLUMNS = 10;
    public static final String HORIZONTAL_LINE = "  - - - - - - - - - -";
    public static final String HORIZONTAL_COUNT = "  0 1 2 3 4 5 6 7 8 9 ";
    private Player [][] current;
    //new attributes below
    private Player [][] future;
    private Player [][] taminatorFuture;


    //Original code written by James Tam, don't modify
    public void Biosphere(Player [][] aWorld)
    {
        //Original code
        current = aWorld;
        //Student code
    }

    public void display()
    /*
    The code used was based on the display code written by James Tam
    */
    { 
        int r;
        int c;

        System.out.println("  PREVIOUS GENERATION \t  BIRTHS AND DEATHS   \t  TAMINATOR");
        System.out.println(HORIZONTAL_COUNT + "\t" + HORIZONTAL_COUNT + "\t" + HORIZONTAL_COUNT);
        System.out.println(HORIZONTAL_LINE + "\t" +HORIZONTAL_LINE+ "\t" + HORIZONTAL_LINE); //Line of dashes before the array
        for (r = 0; r < ROWS; r++)
        {
            System.out.print(r); //Line # before each row
            for (c = 0; c < COLUMNS; c++)
                System.out.print("|" + current[r][c]); //Bounding line left of array element 
            System.out.print("|\t"); //Bounding line at the of the row for the last element
            
            System.out.print(r); //Line # before each row
            for (c = 0; c < COLUMNS; c++)
                System.out.print("|" + future[r][c]); //Bounding line left of array element
            System.out.print("|\t"); //Bounding line at the of the row for the last element

            System.out.print(r); //Line # before each row
            for (c = 0; c < COLUMNS; c++)
                System.out.print("|" + taminatorFuture[r][c]); //Bounding line left of array element
            System.out.print("|\t"); //Bounding line at the of the row for the last element
            System.out.println("");
            System.out.println(HORIZONTAL_LINE + "\t" + HORIZONTAL_LINE + "\t" + HORIZONTAL_LINE);
        }
    } 

    //Original code written by James Tam, don't modify
    public Player [][] getCurrent() 
    {
        return(current);
    }

    
    public void runTurn()
    /*
    Original code written by James Tam
    */
    {
        Scanner in = new Scanner(System.in);
        String line;
        char selection;
        boolean done = false;

        while (done == false)
        {
            future = birthsAndDeaths(current);
            taminatorFuture = tamination(future);

            display();
            System.out.print("Press enter to continue or (q)uit: ");
            line = in.nextLine(); 

            if (line.length() > 0)
            {
                selection = line.charAt(0);
                while (selection == 'd' || selection == 'D')
                {
                    if (GameStatus.debugModeOn == true)
                    {
                        GameStatus.debugModeOn = false;
                        System.out.println("Debug mode off");
                    }
                    else
                    {
                        GameStatus.debugModeOn = true;
                        System.out.println("Debug mode on");
                    }
                    System.out.print("Press enter to continue or (q)uit: ");
                    line = in.nextLine();
                    if (line.length() > 0)
                        selection = line.charAt(0);
                    else
                        break;
                }

                if (selection == 'q' || selection == 'Q')
                    done = true;
            }
            else
            {
                current = taminatorFuture;
            }
        }
    }

    public Player[][] birthsAndDeaths(Player[][] anArray)
    {
        int r;
        int c;
        Location PlayerLocation;
        int numberOfNeighbours;
        Player[][] temporary = new Player[ROWS][COLUMNS];
        
        temporary = copyArray(anArray); // now temporary is a copy of current

        for (r = 0; r < ROWS; r++)
        {
            for (c = 0; c < COLUMNS; c++)
            {
                if (anArray[r][c].getAppearance() != 'T')
                {
                    PlayerLocation = new Location(r,c);
                    numberOfNeighbours = checkForNeighbours(PlayerLocation);
                    if (numberOfNeighbours <= 1 || numberOfNeighbours >= 4) //deaths
                    {
                        temporary[r][c].setAppearance(temporary[r][c].EMPTY);
                        if (GameStatus.debugModeOn == true)
                        {
                            System.out.println("<<birthsAndDeaths>>");
                            System.out.println("critter at " + r + ", " + c + " has died");
                        }
                    }
                    if (numberOfNeighbours == 3) //births
                    {
                        temporary[r][c].setAppearance(temporary[r][c].DEFAULT_APPEARANCE);
                        if (GameStatus.debugModeOn == true)
                        {
                            System.out.println("<<birthsAndDeaths>>");
                            System.out.println("critter at " + r + ", " + c + " has been born");
                        }
                    }
                }
            }
        }
        return(temporary);
    }

    public Player[][] tamination(Player[][] anArray)
    {
        int r;
        int c;
        int i;
        Player[][] temporary = new Player[ROWS][COLUMNS];
        Location[] neighbours = new Location[8]; // hard coded because only 8 max possible neighbours ever
        Location homeLocation;
        Location newLocation;

       temporary = copyArray(anArray); // now temporary is a copy of "future"

        for (r = 0; r < ROWS; r++)
        {
            for (c = 0; c < COLUMNS; c++)
            {
                if (temporary[r][c] instanceof Taminator)
                {
                    homeLocation = new Location(r,c); //location of T
                    neighbours =((Taminator) temporary[r][c]).findNeighbours(homeLocation, temporary); // Location[] of T's neighbours
                    for (i = 0; i < 8; i++)
                    {
                        if(neighbours[i] != null)
                        {
                            int a = neighbours[i].getRow();
                            int b = neighbours[i].getColumn();
                            temporary[a][b].setAppearance(temporary[a][b].EMPTY); //kills all neighbours
                            if (GameStatus.debugModeOn == true)
                                System.out.println("Critter at " + a + "," + b + " was taminated");
                        }
                    }
                    newLocation = ((Taminator) temporary[r][c]).move();
                    while (checkTaminatorTeleportation(homeLocation, newLocation) == false)
                        newLocation = ((Taminator) temporary[r][c]).move();

                    temporary[newLocation.getRow()][newLocation.getColumn()] = temporary[r][c]; //copy T from "old" location (home) to "new" location
                    temporary[r][c] = new Player(); //erases old Taminator
                    temporary[r][c].setAppearance(temporary[r][c].EMPTY);
                }
            }
        }
        return(temporary);
    }

    public int checkForNeighbours(Location aLocation) 
        /*
        what is, how do
        limits, assumpts
        */
    {
        int countNeighbours; 
        int r = aLocation.getRow();
        int c = aLocation.getColumn();
        int i;
        int j;

        if (current[r][c].getAppearance() == Player.DEFAULT_APPEARANCE)
            countNeighbours = -1; // because code will count itself
        else
            countNeighbours = 0;

        for(i = r-1; i <= r+1; i++)
        {
            if (0 <= i && i < ROWS)
            {
                for(j = c-1; j <= c+1; j++)
                {
                    if (0 <= j && j < COLUMNS)
                    {
                        if (current[i][j].getAppearance() == current[i][j].DEFAULT_APPEARANCE) // counts itself as neighbour
                            countNeighbours++;
                    }
                }
            }
        }

        if (GameStatus.debugModeOn == true)
        {
            System.out.println("<<checkForNeighbours>>");
            System.out.println("critter at " + r + ", " + c + " has " + countNeighbours + "neighbours");
        }
        
        return(countNeighbours);
    }

    public boolean checkTaminatorTeleportation(Location homeLocation, Location testLocation)
    {
        boolean valid = true; //false is blocked by self or critter, true is a valid move

        int a = homeLocation.getRow();
        int b = homeLocation.getColumn();
        int c = testLocation.getRow();
        int d = testLocation.getColumn();

        if (a == c && b == d)
            valid = false;
        if (future[c][d].getAppearance() == '*')
            valid = false;

        return(valid);
    }

    public Player[][] copyArray(Player[][] anArray)
    {
        int r;
        int c;
        char letter;
        Player[][] temporary = new Player[ROWS][COLUMNS];

        for (r = 0; r < ROWS; r++)
        {
            for (c = 0; c < COLUMNS; c++)
            {
                letter = anArray[r][c].getAppearance();
                switch(letter)
                {
                    case Player.EMPTY:
                    case Player.DEFAULT_APPEARANCE:
                        temporary[r][c] = new Player(letter);
                        break;

                    case Taminator.DEFAULT_APPEARANCE:
                        temporary[r][c] = new Taminator(letter);
                        break;

                    default:
                    ;
                }
            }
        }
        return(temporary);
    }
}
