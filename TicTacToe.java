/*
Ryan Smit

Oct 30 2021 - created
*/


public class TicTacToe{
    public static void main (String [] args){

    	System.out.println("///////////////////////////////////////////////////////////////");
        System.out.println("/                                                             /");
        System.out.println("/   #####  #   ###     #####   #    ###     #####  ##  ####   /");
        System.out.println("/     #    #  #          #    # #  #          #   #  # #      /");
        System.out.println("/     #    #  #    ###   #   #   # #    ###   #   #  # ###    /");
        System.out.println("/     #    #  #          #   ##### #          #   #  # #      /");
        System.out.println("/     #    #   ###       #   #   #  ###       #    ##  ####   /");
        System.out.println("/                                                             /");
        System.out.println("///////////////////////////////////////////////////////////////");

        World aWorld = new World();
    	aWorld.runTurn();
        System.exit(0);
        
    }
}



