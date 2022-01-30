Starting script is TicTacToe
1. creates a World
    a. creates UserInterface
        1. firstTurn displays player choose X or O
            a. checks input
            b. returns input
    b. creates two Players, X, O, one AI one player
    c. displays world (in ASCII art) 
    d. runturn() 
        1. displays options each round (from UserInterface)
            b. value/type checks the input
            c. returns input
        2. processes input
            a. display new board
            b. check win
        3. does AI turn
            a. say where AI places
            a. display new board
            b. check win
        4. repeat

GameStatus has debugg mode, can toggle at any input with d
Location if needed as a wrapper class for locations

This is a test, ooooo