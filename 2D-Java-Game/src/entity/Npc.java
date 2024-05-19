package entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import util.UtilityTool;

public class Npc extends Entity {

	// SYSTEMS / TOOLS
	private final GamePanel gp;

	// Dialogue
	private String dialogue;

	public Npc(GamePanel gp) {
		this.gp = gp;

		dialogue = "This is test dialogue.";

		setDefaultValues();
		loadNpcImage();
	}

	// Initialize player variables
	public void setDefaultValues() {
		// Starting position (10, 8)
		setWorldX(gp.getTileSize() * 11);
		setWorldY(gp.getTileSize() * 17);
		// Standard speed (3 pixels per frame)
		setSpeed(3);
		// Start facing down (towards camera)
		setDirection("down");
		// Npc Collision
		setCollision(true);
	}

	// Set images of player
	public void loadNpcImage() {
		setUp0(setup("npc_up0"));
		setUp1(setup("npc_up1"));
		setUp2(setup("npc_up2"));
		setDown0(setup("npc_down0"));
		setDown1(setup("npc_down1"));
		setDown2(setup("npc_down2"));
		setLeft0(setup("npc_left0"));
		setLeft1(setup("npc_left1"));
		setLeft2(setup("npc_left2"));
		setRight0(setup("npc_right0"));
		setRight1(setup("npc_right1"));
		setRight2(setup("npc_right2"));
	}

	// Load and up-scale images of player
	public BufferedImage setup(String imageName) {
		UtilityTool uTool = new UtilityTool();
		BufferedImage image = null;

		// Get image from res
		// Scale image
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/npc/" + imageName + ".png"));
			image = uTool.scaleImage(image, gp.getTileSize(), gp.getTileSize());
		} catch (IOException e) {
			e.printStackTrace();
		}
		// Return scaled image
		return image;
	}

	public void facePlayer() {
		switch (gp.getPlayer().getDirection()) {
		case "up":
			setDirection("down");
			break;
		case "down":
			setDirection("up");
			break;
		case "left":
			setDirection("right");
			break;
		case "right":
			setDirection("left");
			break;
		}
	}

	// Update npc variables
	public void update() {

	}

	// Draw npc onto frame
	public void draw(Graphics2D frame) {
		BufferedImage image = null;

		// Display image in facing direction
		switch (getDirection()) {
		case "up":
			if (!isMoving() || getPixelCount() >= 27) {
				image = getUp0();
				break;
			}
			if (getSprite() == 1) {
				image = getUp1();
			}
			if (getSprite() == 2) {
				image = getUp2();
			}
			break;
		case "down":
			if (!isMoving() || getPixelCount() >= 27) {
				image = getDown0();
				break;
			}
			if (getSprite() == 1) {
				image = getDown1();
			}
			if (getSprite() == 2) {
				image = getDown2();
			}
			break;
		case "left":
			if (!isMoving() || getPixelCount() >= 27) {
				image = getLeft0();
				break;
			}
			if (getSprite() == 1) {
				image = getLeft1();
			}
			if (getSprite() == 2) {
				image = getLeft2();
			}
			break;
		case "right":
			if (!isMoving() || getPixelCount() >= 27) {
				image = getRight0();
				break;
			}
			if (getSprite() == 1) {
				image = getRight1();
			}
			if (getSprite() == 2) {
				image = getRight2();
			}
			break;
		}

		// Screen position offset
		int screenX = getWorldX() - gp.getPlayer().getWorldX() + gp.getPlayer().getScreenX();
		int screenY = getWorldY() - gp.getPlayer().getWorldY() + gp.getPlayer().getScreenY();

		// If in player view
		if (getWorldX() + gp.getTileSize() > gp.getPlayer().getWorldX() - gp.getPlayer().getScreenX()
				&& getWorldX() - gp.getTileSize() < gp.getPlayer().getWorldX() + gp.getPlayer().getScreenX()
				&& getWorldY() + gp.getTileSize() > gp.getPlayer().getWorldY() - gp.getPlayer().getScreenY()
				&& getWorldY() - gp.getTileSize() < gp.getPlayer().getWorldY() + gp.getPlayer().getScreenY()) {
			// Draw object on screen
			frame.drawImage(image, screenX, screenY, null);
		}
	}
}
