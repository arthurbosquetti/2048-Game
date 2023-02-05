package game;

import java.util.Scanner;

import view.StartWindow;

/**
 * The Game class contains the game loop.
 * 
 * @author Arthur Bosquetti
 *
 */
public class Game {
	
	private static Board board;
	private static Mover mover;

	public static void main(String[] args) {
		StartWindow startWindow = new StartWindow();
		
//		board = new Board(4);
//		mover = new Mover(board);
//		Scanner scanner = new Scanner(System.in);
//		String move;
//
//		while(true) {
//			if (!mover.hasAvailableMoves()) break;
//			System.out.print("Please enter your move choice (up, down, left, right): ");
//			move = scanner.nextLine();
//			makeMove(move);
//		}
//		System.out.println("Game over!");
	}
	
//	private static void makeMove(String move) {
//		if (mover.makeMove(move.toLowerCase())) {
//			board.placeNewNumber();
//			System.out.println("Score: " + board.getScore() + "\n");
//			board.displayBoard();				
//		}
//	}
}