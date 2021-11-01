import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Player represents a player playing a tic-tac-toe game.
 * The data fields and method of the class allow the Player to make moves
 * on the board, and determine if the player, or their opponent won.
 *
 * @author      Rohinesh Ram
 * @version     1.0
 * @since       September 19, 2021
 */
public class Player {

    private String name;
    private Board board;
    private char mark;
    private Player opponent;
    private PrintWriter socketOut;
    private BufferedReader socketIn;

    /**
     * Class constructor used to set the players name and
     * the mark they will use.
     * @param mark mark of the player
     */
    public Player(Socket socket, char mark) {

        setMark(mark);

        try{
            socketIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            socketOut = new PrintWriter(socket.getOutputStream(), true);
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    /**
     * Method that allows a Player to make a full turn.
     * The Player makes a move and displays the Board. If an end game scenario is reached, the method returns.
     * If no end game is reached, the turn is passed to the Player's opponent.
     * @throw IOException if an input or output exception occurs
     */
    public void play() throws IOException {

        // player makes move
        this.makeMove();
        // show the board
        socketOut.println(board);
        // show board to opponent
        opponent.getSocketOut().println(board);
        // game was a tie
        if(board.isFull()){
            socketOut.println("Game is a tie");
            opponent.getSocketOut().println("Game is a tie");
            socketOut.println("QUIT");
            opponent.getSocketOut().println("QUIT");
            return;
        }
        // player o won
        if(board.oWins()){
            socketOut.println("Player O wins!");
            opponent.getSocketOut().println("Player O wins!");
            socketOut.println("QUIT");
            opponent.getSocketOut().println("QUIT");
            return;
        }
        // player x won
        if(board.xWins()) {
            socketOut.println("Player X wins!");
            opponent.getSocketOut().println("Player X wins!");
            socketOut.println("QUIT");
            opponent.getSocketOut().println("QUIT");
            return;
        }
        // game still on, pass turn to next player
        this.opponent.play();
    }

    /**
     * The method that allows the Player to place a mark on the Board.
     */
    public void makeMove() throws IOException {

        socketOut.println(this.getName() + "'s turn.");
        opponent.getSocketOut().println(this.getName()+ "'s turn.");

        sendString("Enter row: ");
        int row = Integer.parseInt(socketIn.readLine());
        sendString("Enter column: ");
        int col = Integer.parseInt(socketIn.readLine());

        board.addMark(row, col, this.mark);
    }

    /**
     * Sets the Player's opponent as the specified Player object.
     * @param player opponent of the Player
     */
    public void setOpponent(Player player){
        this.opponent = player;
    }

    /**
     * Sets the players board as the specified Board object.
     * @param theBoard - the board object for the Player
     */
    public void setBoard(Board theBoard) {
        this.board = theBoard;
    }

    /**
     * Returns the name of the Player.
     * @return the Player's name
     */
    public String getName() {
        return name;
    }

    /**
     * Method to get the output stream to the client side.
     * @return
     */
    public PrintWriter getSocketOut() {
        return socketOut;
    }

    /**
     * Helper function to send a string to the client. Uses the null character
     * protocol to designate end of message.
     * @param toSend
     */
    private void sendString(String toSend){
        socketOut.println(toSend + " \0");
        socketOut.flush();
    }

    /**
     * Gets the players name from the client side
     */
    public void getPlayerName(){

        try {
            sendString("Please enter the name of '" + this.mark + "' player:");
            name = socketIn.readLine();
            while(name == null){
                sendString("Please try again: ");
                name = socketIn.readLine();
            }
            setName(name);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Sets the name of the Player to the specified name.
     * @param name the name of the Player
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the Player's mark as the specified mark.
     * @param mark the mark of the Player
     */
    public void setMark(char mark) {
        this.mark = mark;
    }
}
