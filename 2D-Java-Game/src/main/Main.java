package main;

import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
		// Create JFrame to house game
		JFrame window = new JFrame();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.setTitle("2D Java Game");

		// Add GamePanel to JFrame
		GamePanel gamePanel = new GamePanel();
		window.add(gamePanel);
		window.pack();

		// Display JFrame at center of user window
		window.setLocationRelativeTo(null);
		window.setVisible(true);

		// Setup game variables
		gamePanel.setupGame();
		// Begin Game
		gamePanel.startGameThread();
	}
}
