
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *	Game repesents a tic-tac-toe game.
 * 	Game implements the Constants interface.
 * 	@author		MM
 * 	@version 	1.0
 * 	@since		September 17, 2021
 */
public class Game implements Constants, Runnable {

	private Board theBoard;
	private Referee theRef;

	/**
	 * Class constructor.
	 */
    public Game() {
		theBoard  = new Board();
	}

	/**
	 * The method used to set a specified Referee object for the game.
	 * @param r the referee to be appointed
	 * @throws IOException if an input or output exception occurs
	 */
    public void appointReferee(Referee r) throws IOException {
        theRef = r;
		theRef.setBoard(this.theBoard);
		theRef.getxPlayer().setBoard(theBoard);
		theRef.getoPLayer().setBoard(theBoard);
    }

	/**
	 * Method to run the game.
	 */
	@Override
	public void run() {
		try {
			theRef.runTheGame();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
