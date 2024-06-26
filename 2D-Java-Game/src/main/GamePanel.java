package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import battle.Battle;
import entity.Npc;
import entity.Player;
import object.Obj;
import util.AssetHandler;
import util.CollisionHandler;
import util.KeyHandler;
import util.SoundHandler;
import util.TileHandler;
import util.UIHandler;

public class GamePanel extends JPanel implements Runnable {
	private static final long serialVersionUID = 1L;

	// SCREEN SETTINGS
	private final int initTileSize = 16; // 16x16 pixels
	private final int scale = 3; // Up-scale rate (for better resolution)
	private final int tileSize = initTileSize * scale; // 48x48 pixels
	private final int maxScreenCol = 13; // Max on-screen tiles in columns
	private final int maxScreenRow = 11; // Max on-screen tiles in rows
	private final int screenWidth = tileSize * maxScreenCol; // 528 pixels (across)
	private final int screenHeight = tileSize * maxScreenRow; // 432 pixels (down)

	// MAP SETTINGS
	private final int maxWorldCol = 23; // Number of column tiles on map
	private final int maxWorldRow = 24; // Number of row tiles on map

	// FRAMES PER SECOND
	private final int fps = 60;

	// SYSTEMS / UTILITIES
	private final AssetHandler assetH = new AssetHandler(this);
	private final CollisionHandler collisionH = new CollisionHandler(this);
	private final KeyHandler keyH = new KeyHandler();
	private final SoundHandler musicH = new SoundHandler();
	private final SoundHandler seH = new SoundHandler();
	private final TileHandler tileH = new TileHandler(this);
	private final UIHandler uiH = new UIHandler(this);

	// BATTLE STUFF
	private Battle battle;

	// GAME STATES
	private final int playMode = 1;
	private final int dialogueMode = 2;
	private final int battleMode = 3;
	private int gameMode = 1;
	// THREADS
	private Thread gameThread;

	// ASSETS / HOLDERS
	private Player player = new Player(this, keyH); // Player character
	private Obj[] objArray = new Obj[10]; // Objects to display
	private Npc npc = new Npc(this);

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
		// Update npc
		npc.update();
	}

	// Paint frame
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D frame = (Graphics2D) g;

		// If play mode
		if (gameMode == playMode || gameMode == dialogueMode) {
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

			// Draw npc
			npc.draw(frame);

			// Draw ui
			uiH.draw(frame);

			frame.dispose();
		}

		if (gameMode == battleMode) {
			battle.draw(frame);

			frame.dispose();
		}
	}

	// Play music file (on loop)
	public void playMusic(int i) {
		musicH.setFile(i);
		musicH.play();
		musicH.loop();
	}

	// Stop music
	public void stopMusic() {
		musicH.stop();
	}

	// Play sound effect (single time)
	public void playSE(int i) {
		seH.setFile(i);
		seH.play();
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

	public Npc getNpc() {
		return npc;
	}

	public void setNpc(Npc npc) {
		this.npc = npc;
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

	public SoundHandler getMusicH() {
		return musicH;
	}

	public SoundHandler getSEH() {
		return seH;
	}

	public TileHandler getTileH() {
		return tileH;
	}

	public int getGameMode() {
		return gameMode;
	}

	public void setGameMode(int gameMode) {
		this.gameMode = gameMode;
	}

	public SoundHandler getSeH() {
		return seH;
	}

	public int getPlayMode() {
		return playMode;
	}

	public int getDialogueMode() {
		return dialogueMode;
	}

	public UIHandler getUiH() {
		return uiH;
	}

	public Battle getBattle() {
		return battle;
	}

	public void setBattle(Battle battle) {
		this.battle = battle;
	}

	public int getBattleMode() {
		return battleMode;
	}
}
