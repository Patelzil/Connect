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

    }// end runGame

    /* setAnswer
     * purpose - called by the player after the player has determined their move
     * @param col - the col played by the actual human
     *
     */
    public void setAnswer(int col)
    {

    }// setAnswer
}// class MyGame
