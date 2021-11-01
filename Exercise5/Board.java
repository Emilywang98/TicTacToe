import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Arrays;

/**
 * Board represents the board for a tic-tac-toe game.
 * Board's methods and data fields are used to display the board, allow players
 * to add marks, and determine when the board is full or when a player wins.
 *
 * @author		MM
 * @version 	1.0
 * @since  		September 17, 2021
 */
public class Board implements Constants {

	private char theBoard[][];
	private int markCount;


	/**
	 * Class constructor used to initialize a blank board.
	 */
	public Board() {

		markCount = 0;
		theBoard = new char[3][];
		for (int i = 0; i < 3; i++) {
			theBoard[i] = new char[3];
			for (int j = 0; j < 3; j++)
				theBoard[i][j] = SPACE_CHAR;
		}
	}

	/**
	 * Return the character in a specified square of the board.
	 * @param row the row of the mark
	 * @param col the column of the mark
	 * @return the mark in the square
	 */
	public char getMark(int row, int col) {
		return theBoard[row][col];
	}

	/**
	 * Return True if all 9 squares in the Board are filled.
	 * @return True if all squares are filled
	 */
	public boolean isFull() {
		return markCount == 9;
	}

	/**
	 * Returns True if the player using the 'X' mark won.
	 * @return True if player using 'X' mark won
	 */
	public boolean xWins() {
		if (checkWinner(LETTER_X) == 1)
			return true;
		else
			return false;
	}

	/**
	 * Returns True if the player using the 'O' mark won.
	 * @return True if player using 'O' mark won
	 */
	public boolean oWins() {
		if (checkWinner(LETTER_O) == 1)
			return true;
		else
			return false;
	}

	/**
	 * Adds a specified mark to the specified square.
	 * @param row the row to add the mark
	 * @param col the column to add the mark
	 * @param mark the mark to be added
	 */
	public void addMark(int row, int col, char mark) {
		
		theBoard[row][col] = mark;
		markCount++;
	}

	/**
	 * Clears all marks from the board.
	 */
	public void clear() {
		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++)
				theBoard[i][j] = SPACE_CHAR;
		markCount = 0;
	}

	/**
	 * Checks if a player using the specified mark won.
	 * @param mark the mark (X or O) for the player
	 * @return 1 if a mark won, 0 if a mark did not
	 */
	int checkWinner(char mark) {
		int row, col;
		int result = 0;

		// Check each row for mark in all three columns
		for (row = 0; result == 0 && row < 3; row++) {
			int row_result = 1;
			for (col = 0; row_result == 1 && col < 3; col++)
				if (theBoard[row][col] != mark)
					row_result = 0;
			if (row_result != 0)
				result = 1;
		}

		// Check each column for mark in all three rows
		for (col = 0; result == 0 && col < 3; col++) {
			int col_result = 1;
			for (row = 0; col_result != 0 && row < 3; row++)
				if (theBoard[row][col] != mark)
					col_result = 0;
			if (col_result != 0)
				result = 1;
		}

		// check one diagonal
		if (result == 0) {
			int diag1Result = 1;
			for (row = 0; diag1Result != 0 && row < 3; row++)
				if (theBoard[row][row] != mark)
					diag1Result = 0;
			if (diag1Result != 0)
				result = 1;
		}

		// checks the other diagonal
		if (result == 0) {
			int diag2Result = 1;
			for (row = 0; diag2Result != 0 && row < 3; row++)
				if (theBoard[row][3 - 1 - row] != mark)
					diag2Result = 0;
			if (diag2Result != 0)
				result = 1;
		}
		return result;
	}

	/**
	 * Displays the column headers of the board.
	 */
	String displayColumnHeaders() {
		String columnHeaders = "";
		columnHeaders+= "          ";
		for (int j = 0; j < 3; j++)
			columnHeaders += "|col " + j;
		columnHeaders += "\n";
		return columnHeaders;
	}

	/**
	 * Displays hyphens on the board.
	 */
	String addHyphens() {
		String hyphens = "          ";
		for (int j = 0; j < 3; j++)
			hyphens += "+-----";
		hyphens += "+\n";
		return hyphens;
	}

	/**
	 * Displays spaces on the board.
	 */
	String addSpaces() {
		String spaces = "";
		spaces += "          ";
		for (int j = 0; j < 3; j++)
			spaces+= "|     ";
		spaces += "|\n";

		return spaces;
	}

	/**
	 * Method to print the board as a string.
	 * @return
	 */
	@Override
	public String toString() {
		String board = "";
		board += displayColumnHeaders();
		board += addHyphens();
		for (int row = 0; row < 3; row++) {
			board += addSpaces();
			board += "    row " + row + ' ';
			for (int col = 0; col < 3; col++)
				board += "|  " + getMark(row, col) + "  ";
			board += "|\n";
			board += addSpaces();
			board += addHyphens();
		}
		return board + "\0";
	}
}
