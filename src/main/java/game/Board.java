package game;

import java.util.Random;

/**
* The Board class is responsible for storing the @see Tiles and moving them around whenever moves
* are made.
* 
* @author 	Arthur Bosquetti
* @version	1.0 - Command line version
* @since	2023-01-30
*/
public class Board {
	
	private Random random;
	private Tile[][] board;
	private int boardSize;
	
	public Board(int boardSize) {
		this.boardSize = boardSize;
		random = new Random();
		createBoard();
		placeNewNumber();
		placeNewNumber();
		displayBoard();
	}
	
	private void createBoard() {
		board = new Tile[boardSize][boardSize];
		for (int i = 0; i < boardSize; i++) {
			for (int j = 0; j < boardSize; j++) {
				board[i][j] = new Tile();
			}
		}
	}
	
	/**
	 * This method places a new number on the board.
	 */
	public void placeNewNumber() {
		int newNumber = createNewNumber();
		int row = random.nextInt(boardSize);
		int column = random.nextInt(boardSize);
		
		while(!board[row][column].isEmpty()) {
			row = random.nextInt(boardSize);
			column = random.nextInt(boardSize);
		}
	
		board[row][column].setNumber(newNumber);
	}
	
	/**
	 * This method creates a new number (2 or 4) with 90% chance of the output being a 2 and 10%
	 * chance of it being a 4.
	 * 
	 * @return the new number.
	 */
	private int createNewNumber() {
		if (random.nextDouble(1) < 0.9) return 2;
		else return 4;
	}
	
	public void displayBoard() {
		for (int row = 0; row < boardSize; row++) {
			for (int column = 0; column < boardSize; column++) {
				System.out.print(" " + board[row][column] + " ");
			}
			System.out.println("\n");
		}
	}

	public boolean makeMove(String move) {
		switch (move) {
		case "up":
			return moveUp();
		case "down":
			return moveDown();
		case "left":
			return moveLeft();
		case "right":
			return moveRight();
		default:
			System.out.println("Invalid move choice. Please try again!");
			return false;
		}
	}
	
	/**
	 * Moves all tiles up starting from the top-most rows.
	 * 
	 * @return hasUpdated indicates whether any tile has moved on the board.
	 */
	private boolean moveUp() {
		int rowChange = -1;
		int columnChange = 0;
		boolean hasUpdated = false;
		
		for (int row = 1; row < boardSize; row ++) {
			for (int column = 0; column < boardSize; column ++) {
				if (moveTile(row, column, rowChange, columnChange)) hasUpdated = true;
			}
		}
		return hasUpdated;
	}
	
	/**
	 * Moves all tiles down starting from the bottom-most rows.
	 * 
	 * @return hasUpdated indicates whether any tile has moved on the board.
	 */
	private boolean moveDown() {
		int rowChange = 1;
		int columnChange = 0;
		boolean hasUpdated = false;
		
		for (int row = boardSize - 2; row >= 0; row --) {
			for (int column = 0; column < boardSize; column ++) {
				if (moveTile(row, column, rowChange, columnChange)) hasUpdated = true;
			}
		}
		return hasUpdated;
	}
	
	/**
	 * Moves all tiles left starting from the left-most columns.
	 * 
	 * @return hasUpdated indicates whether any tile has moved on the board.
	 */
	private boolean moveLeft() {
		int rowChange = 0;
		int columnChange = -1;
		boolean hasUpdated = false;

		for (int row = 0; row < boardSize; row ++) {
			for (int column = 1; column < boardSize; column ++) {
				if (moveTile(row, column, rowChange, columnChange)) hasUpdated = true;
			}
		}
		return hasUpdated;
	}
	
	/**
	 * Moves all tiles left starting from the right-most columns.
	 * 
	 * @return hasUpdated indicates whether any tile has moved on the board.
	 */
	private boolean moveRight() {
		int rowChange = 0;
		int columnChange = 1;
		boolean hasUpdated = false;
		
		for (int row = 0; row < boardSize; row ++) {
			for (int column = boardSize - 2; column >= 0; column --) {
				if (moveTile(row, column, rowChange, columnChange)) hasUpdated = true;
			}
		}
		return hasUpdated;
	}
	
	/**
	 * This method handles the logic of moving a tile around the board. A tile moves in the desired
	 * direction while the next tile is empty and in bounds, and merges with its neighbor if they
	 * have the same number.
	 * 
	 * @param  currentRow		the current row of the tile.
	 * @param  currentColumn	the current column of the tile.
	 * @param  rowChange		the increment that guides the vertical movement of the tile.
	 * @param  columnChange		the increment that guides the horizontal movement of the tile.
	 * @return hasMoved			indicates whether the tile has moved on the board.
	 */
	private boolean moveTile(int currentRow, int currentColumn, int rowChange, int columnChange) {
		int nextRow = currentRow + rowChange;
		int nextColumn = currentColumn + columnChange;
		boolean hasMoved = false;
		
		while (board[nextRow][nextColumn].isEmpty()) {
			board[nextRow][nextColumn].setNumber(board[currentRow][currentColumn].getNumber());
			board[currentRow][currentColumn].clearTile();
			hasMoved = true;
			
			if (!isInBounds(nextRow + rowChange, nextColumn + columnChange)) break;
			
			// Update row and column of the tile.
			currentRow = nextRow;
			currentColumn = nextColumn;
			nextRow += rowChange;
			nextColumn += columnChange;				
			
		}
		if (!board[nextRow][nextColumn].isEmpty() && 
				board[nextRow][nextColumn].equals(board[currentRow][currentColumn])) {
			board[nextRow][nextColumn].setNumber(board[currentRow][currentColumn].nextNumber());
			board[currentRow][currentColumn].clearTile();
		}	
		
		return hasMoved;
	}
	
	private boolean isInBounds(int row, int column) {
		return (0 <= row & row < boardSize & 0 <= column & column < boardSize);
	}	
	
	
}
