package game;

public class Tile {
	
	private int EMPTY_TILE = 0;
	private int number;
	
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
	
	public int nextNumber() {
		return 2*number;
	}
	
	public void clearTile() {
		this.number = EMPTY_TILE;
	}
	
}
