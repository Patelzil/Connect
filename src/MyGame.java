import java.util.Random;

public class MyGame implements GameLogic
{
    private AIPlayer computer;
    private HumanPlayer human;
    private Status status; // if ONE then its human's turn to play
                           // if TWO then AI's turn
    private Status[][] myBoard; // to keep track of our board/game

    public MyGame()
    {
        computer = new AIPlayer();
        human = new HumanPlayer();
    }

    /* runGame
     * Purpose - implements the game logic
     */
    public void runGame()
    {
        // randomly generates the board size(btn 6-12)
        // and send the information to the players
        int size = generateBoardSize();
        initializeBoard(size);
        computer.setInfo(size, this);
        human.setInfo(size,this);

        // chooses which player starts the game
        choosePlayer();

        // todo:determine if the player wins or if there is a draw and then inform the player
    }// end runGame

    /* setAnswer
     * purpose - called by the player after the player has determined their move
     *          helps in alternating the players.
     * @param col - the col played by the actual human
     */
    public void setAnswer(int col)
    {

        int row = findRow(col);
        if(status == Status.ONE) // since the human played its now AI's turn
        {
            myBoard[row][col] = Status.ONE; // set to human player at that location on the board
            findWinner(Status.ONE, row, col); // check for the winner
            status = Status.TWO;
            computer.lastMove(col);
        }
        else // AI played and now its human's turn
        {
            myBoard[row][col] = Status.TWO; // set to AI player at that location on the board
            findWinner(Status.TWO, row, col); // check for the winner
            status = Status.ONE;
            human.lastMove(col);
        }
    }// setAnswer

    /* findWinner
     * Purpose - search for the winner
     *    (by going through the entire board from bottom to up
     *    to ensure minimal iterations over the whole board as
     *    the board gets filled up from down)
     *    and let the players know if a winner is found.
     *    4 cases: Vertically, horizontally, diagonal towards left, diagonal towards right
     */
    private void findWinner(Status s, int row, int col)
    {
        // check vertically (from bottom of the board going up)
        checkVertically(s,row,col);
        // check horizontally
        checkHorizontally(s);
        // check for diagonal(moving upwards towards the left)
        checkLeftDiagonal(s);
        // check for diagonal (moving upwards towards the right)
        checkRightDiagonal(s);
    }// end findWinner

    /* checkVertically
     * Purpose - checks the board vertically (from bottom of the board going up)
     *
     */
    private void checkVertically(Status st, int row, int col)
    {
        for (int i = myBoard.length-1; i >= 3; i--)
        {
            for (int j = myBoard.length-1; j >= 0; j--)
            {
                if(myBoard[i][j] == st && myBoard[i][j] == myBoard[i-1][j]
                        && myBoard[i][j]== myBoard[i-2][j] && myBoard[i][j] == myBoard[i-3][j])
                {
                    human.gameOver(st);
                    computer.gameOver(st);
                }
            }
        }
    }// end checkVertically

    /* checkHorizontally
     * Purpose -  checks the board horizontally for the winner
     *
     */
    private void checkHorizontally(Status st)
    {
        for (int i = myBoard.length-1; i >= 0; i--)
        {
            for (int j = myBoard.length-1; j >= 3; j--)
            {
                if(myBoard[i][j] == st && myBoard[i][j] == myBoard[i][j-1]
                        && myBoard[i][j]== myBoard[i][j-2] && myBoard[i][j] == myBoard[i][j-3])
                {
                    human.gameOver(st);
                    computer.gameOver(st);
                }
            }
        }
    }// end checkHorizontally

    /* checkLeftDiagonal
     * Purpose - check the board diagonally(moving upwards towards the left)
     *
     */
    private void checkLeftDiagonal(Status st)
    {
        for (int i = myBoard.length-1; i >= 3; i--)
        {
            for (int j = myBoard.length-1; j >= 3; j--)
            {
                if(myBoard[i][j] == st && myBoard[i][j] == myBoard[i-1][j-1]
                        && myBoard[i][j]== myBoard[i-2][j-2] && myBoard[i][j] == myBoard[i-3][j-3])
                {
                    human.gameOver(st);
                    computer.gameOver(st);
                }
            }
        }
    }// end checkLeftDiagonal

    /* checkRightDiagonal
     * Purpose - check the board diagonally(moving upwards towards the right)
     *
     */
    private void checkRightDiagonal(Status st)
    {
        for (int i = myBoard.length-1; i >= 3; i--)
        {
            for (int j = myBoard.length-3; j >= 0; j--)
            {
                if(myBoard[i][j] == st && myBoard[i][j] == myBoard[i-1][j+1]
                        && myBoard[i][j]== myBoard[i-2][j+2] && myBoard[i][j] == myBoard[i-3][j+3])
                {
                    human.gameOver(st);
                    computer.gameOver(st);
                }
            }
        }
    }// end checkRightDiagonal

    /* findRow
     * Purpose - finds the position of last play when it is dropped in a column.
     * @param col - the column where the piece is dropped
     * @return the row where the piece lands
     */
    private int findRow(int col)
    {
        int row = 0;
        while (row < myBoard.length && myBoard[row][col] == Status.NEITHER)
        {
            row ++;
        }
        return row-1;
    }// end findRow

    /* generateBoardSize
     * Purpose - randomly generates the board size
     * @return the size of the board
     */
    private int generateBoardSize()
    {
        int max = 12;
        int min = 6;
        Random ran = new Random();
        return ( ran.nextInt((max-min) + 1) + min );
    }// end generateBoardSize

    /* choosePlayer
     * Purpose -1) randomly selects which player will start the game
     *          2) set the value of isHuman accordingly
     *          2) send -1 as the lastMove to the selected player
     */
    private void choosePlayer()
    {
        int max = 2;
        int min = 1;
        Random ran = new Random();
        int num = ran.nextInt((max-min) + 1) + min; // randomly chooses 1 or 2

        if(num == 1)// Human chosen
        {
            status = Status.ONE;
            System.out.println("Human players starts the game.");
            human.lastMove(-1); // -1 since its the first move of the game
        }
        else if(num==2) // AI chosen
        {
            status = Status.TWO;
            System.out.println("AI player started the game.");
            computer.lastMove(-1); // -1 since its the first move of the game
        }
    }// end choosePlayer

    /* initializeBoard
     * Purpose - initializes the board and sets all
     *          the locations to Status.NEITHER
     */
    private void initializeBoard(int size)
    {
        myBoard = new Status[size][size];
        for (int i = 0; i < size ; i++)
        {
            for (int j = 0; j < size; j++)
            {
                myBoard[i][j] = Status.NEITHER;
            }
        }
    }// end initializeBoard
}// class MyGame
