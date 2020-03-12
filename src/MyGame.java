import java.util.Random;

public class MyGame implements GameLogic
{
    private AIPlayer computer;
    private HumanPlayer human;
    private static boolean isHuman; // if true then its human's turn to play
                                    // if false then AI's turn

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

        // chooses which player starts the game
        choosePlayer();

        // todo:determine if the player wins or if there is a draw and then inform the player
    }// end runGame

    /* setAnswer
     * purpose - called by the player after the player has determined their move
     *          helps in alternating the players.
     * @param col - the col played by the actual human
     *
     */
    public void setAnswer(int col)
    {
        if(isHuman) // since the human played its now AI's turn
        {
            isHuman = false;
            computer.lastMove(col);
        }
        else // AI played and now its human's turn
        {
            isHuman = true;
            human.lastMove(col);

        }
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

    /* choosePlayer
     * Purpose -1) randomly selects which player will start the game
     *          2) set the value of isHuman accordingly
     *          2) send -1 as the lastMove to the selected player
     */
    private void choosePlayer()
    {
        Random ran = new Random();
        int num = ran.nextInt(2); // randomly chooses 0 or 1

        if(num == 0) // AI chosen
        {
            isHuman = false;
            System.out.println("AI player started the game.");
            computer.lastMove(-1); // -1 since its the first move of the game
        }
        else // Human chosen
        {
            isHuman = true;
            System.out.println("Human players starts the game.");
            human.lastMove(-1); // -1 since its the first move of the game
        }
    }// end choosePlayer
}// class MyGame
