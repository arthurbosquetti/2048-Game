package game;

import java.util.Random;

import view.TilePrinter;

/**
* The Board class is responsible for storing, generating, and displaying the @see Tiles.
* 
* @author Arthur Bosquetti
* 
*/
public class Board {
	
	private Random random;
	private Tile[][] board;
	private final int boardSize;
	private int score;
	private int tileCount;
	
	private TilePrinter tilePrinter;
	
	public Board(int boardSize) {
		this.boardSize = boardSize;
		this.random = new Random();
		this.tilePrinter = new TilePrinter();
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
		int newNumber = generateNewNumber();
		int row = random.nextInt(boardSize);
		int column = random.nextInt(boardSize);
				
		while(!board[row][column].isEmpty()) {
			row = random.nextInt(boardSize);
			column = random.nextInt(boardSize);
		}
	
		board[row][column].setNumber(newNumber);
		tileCount += 1;
	}
	
	/**
	 * This method creates a new number (2 or 4) with 90% chance of the output being a 2 and 10%
	 * chance of it being a 4.
	 * 
	 * @return the new number.
	 */
	private int generateNewNumber() {
		if (random.nextDouble(1) < 0.9) return 2;
		else return 4;
	}
	
	public void displayBoard() {
		for (int row = 0; row < boardSize; row++) {
			for (int column = 0; column < boardSize; column++) {
				tilePrinter.printColoredTile(board[row][column]);
			}
			System.out.println("\n");
		}
	}
	
	public int getSize() {
		return boardSize;
	}
	
	public Tile getTile(int row, int column) {
		return board[row][column];
	}
	
	public void setScore(int score) {
		this.score = score;
	}
	
	public int getScore() {
		return score;
	}
	
	public void setTileCount(int tileCount) {
		this.tileCount = tileCount;
	}
	
	public int getTileCount() {
		return tileCount;
	}
	
	public boolean isFull() {
		return (tileCount == boardSize*boardSize);
	}
}
