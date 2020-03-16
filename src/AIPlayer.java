import java.util.Arrays;
import java.util.Random;

public class AIPlayer implements Player
{
    private int boardSize;
    private GameLogic game;
    private Status[][] AIBoard;

    /* lastMove
     * Purpose - chooses the column to make a move and then sends to the GameLogic
     * @param col - the column of the last move made by the opposite player
     *
     */
    public void lastMove(int col)
    {
        if (col != -1)
        {
            int posn = drop(col);
            AIBoard[posn][col] = Status.ONE; // this is the human's move, so it's ONE.
        }

        int num = playColumn(col);
        while (!verifyCol(num))
        {
            num = playColumn(col);
        }

        int row = drop(num);
        AIBoard[row][num] = Status.TWO;
        game.setAnswer(num);
    }// end lastMove

    /* gameOver
     * Purpose - gives the message to the AI that one of the two players won
     * @param winner - gives the result of the game
     *
     */
    public void gameOver(Status winner) { }// end gameOver


    /* setInfo
     * Purpose - called for each player before the game starts
     *         sends player basic info about the game and initializes the board
     * @param size- dimension of the board with the size parameter
     * @param gl - for the Player to communicate with this class
     *
     */
    public void setInfo(int size, GameLogic gl)
    {
        boardSize = size;
        game = gl;
        AIBoard = new Status[boardSize][boardSize];
        for (Status[] s : AIBoard) {
            Arrays.fill(s, Status.NEITHER);
        }
    }// end setInfo

    /* playColumn
     * Purpose - chooses a column to play at and return it
     *          It must be defensive or offensive or play as it likes
     *          depending on the situation
     * @param col - column the human player played at last
     */
    private int playColumn(int col)
    {
        Random ran = new Random();
        return ( ran.nextInt(boardSize));
//        return 0;
    }// end playColumn

    /**
     * verifyCol - private helper method to determine if an integer is a valid
     * column that still has spots left.
     * @param col - integer (potential column number)
     * @return - is the column valid?
     */
    private boolean verifyCol(int col) {
        return (col >= 0 && col < AIBoard[0].length && AIBoard[0][col] == Status.NEITHER);
    }

    /**
     * drop - a private helper method that finds the position of a marker
     * when it is dropped in a column.
     * @param col the column where the piece is dropped
     * @return the row where the piece lands
     */
    private int drop(  int col)
    {
        int posn = 0;
        while (posn < AIBoard.length && AIBoard[posn][col] == Status.NEITHER)
        {
            posn ++;
        }
        return posn-1;
    }
}//class AIPlayer
