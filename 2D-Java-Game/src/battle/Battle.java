package battle;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import entity.Entity;
import main.GamePanel;

public class Battle {

	// SYSTEMS / TOOLS
	private final GamePanel gp;

	// Enemy battle variables
	private final int enemyX;
	private final int enemyY;
	private BufferedImage enemyImage;

	// Player battle variables
	private final int playerX;
	private final int playerY;
	private BufferedImage playerImage;

	public Battle(Entity player, Entity enemy, GamePanel gp) {
		this.gp = gp;

		enemyX = 100;
		enemyY = 100;
		enemyImage = enemy.getDown0();

		playerX = gp.getScreenWidth() - 100;
		playerY = gp.getScreenHeight() - 100;
		playerImage = player.getUp0();
	}

	public void draw(Graphics2D frame) {
		frame.drawImage(enemyImage, enemyX, enemyY, null);
		frame.drawImage(playerImage, playerX, playerY, null);
	}
}
