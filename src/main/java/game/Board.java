package game;

import java.util.Random;

public class Board {
	
	private Random random;
	private Tile[][] board;
	private int boardSize;
	
	public Board(int boardSize) {
		this.boardSize = boardSize;
		random = new Random();
		createBoard();
		addNewNumber();
		addNewNumber();
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
	
	public void addNewNumber() {
		int newNumber = createNewNumber();
		int row = random.nextInt(boardSize);
		int column = random.nextInt(boardSize);
		
		while(!board[row][column].isEmpty()) {
			row = random.nextInt(boardSize);
			column = random.nextInt(boardSize);
		}
	
		board[row][column].setNumber(newNumber);
	}
	
	private int createNewNumber() {
		if (random.nextDouble(1) < 0.9) return 2;
		else return 4;
	}
	
	public void displayBoard() {
		for (int row = 0; row < boardSize; row++) {
			printRow(row);
			System.out.println("\n");
		}
	}
	
	private void printRow(int row) {
		for (int column = 0; column < boardSize; column++) {
			System.out.print(" " + board[row][column] + " ");
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
	
	private boolean moveTile(int currentRow, int currentColumn, int rowChange, int columnChange) {
		int nextRow = currentRow + rowChange;
		int nextColumn = currentColumn + columnChange;
		boolean hasMoved = false;
		
		while (board[nextRow][nextColumn].isEmpty()) {
			board[nextRow][nextColumn].setNumber(board[currentRow][currentColumn].getNumber());
			board[currentRow][currentColumn].clearTile();
			hasMoved = true;
			
			if (isInBounds(nextRow + rowChange, nextColumn + columnChange)) {
				currentRow = nextRow;
				currentColumn = nextColumn;
				nextRow += rowChange;
				nextColumn += columnChange;				
			} else break;
		}
		if (!board[nextRow][nextColumn].isEmpty() && board[nextRow][nextColumn].equals(board[currentRow][currentColumn])) {
			board[nextRow][nextColumn].setNumber(board[currentRow][currentColumn].nextNumber());
			board[currentRow][currentColumn].clearTile();
		}	
		
		return hasMoved;
	}
	
	private boolean isInBounds(int row, int column) {
		return (0 <= row & row < boardSize & 0 <= column & column < boardSize);
	}	
	
	
}
