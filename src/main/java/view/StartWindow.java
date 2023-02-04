package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import java.awt.BorderLayout;

public class StartWindow {
	
	private JFrame startWindow;
	
	private int boardSize;
	
	
	public StartWindow() {
		createWindow();
		
		startWindow.pack();
		startWindow.setLocationRelativeTo(null);
		startWindow.setVisible(true);
	}
	
	private void createWindow() {
		startWindow = new JFrame("Starting Page");
		startWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				
		JTextArea welcomeTextArea = new JTextArea();
		welcomeTextArea.setText("Welcome to 2048!\n" + "Please enter the grid size for the game!");
		welcomeTextArea.setEditable(false);
		
		JSlider boardSizeSlider = new JSlider(4, 8, 4);
		boardSizeSlider.setMajorTickSpacing(1);
		boardSizeSlider.setPaintTicks(true);
		boardSizeSlider.setPaintLabels(true);
		boardSizeSlider.setSnapToTicks(true);
		
		JButton completeButton = new JButton("Start Game!");
		completeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				boardSize = boardSizeSlider.getValue();
				System.out.println("The board size is " + boardSize);
			}
		});

		startWindow.add(welcomeTextArea, BorderLayout.PAGE_START);
		startWindow.add(boardSizeSlider, BorderLayout.CENTER);
		startWindow.add(completeButton, BorderLayout.PAGE_END);
	}
}
