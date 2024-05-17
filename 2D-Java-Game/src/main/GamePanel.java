package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import entity.Player;
import object.Obj;
import util.AssetHandler;
import util.CollisionHandler;
import util.KeyHandler;
import util.SoundHandler;
import util.TileHandler;

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

	// MAP SETTINGS
	private final int maxWorldCol = 21; // Number of column tiles on map
	private final int maxWorldRow = 21; // Number of row tiles on map

	// FRAMES PER SECOND
	private final int fps = 60;

	// SYSTEMS / UTILITIES
	private final AssetHandler assetH = new AssetHandler(this);
	private final CollisionHandler collisionH = new CollisionHandler(this);
	private final KeyHandler keyH = new KeyHandler();
	private final SoundHandler soundH = new SoundHandler();
	private final TileHandler tileH = new TileHandler(this);

	// THREADS
	private Thread gameThread;

	// ASSETS / HOLDERS
	private Player player = new Player(this, keyH); // Player character
	private Obj[] objArray = new Obj[10]; // Objects to display

	// GamePanel Constructor
	public GamePanel() {
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
		this.setFocusable(true);
	}

	// Set up game assets
	public void setupGame() {
		// Initialize objects
		assetH.setObject();

		// Start music
		playMusic(0);
	}

	// Create and run game thread
	public void startGameThread() {
		gameThread = new Thread(this);
		gameThread.start();
	}

	// Run game loop
	@Override
	public void run() {
		double frameRate = 1000000000 / fps; // Time of one frame (1/60th of a second)
		double delta = 0; // Difference in previous and current time
		long preTime = System.nanoTime(); // Previous time
		long curTime; // Current time
		long timer = 0; // Seconds (time) timer
//		int drawCount = 0; // Number of draws within a second (FPS)

		// While game thread is running
		while (gameThread != null) {
			// Get current time
			curTime = System.nanoTime();
			// Track difference from previous to current time
			delta += (curTime - preTime) / frameRate;
			// Track time passed overall
			timer += (curTime - preTime);
			// Make current time the previous time
			preTime = curTime;

			// If time difference greater than one frame
			// update(), repaint()
			if (delta >= 1) {
				update();
				repaint();
				delta--;
//				drawCount++;
			}
			// If one second passed
			// Display FPS
			// Reset timer
			if (timer >= 1000000000) {
//				System.out.println("FPS: " + drawCount);
//				drawCount = 0;
				timer = 0;
			}
		}
	}

	// Update game variables
	public void update() {
		// Update player
		player.update();
	}

	// Paint frame
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D frame = (Graphics2D) g;

		// Draw tiles
		tileH.draw(frame);

		// Draw objects
		for (int i = 0; i < objArray.length; i++) {
			if (objArray[i] != null) {
				objArray[i].draw(frame, this);
			}
		}

		// Draw player
		player.draw(frame);

		frame.dispose();
	}

	// Play music file (on loop)
	public void playMusic(int i) {
		soundH.setFile(i);
		soundH.play();
		soundH.loop();
	}

	// Stop music
	public void stopMusic() {
		soundH.stop();
	}

	// Play sound effect (single time)
	public void playSE(int i) {
		soundH.setFile(i);
		soundH.play();
	}

	// GETTER / SETTER METHODS
	public Thread getGameThread() {
		return gameThread;
	}

	public void setGameThread(Thread gameThread) {
		this.gameThread = gameThread;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public Obj[] getObjArray() {
		return objArray;
	}

	public void setObjArray(Obj[] objArray) {
		this.objArray = objArray;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public int getInitTileSize() {
		return initTileSize;
	}

	public int getScale() {
		return scale;
	}

	public int getTileSize() {
		return tileSize;
	}

	public int getMaxScreenCol() {
		return maxScreenCol;
	}

	public int getMaxScreenRow() {
		return maxScreenRow;
	}

	public int getScreenWidth() {
		return screenWidth;
	}

	public int getScreenHeight() {
		return screenHeight;
	}

	public int getMaxWorldCol() {
		return maxWorldCol;
	}

	public int getMaxWorldRow() {
		return maxWorldRow;
	}

	public int getFps() {
		return fps;
	}

	public AssetHandler getAssetH() {
		return assetH;
	}

	public CollisionHandler getCollisionH() {
		return collisionH;
	}

	public KeyHandler getKeyH() {
		return keyH;
	}

	public SoundHandler getSoundH() {
		return soundH;
	}

	public TileHandler getTileH() {
		return tileH;
	}
}
