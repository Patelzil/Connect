import java.util.Random;

public class MyGame implements GameLogic
{
    private AIPlayer computer;
    private HumanPlayer human;
    private Status status; // if true then its human's turn to play
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
        if(status == Status.ONE) // since the human played its now AI's turn
        {
            status = Status.TWO;
            computer.lastMove(col);
        }
        else // AI played and now its human's turn
        {
            status = Status.ONE;
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
        else // AI chosen
        {
            status = Status.TWO;
            System.out.println("AI player started the game.");
            computer.lastMove(-1); // -1 since its the first move of the game
        }
    }// end choosePlayer
}// class MyGame
