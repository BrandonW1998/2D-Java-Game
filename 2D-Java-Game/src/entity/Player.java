package entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import util.KeyHandler;

public class Player extends Entity {

	// SYSTEMS / TOOLS
	private final GamePanel gp;
	private final KeyHandler keyH;

	// Entity variables
	private final int screenX; // Center of screen X value
	private final int screenY; // Center of screen Y value

	// Inventory
	private int keyBag = 0;

	// Player Constructor
	public Player(GamePanel gp, KeyHandler keyH) {
		this.gp = gp;
		this.keyH = keyH;

		// Calculate center of display
		screenX = gp.getScreenWidth() / 2 - (gp.getTileSize() / 2);
		screenY = gp.getScreenHeight() / 2 - (gp.getTileSize() / 2);

		setDefaultValues();
		loadPlayerImage();
	}

	// Initialize player variables
	public void setDefaultValues() {
		// Starting position (10, 8)
		setWorldX(gp.getTileSize() * 10);
		setWorldY(gp.getTileSize() * 8);
		// Standard speed (3 pixels per frame)
		setSpeed(3);
		// Start facing down (towards camera)
		setDirection("down");
	}

	// Load images of player
	public void loadPlayerImage() {
		try {
			setUp0(ImageIO.read(getClass().getResourceAsStream("/player/player_up0.png")));
			setUp1(ImageIO.read(getClass().getResourceAsStream("/player/player_up1.png")));
			setUp2(ImageIO.read(getClass().getResourceAsStream("/player/player_up2.png")));
			setDown0(ImageIO.read(getClass().getResourceAsStream("/player/player_down0.png")));
			setDown1(ImageIO.read(getClass().getResourceAsStream("/player/player_down1.png")));
			setDown2(ImageIO.read(getClass().getResourceAsStream("/player/player_down2.png")));
			setLeft0(ImageIO.read(getClass().getResourceAsStream("/player/player_left0.png")));
			setLeft1(ImageIO.read(getClass().getResourceAsStream("/player/player_left1.png")));
			setLeft2(ImageIO.read(getClass().getResourceAsStream("/player/player_left2.png")));
			setRight0(ImageIO.read(getClass().getResourceAsStream("/player/player_right0.png")));
			setRight1(ImageIO.read(getClass().getResourceAsStream("/player/player_right1.png")));
			setRight2(ImageIO.read(getClass().getResourceAsStream("/player/player_right2.png")));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Update player variables (called every frame)
	public void update() {
		// If player IS NOT moving
		if (!isMoving()) {
			// If movement key is pressed
			// Change player's facing direction
			if (keyH.isUp() || keyH.isDown() || keyH.isLeft() || keyH.isRight()) {
				if (keyH.isUp()) {
					setDirection("up");
				}
				if (keyH.isDown()) {
					setDirection("down");
				}
				if (keyH.isLeft()) {
					setDirection("left");
				}
				if (keyH.isRight()) {
					setDirection("right");
				}
				// Set moving flag (to play animation)
				setMoving(true);

				// Check tile collision
				setCollisionOn(false);
				gp.getCollisionH().checkTile(this);

				// Check object collision
				int objIndex = gp.getCollisionH().checkObject(this, true);
				pickUpObject(objIndex);
			}
		}
		// If player IS moving
		if (isMoving()) {
			// If no collision
			// Move player
			if (!isCollisionOn()) {
				switch (getDirection()) {
				case "up":
					setWorldY(getWorldY() - getSpeed());
					break;
				case "down":
					setWorldY(getWorldY() + getSpeed());
					break;
				case "left":
					setWorldX(getWorldX() - getSpeed());
					break;
				case "right":
					setWorldX(getWorldX() + getSpeed());
					break;
				}
			}
			// Track number of pixels moved
			setPixelCount(getPixelCount() + getSpeed());
			// If moved 1 tile
			// Stop movement
			// Alternate sprite to use for next movement
			// Reset counter
			if (getPixelCount() == 48) {
				setMoving(false);
				altSprite();
				setPixelCount(0);
			}
		}
	}

	// Action when object is interacted/collided with
	public void pickUpObject(int index) {
		// If NOT an object
		if (index != -1) {
			// Name of object to pickup
			String objName = gp.getObjArray()[index].getName();

			switch (objName) {
			// Pickup a key
			// Play pickup sound effect (i = 1)
			case "key":
				gp.playSE(1);
				keyBag++;
				gp.getObjArray()[index] = null;
				gp.getUiH().showMessage("Got a Key!");
				break;
			// Use a key on door
			// Play open door sound effect (i = 2)
			case "door":
				if (keyBag > 0) {
					gp.playSE(2);
					gp.getObjArray()[index] = null;
					gp.getUiH().showMessage("Used a Key");
					keyBag--;
				} else {
					gp.getUiH().showMessage("Need a Key");
				}
				break;
			}
		}
	}

	// Draw player onto frame
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
		frame.drawImage(image, screenX, screenY, gp.getTileSize(), gp.getTileSize(), null);
	}

	// GETTER / SETTER METHODS
	public int getKeyBag() {
		return keyBag;
	}

	public void setKeyBag(int keyBag) {
		this.keyBag = keyBag;
	}

	public GamePanel getGp() {
		return gp;
	}

	public KeyHandler getKeyH() {
		return keyH;
	}

	public int getScreenX() {
		return screenX;
	}

	public int getScreenY() {
		return screenY;
	}
}
