package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import game.Board;
import game.Mover;

public class GameWindow {

	private JFrame gameWindow;
	private JPanel gridPanel;
	private int boardSize;
	private Board board;
	private Mover mover;
	
	
	public GameWindow(int boardSize) {
		this.boardSize = boardSize;
		this.board = new Board(boardSize);
		this.mover = new Mover(board);
		createWindow();
		includeKeyListener();
		
		gameWindow.pack();
		gameWindow.setLocationRelativeTo(null);
		gameWindow.setVisible(true);
		gameWindow.setFocusable(true);
		
		gameWindow.requestFocusInWindow();
	}

	private void createWindow() {
		gameWindow = new JFrame("2048");
		gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				
		JTextArea informationTextArea = new JTextArea();
		informationTextArea.setText("How to play:\n" + 
				"Combine tiles containing the same numbers until they reach the number 2048.\n" +
				"Use the arrow keys to move the tiles around the board.");
		informationTextArea.setEditable(false);
		
		gridPanel = new JPanel(new GridLayout(boardSize, boardSize));
		fillGridPanel();
		
		gameWindow.add(informationTextArea, BorderLayout.PAGE_START);
		gameWindow.add(gridPanel, BorderLayout.CENTER);
	}
	
	private void fillGridPanel() {
		for (int row = 0; row < boardSize; row++) {
			for (int column = 0; column < boardSize; column++) {
				int number = board.getTile(row, column).getNumber();
				JTextArea tileTextArea = new JTextArea(Integer.toString(number));
				tileTextArea.setEditable(false);
				tileTextArea.setPreferredSize(new Dimension(20, 40));
				gridPanel.add(tileTextArea);
			}
		}
	}
	
	private void includeKeyListener() {		
		gameWindow.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {}
			
			@Override
			public void keyReleased(KeyEvent e) {}
			
			@Override
			public void keyPressed(KeyEvent e) {
				boolean hasUpdatedBoard = false;
				System.out.println("A key has been PRESSED!");
				switch (e.getKeyCode()) {
				case KeyEvent.VK_UP:
					hasUpdatedBoard = (mover.makeMove("up"));
					break;
				case KeyEvent.VK_DOWN:
					hasUpdatedBoard = (mover.makeMove("down"));
					break;
				case KeyEvent.VK_LEFT:
					hasUpdatedBoard = (mover.makeMove("left"));
					break;
				case KeyEvent.VK_RIGHT:
					hasUpdatedBoard = (mover.makeMove("right"));
					break;
				}
				if (hasUpdatedBoard) {
					board.placeNewNumber();
					board.displayBoard();
					updateGridPanel();
					gameWindow.repaint();
				}
			}
		});
	}
	
	private void updateGridPanel() {
		for (int row = 0; row < boardSize; row++) {
			for (int column = 0; column < boardSize; column++) {
				int number = board.getTile(row, column).getNumber();
				JTextArea tileTextArea = (JTextArea) gridPanel.getComponent(row*boardSize + column);
				tileTextArea.setText((Integer.toString(number)));
			}
		}
	}
	

	
	
}



















