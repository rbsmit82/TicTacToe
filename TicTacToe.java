import java.util.Scanner;
/*
Ryan Smit

mains Driver() for simulation of Conway's Game of Life
creates a [][] of Critters from an input text file
rules for Critter birth/death can be either regular (Biosphere), or prosperous (ProsperousBiosphere)
births/deaths happen "simultaneously", before any effects by a Taminator (if present)
Critter[][] can have a Taminator, a child of Critter that can "kill" neighbouring Critters and randomly move
displays the Critter[][] along with changes made by births/deaths and the Taminator
program runs until user quits

program will probably have errors if customized Critter or Taminator appearances are used (especially Taminators that are not 'T', or Critters that are not '*')


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
        
    }
}



