#Tic-Tac-Toe in Java
##Ryan Smit [rbsmit82](https://github.com/rbsmit82)
!(cli.jpg)

This is a simple version of Tic-Tac-Toe played in a command-line interface.
It has one human player (you) and one "AI".

##Description
The starting script is `TicTacToe.java`
1. It creates a `World` 
    - Initializes the "board"
    - Creates a `UserInterface`
        - Asks the player to choose their symbol
        - Asks the player to choose heads or tails
2. Runs the main function `World.runTurn()`
    - Performs the "coin flip"
    - The player and AI take turns placing symbols
        - Only on empty spaces
        - The AI is hard coded
            - First it checks if it can win
            - Then it checks if it needs to block the player
            - Otherwise it places a symbol at the "best" position
        - If someone has three symbols in a line, they win!
        - Or else, if no more moves can be made, the game ends in a stalemate

##Install/Execution
Java JDK required to run.
Works with Java 17.0.4.1 (older or new versions untested, but most likely okay).
Download all files to the same directory.
Compile the files by typing `javac *.java` in the command line.
Execute the game by typing `java TicTacToe`.

##Help
The game does have a "debugging mode" that can be accessed by typing 'd' at any point when input is required.
(Although not all functions currently display debugging information)
All possible moves have not been bug tested against the hard coded AI.

##Versions
1.0 Public release Apr 23 2023
