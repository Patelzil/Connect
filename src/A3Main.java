//-----------------------------------------
// NAME		        : Patel Zil
// STUDENT NUMBER	: 7876456
// COURSE		    : COMP 2150
// INSTRUCTOR	    : Mike Domaratzki
// ASSIGNMENT	    : assignment 3
// QUESTION     	: question 1
//
// REMARKS: enable a user to play a game of “Connect”, where players
//          alternate dropping coloured playing pieces into a grid to
//          get four of their pieces in a line (horizontal, diagonal or vertical)
//          to win
//
//-----------------------------------------

public class A3Main
{
    public static void main(String[] args)
    {
        System.out.println("The game starts...");

        MyGame theGame = new MyGame();
        theGame.runGame();

        System.out.println("End of Processing");
    }// end main
}// class A3Main
