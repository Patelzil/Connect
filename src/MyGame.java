import java.util.Random;

public class MyGame implements GameLogic
{
    private AIPlayer computer;
    private HumanPlayer human;

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
        computer.setInfo(size, this);
        human.setInfo(size,this);

        // todo: determines which player starts the game

        // todo: alternate between the players

        // todo:determine if the player wins or if there is a draw and then inform the player
    }// end runGame

    /* setAnswer
     * purpose - called by the player after the player has determined their move
     * @param col - the col played by the actual human
     *
     */
    public void setAnswer(int col)
    {
        // todo: write code
    }// setAnswer

    /* generateBoardSize
     * Purpose - randomly generates the board size and returns it
     *
     */
    private int generateBoardSize()
    {
        int max = 12;
        int min = 6;
        Random ran = new Random();
        return ( ran.nextInt((max-min) + 1) + min );
    }// end generateBoardSize
}// class MyGame
