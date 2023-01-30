package game;

import java.util.Scanner;

public class Game {
	
	private static Board board;

	public static void main(String[] args) {
		board = new Board(4);
		Scanner scanner = new Scanner(System.in);
		String move;

		while(true) {
			System.out.print("Please enter your move choice (up, down, left, right): ");
			move = scanner.nextLine();
			if (board.makeMove(move.toLowerCase())) {
				System.out.println("\n");
				board.addNewNumber();
				board.displayBoard();				
			}
		
		}
	}
}