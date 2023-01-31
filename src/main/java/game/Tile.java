package game;

/**
* The Tile class stores a number and does basic set/get operations.
* 
* @author Arthur Bosquetti
* 
*/
public class Tile {
	
	private final int EMPTY_TILE = 0;
	private int number;
	private boolean merged = false;
	
	public Tile() {
		this.number = EMPTY_TILE;
	}
	
	public void setNumber(int newNumber) {
		this.number = newNumber;
	}
	
	public int getNumber() {
		return number;
	}
	
	public boolean isEmpty() {
		return (number == EMPTY_TILE);
	}
	
	@Override
	public String toString() {
		return number + "";
	}
	
	public boolean equals(Tile otherTile) {
		return number == otherTile.getNumber();
	}
	
	public int getNextNumber() {
		return 2*number;
	}
	
	public void clearTile() {
		this.number = EMPTY_TILE;
	}	
	
	public boolean getMerged() {
		return merged;
	}
	
	public void setMerged(boolean merged) {
		this.merged = merged;
	}
}
