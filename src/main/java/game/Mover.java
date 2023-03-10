package game;

import java.util.ArrayList;

/**
 * This class is responsible for moving @see Tiles and handling the move logic.
 * 
 * @author Arthur Bosquetti
 *
 */
public class Mover {
	
	private Board board;
	private final int boardSize;
	private ArrayList<Tile> mergedTiles;
	
	public Mover(Board board) {
		this.board = board;
		this.boardSize = board.getSize();
	}
	
	public boolean makeMove(String move) {
		mergedTiles = new ArrayList<Tile>();
		boolean hasUpdated = false;
		
		if (move.equals("up")) hasUpdated = moveUp();
		else if (move.equals("down")) hasUpdated = moveDown();
		else if (move.equals("left")) hasUpdated = moveLeft();
		else if (move.equals("right")) hasUpdated = moveRight();
		else System.out.println("Invalid move choice. Please try again!");
		
		clearMergedTags();
		return hasUpdated;
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
	 * direction while the next tile is empty and in bounds, and then it tries to merge with its
	 * neighbor.
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
		
		if (board.getTile(currentRow, currentColumn).isEmpty()) return hasMoved; 
		
		// Move the tile through the empty space of the board.
		while (board.getTile(nextRow, nextColumn).isEmpty()) {
			board.getTile(nextRow, nextColumn).setNumber(board.getTile(currentRow, currentColumn).getNumber());
			board.getTile(currentRow, currentColumn).clearTile();
			hasMoved = true;
			
			if (!isInBounds(nextRow + rowChange, nextColumn + columnChange)) break;
			
			// Update row and column of the tile.
			currentRow = nextRow;
			currentColumn = nextColumn;
			nextRow += rowChange;
			nextColumn += columnChange;				
			
		}
		
		// Try to merge the tile with its neighbor.
		if (mergeTiles(board.getTile(currentRow, currentColumn), board.getTile(nextRow, nextColumn))) hasMoved = true;
		
		return hasMoved;
	}
	
	private boolean isInBounds(int row, int column) {
		return (0 <= row & row < boardSize & 0 <= column & column < boardSize);
	}	
	
	/**
	 * This method handles the logic of merging two tiles, and tagging the new one as 'merged', so 
	 * that no tile merges twice during the same move.
	 * 
	 * @param currentTile	the current tile.
	 * @param nextTile		the next tile - the tile that currentTile will try to merge with.
	 * @return hasMerged 	indicates whether currentTile and nextTile have merged.
	 */
	private boolean mergeTiles(Tile currentTile, Tile nextTile) {
		boolean hasMerged = false;
		if (nextTile.equals(currentTile) && !nextTile.getMerged()) {
			nextTile.setNumber(currentTile.getNextNumber());
			currentTile.clearTile();
			nextTile.setMerged(true);
			mergedTiles.add(nextTile);
			board.setScore(board.getScore() + nextTile.getNumber());
			board.setTileCount(board.getTileCount() - 1);
			hasMerged = true;
		}
		return hasMerged;
	}
	
	/**
	 * This method resets the @see merged attribute of tiles that have it set to true.
	 */
	private void clearMergedTags() {
		for (Tile tile : mergedTiles) {
			tile.setMerged(false);
		}
	}
	
	/**
	 * This method checks whether there are any available moves left on the @see Board. 
	 * 
	 * The algorithm first iterates through the (boardSize - 1)x(boardSize - 1) top-left-most tiles 
	 * of the board and checks whether they have any neighbors on their right or bottom that allows 
	 * merging to occur. Then, it checks if the bottom-most tiles have any neighbors on their right
	 * they can merge with. Finally, it checks for the right-most tiles if they have any bottom
	 * neighbors they can merge with.
	 * 
	 * @return boolean indicating whether there are any available moves left on the board.
	 */
	public boolean hasAvailableMoves() {
		if (!board.isFull()) return true;
		
		System.out.println("Checking for top-left-most tiles..");
		if (findNeighborsTopLeftMostTiles()) return true;
		
		System.out.println("Checking for bottom-most tiles..");
		if (findNeighborsBottomMostTiles()) return true;
		
		
		System.out.println("Checking for right-most tiles..");
		if (findNeighborsRightMostTiles()) return true;
		
		return false;
	}
	
	private boolean findNeighborsTopLeftMostTiles() {
		for (int row = 0; row < boardSize - 1; row++) {
			for (int column = 0; column < boardSize - 1; column++) {
				if (checkRightNeighbor(row, column)) return true;
				if (checkBottomNeighbor(row, column)) return true;
			}
		}
		return false;
	}
	
	private boolean findNeighborsBottomMostTiles() {
		for (int column = 0; column < boardSize - 1; column++) {
			if (checkRightNeighbor(boardSize - 1, column)) return true;
		}
		return false;
	}
	
	private boolean findNeighborsRightMostTiles() {
		for (int row = 0; row < boardSize - 1; row++) {
			if (checkBottomNeighbor(row, boardSize - 1)) return true;
		}
		return false;
	}

	private boolean checkRightNeighbor(int row, int column) {
		return (board.getTile(row, column + 1).equals(board.getTile(row, column)));
	}
	
	private boolean checkBottomNeighbor(int row, int column) {
		return (board.getTile(row + 1, column).equals(board.getTile(row, column)));
	}

}
