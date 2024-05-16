package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import entity.Player;
import util.KeyHandler;

public class GamePanel extends JPanel implements Runnable {
	private static final long serialVersionUID = 1L;

	// SCREEN SETTINGS
	private final int initTileSize = 16; // 16x16 pixels
	private final int scale = 3; // Up-scale rate (for better resolution)
	private final int tileSize = initTileSize * scale; // 48x48 pixels
	private final int maxScreenCol = 11; // Max on-screen tiles in columns
	private final int maxScreenRow = 9; // Max on-screen tiles in rows
	private final int screenWidth = tileSize * maxScreenCol; // 528 pixels (across)
	private final int screenHeight = tileSize * maxScreenRow; // 432 pixels (down)

	// FRAMES PER SECOND
	private final int fps = 60;

	// SYSTEMS / UTILITIES
	private final KeyHandler keyH = new KeyHandler();

	// THREADS
	private Thread gameThread;

	// ASSETS / HOLDERS
	private Player player = new Player(this, keyH);

	// GamePanel Constructor
	public GamePanel() {
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
		this.setFocusable(true);
	}

	// Create and run game thread
	public void startGameThread() {
		gameThread = new Thread(this);
		gameThread.start();
	}

	// Run game loop
	@Override
	public void run() {
		double frameRate = 1000000000 / fps;
		double delta = 0;
		long preTime = System.nanoTime();
		long curTime;
		long timer = 0;
		int drawCount = 0;

		while (gameThread != null) {
			curTime = System.nanoTime();
			delta += (curTime - preTime) / frameRate;
			timer += (curTime - preTime);
			preTime = curTime;

			if (delta >= 1) {
				update();
				repaint();
				delta--;
				drawCount++;
			}
			if (timer >= 1000000000) {
				System.out.println("FPS: " + drawCount);
				drawCount = 0;
				timer = 0;
			}
		}
	}

	// Update game variables
	public void update() {
		player.update();
	}

	// Paint frame
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D frame = (Graphics2D) g;

		player.draw(frame);

		frame.dispose();
	}

	// GETTER / SETTER METHODS
	public int getTileSize() {
		return tileSize;
	}
}
