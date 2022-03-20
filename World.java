import java.util.Scanner;
import java.util.Random;
/*
Ryan Smit



Oct 30 2021 - created
Mar 19 2022 - comments and
*/


public class World{
    private static final int ROWS = 3;
    private static final int COLUMNS = 3;
    private static final String HORIZONTAL_LINE = " _".repeat(COLUMNS);
    private char [][] current;                                              // the "map"
    private char aPlayer = ' ';                                             // default player = ' '
    private char computerPlayer = 'O';                                      // default AI = 'O'
    private UserInterface anInterface;
    private int coin;                        
    private boolean winner = false;                                         // winner = true if player in winner
    private boolean stalemate = false;

    public World(){
        current = new char[ROWS][COLUMNS];
        anInterface = new UserInterface();
        // initializes char's in map to '1' to '9'
        int count = 1;
        for (int r = 0; r < ROWS; r++){
            for (int c = 0; c < COLUMNS; c++){
                current[r][c] = (char)count;
                count ++;
            }
        }
        //player chooses symbol
        aPlayer = anInterface.chooseSymbol();
        if (aPlayer == 'O')
            computerPlayer = 'X';
        coin = anInterface.flipCoin();
    }

    // prints out the current "map"
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


    /* runs one set of player and AI turns each loop
    ** ends when either player or AI gets 3 in a row (checkDone())
    */
    public void runTurn(){                                                  //debugModeOn 
        char entry = ' ';
        boolean valid;
        Random rand = new Random();
        int flip = rand.nextInt(2);
        char coinFlipped = ' ';
        if (flip == 0)
            coinFlipped = 'H';
        else if (flip == 1)
            coinFlipped = 'T';

        if (flip == coin){ //player goes first
            System.out.format("Coin flip was %c, you go first!\n", coinFlipped);
            display();
            while (true){   
                valid = false;
                while (valid == false){
                    entry = anInterface.start(aPlayer);
                    valid = checkEmpty(entry);
                    if (valid == false)
                        System.out.println("Please choose an empty space");
                }
                placeSymbol(aPlayer, entry);
                System.out.format("You successfully placed your %c at position %c\n", aPlayer, entry);
                display();
                if (checkDone(aPlayer)){
                    winner = true;
                    break;
                }     
                isStalemate();    
                if (stalemate == true)
                    break;   
                computerTurn();
                display();
                if (checkDone(computerPlayer))
                    break;
                isStalemate();    
                if (stalemate == true)
                    break;  
            }
        }
        else { // AI goes first
            System.out.format("Coin flip was %c...\n", coinFlipped);
            display();
            while (true){
                computerTurn();
                display();
                if (checkDone(computerPlayer))
                    break;       
                isStalemate();    
                if (stalemate == true)
                    break;  
                valid = false;
                while (valid == false){
                    entry = anInterface.start(aPlayer);
                    valid = checkEmpty(entry);
                    if (valid == false)
                        System.out.println("Please choose an empty space");
                }
                placeSymbol(aPlayer, entry);
                System.out.format("You successfully placed your %c at position %c\n", aPlayer, entry);
                display();
                if (checkDone(aPlayer)){
                    winner = true;
                    break;
                } 
                isStalemate();    
                if (stalemate == true)
                    break;  
            }
        }
        if (stalemate == true)
            System.out.println("No more moves left, no one wins...");
        else {
            if (winner == true)
                System.out.println("Congratulations! You won!");
            else
                System.out.println("You lost...");
        }     
    }

    /* checks if player's choice is an empty square
    ** returns true if empty
    ** assumes default square numbers means empty
    */
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

    /* checks if passed char has 3 in a row
    **
    */
    public boolean checkDone(char testChar){                                       // debug mode
        boolean done = false;
        if (current[1][1] == testChar){
            if (current[1][0] == testChar && current[1][2] == testChar)
                return done = true;
            else if (current[0][1] == testChar && current[2][1] == testChar)
                return done = true;
            else if (current[0][0] == testChar && current[2][2] == testChar)
                return done = true;
            else if (current[0][2] == testChar && current[2][0] == testChar)
                return done = true;
        }
        if (current[0][0] == testChar){
            if (current[0][1] == testChar && current[0][2] == testChar)
                return done = true;
            else if (current[1][0] == testChar && current[2][0] == testChar)
                return done = true;
        }
        if (current[2][2] == testChar) {
            if (current[2][0] == testChar && current[2][1] == testChar)
                return done = true;
            else if (current[0][2] == testChar && current[1][2] == testChar)
                return done = true;
        }
        return done;
    }
    
    /* sets symbol at chosen square 
    ** already checked if empty square
    */
    public void placeSymbol(char symbol, char position){
        switch(position){
			case '1':
                current[0][0] = symbol;
				break;

            case '2':
                current[0][1] = symbol;
				break;

            case '3':
                current[0][2] = symbol;
				break;

            case '4':
                current[1][0] = symbol;
				break;

            case '5':
                current[1][1] = symbol;
				break;

            case '6':
                current[1][2] = symbol;
				break;

            case '7':
                current[2][0] = symbol;
				break;

            case '8':
                current[2][1] = symbol;
				break;

            case '9':
                current[2][2] = symbol;
				break;

            default:
                break;
        }
    }

    /* logic for AI's turn
    ** 1. win if about to win
    ** 2. block player if player about to win
    ** 3. choose best place
    **      a. middle square, 5
    **      b. corner square, 1, 3, 7, 9
    **      c. 2, 4, 6, 8
    */
    public void computerTurn(){                                                     //debugmodeon
        char close = checkIfClose(computerPlayer);
        if (close != '0'){ // AI about to win
            placeSymbol(computerPlayer, close);
        }
        else {
            close = checkIfClose(aPlayer);
            if (close != '0'){ //player about to win
                placeSymbol(computerPlayer, close);
            }
            else { //choose a spot
                if (checkEmpty(5)){ //middle is empty
                    placeSymbol(computerPlayer, '5');
                    close = '5';
                }    
                else if (checkSymbol(5)){ //computer has middle
                    if (checkEmpty(1)){ 
                        placeSymbol(computerPlayer, '1');
                        close = '1';
                    } 
                    else if (checkEmpty(3)){ 
                        placeSymbol(computerPlayer, '3');
                        close = '3';
                    } 
                    else if (checkEmpty(7)){ 
                        placeSymbol(computerPlayer, '7');
                        close = '7';
                    } 
                    else if (checkEmpty(9)){ 
                        placeSymbol(computerPlayer, '9');
                        close = '9';
                    } 
                    else if (checkEmpty(2)){
                        placeSymbol(computerPlayer, '2');
                        close = '2';
                    } 
                    else if (checkEmpty(4)){ 
                        placeSymbol(computerPlayer, '4');
                        close = '4';
                    } 
                    else if (checkEmpty(6)){ 
                        placeSymbol(computerPlayer, '6');
                        close = '6';
                    } 
                    else if (checkEmpty(8)){ 
                        placeSymbol(computerPlayer, '8');
                        close = '8';
                    } 
                }
                else {  //player has middle square
                    if (checkEmpty(1)){ 
                        placeSymbol(computerPlayer, '1');
                        close = '1';
                    } 
                    else if (checkEmpty(3)){ 
                        placeSymbol(computerPlayer, '3');
                        close = '3';
                    } 
                    else if (checkEmpty(7)){ 
                        placeSymbol(computerPlayer, '7');
                        close = '7';
                    } 
                    else if (checkEmpty(9)){ 
                        placeSymbol(computerPlayer, '9');
                        close = '9';
                    } 
                    else if (checkEmpty(2)){
                        placeSymbol(computerPlayer, '2');
                        close = '2';
                    } 
                    else if (checkEmpty(4)){ 
                        placeSymbol(computerPlayer, '4');
                        close = '4';
                    } 
                    else if (checkEmpty(6)){ 
                        placeSymbol(computerPlayer, '6');
                        close = '6';
                    } 
                    else if (checkEmpty(8)){ 
                        placeSymbol(computerPlayer, '8');
                        close = '8';
                    } 
                }
            }  
        } 
        if (close == '0'){
            System.out.println("Error, map is full?!");
            System.exit(0);
        }
        System.out.format("%c placed at position %c", computerPlayer, close);
    }

    /*
    **
    */
    public boolean checkSymbol(int position){ //returns true if computer has the spot
        boolean result = false;

        switch(position){
			case 1:
                if (current[0][0] == computerPlayer)
                    result = true;
				break;

            case 2:
                if (current[0][1] == computerPlayer)
                    result = true;
				break;

            case 3:
                if (current[0][2] == computerPlayer)
                    result = true;
				break;

            case 4:
                if (current[1][0] == computerPlayer)
                    result = true;
				break;

            case 5:
                if (current[1][1] == computerPlayer)
                    result = true;
				break;

            case 6:
                if (current[1][2] == computerPlayer)
                    result = true;
				break;

            case 7:
                if (current[2][0] == computerPlayer)
                    result = true;
				break;

            case 8:
                if (current[2][1] == computerPlayer)
                    result = true;
				break;

            case 9:
                if (current[2][2] == computerPlayer)
                    result = true;
				break;

            default:
                break;
        }
        return(result);
    }

    /* checks if someone (parameter) close to winning
    ** returns the (first found) empty space needed to win/block
    */
    public char checkIfClose(char testChar){                                       // debug mode
        char next = '0'; 
        if (current[0][0] == testChar){
            if (current[0][1] == testChar)
                return next = '3';
            if (current[0][2] == testChar)
                return next = '2';
            if (current[1][0] == testChar)
                return next = '7';
            if (current[1][1] == testChar)
                return next = '9';
            if (current[2][0] == testChar)
                return next = '4';
            if (current[2][2] == testChar)
                return next = '5';
        }
        if (current[0][1] == testChar){
            if (current[0][2] == testChar)
                return next = '1';
            if (current[1][1] == testChar)
                return next = '8';
            if (current[2][1] == testChar)
                return next = '5';
        }
        if (current[0][2] == testChar) {
            if (current[1][1] == testChar)
                return next = '7';
            if (current[1][2] == testChar)
                return next = '9';
            if (current[2][0] == testChar)
                return next = '5';
            if (current[2][2] == testChar)
                return next = '6';                
        }
        if (current[1][0] == testChar){
            if (current[1][1] == testChar)
                return next = '6';
            if (current[1][2] == testChar)
                return next = '5';
            if (current[2][0] == testChar)
                return next = '1';
        }
        if (current[1][1] == testChar){
            if (current[1][2] == testChar)
                return next = '4';
            if (current[2][0] == testChar)
                return next = '3';
            if (current[2][1] == testChar)
                return next = '2';
            if (current[2][2] == testChar)
                return next = '1';    
        }
        if (current[2][1] == testChar){
            if (current[2][0] == testChar)
                return next = '9';
            if (current[2][2] == testChar)
                return next = '7';
        }
        if (current[2][2] == testChar){
            if (current[2][0] == testChar)
                return next = '8';
            if (current[1][2] == testChar)
                return next = '3';
        }
        return next;
    }
    
    /* checks if there are no moves left
    ** changes this.stalemate to true and if there are empty spaces, changes it back to false
    */
    public void isStalemate(){                                       // debug mode
        stalemate = true;
        for (int r = 0; r < ROWS; r++){
            for (int c = 0; c < COLUMNS; c++){
                if (current[r][c] != 'O' && current[r][c] != 'X'){
                    stalemate = false;
                    return;
                }
            }

        }
    }

}
