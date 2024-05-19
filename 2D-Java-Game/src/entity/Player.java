package entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import battle.Battle;
import main.GamePanel;
import util.KeyHandler;
import util.UtilityTool;

public class Player extends Entity {

	// SYSTEMS / TOOLS
	private final GamePanel gp;
	private final KeyHandler keyH;

	// Entity variables
	private final int screenX; // Center of screen X value
	private final int screenY; // Center of screen Y value

	// Interacting variables
	private int interactCount;
	private final int interactLimit;

	// Inventory
	private int keyBag = 0;

	// Player Constructor
	public Player(GamePanel gp, KeyHandler keyH) {
		this.gp = gp;
		this.keyH = keyH;

		// Calculate center of display
		screenX = gp.getScreenWidth() / 2 - (gp.getTileSize() / 2);
		screenY = gp.getScreenHeight() / 2 - (gp.getTileSize() / 2);

		// Time possible between interactions
		interactLimit = 15;
		interactCount = interactLimit; // 15 frames between interactions

		setDefaultValues();
		loadPlayerImage();
	}

	// Initialize player variables
	public void setDefaultValues() {
		// Starting position (10, 8)
		setWorldX(gp.getTileSize() * 11);
		setWorldY(gp.getTileSize() * 11);
		// Standard speed (3 pixels per frame)
		setSpeed(3);
		// Start facing down (towards camera)
		setDirection("down");
	}

	// Set images of player
	public void loadPlayerImage() {
		setUp0(setup("player_up0"));
		setUp1(setup("player_up1"));
		setUp2(setup("player_up2"));
		setDown0(setup("player_down0"));
		setDown1(setup("player_down1"));
		setDown2(setup("player_down2"));
		setLeft0(setup("player_left0"));
		setLeft1(setup("player_left1"));
		setLeft2(setup("player_left2"));
		setRight0(setup("player_right0"));
		setRight1(setup("player_right1"));
		setRight2(setup("player_right2"));
	}

	// Load and up-scale images of player
	public BufferedImage setup(String imageName) {
		UtilityTool uTool = new UtilityTool();
		BufferedImage image = null;

		// Get image from res
		// Scale image
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/player/" + imageName + ".png"));
			image = uTool.scaleImage(image, gp.getTileSize(), gp.getTileSize());
		} catch (IOException e) {
			e.printStackTrace();
		}
		// Return scaled image
		return image;
	}

	// Update player variables (called every frame)
	public void update() {
		// 15 frame interval, before interactions can occur again
		if (interactCount < interactLimit) {
			interactCount++;
		}
		if (gp.getGameMode() == gp.getBattleMode()) {
			if (keyH.isDebugBattle() && interactCount == interactLimit) {
				interactCount = 0;
				gp.setGameMode(gp.getPlayMode());
			}
		}
		if (keyH.isDebugBattle() && interactCount == interactLimit) {
			interactCount = 0;
			gp.setGameMode(gp.getBattleMode());
			gp.setBattle(new Battle(this, gp.getNpc(), gp));
		}
		if (gp.getGameMode() == gp.getDialogueMode()) {
			if (keyH.isInteract() && interactCount == interactLimit) {
				interactCount = 0;
				gp.getUiH().exitDialogue();
				gp.setGameMode(gp.getPlayMode());
			}
		}
		if (gp.getGameMode() == gp.getPlayMode()) {
			// If player IS NOT moving
			if (!isMoving()) {
				// If interaction key is pressed
				// Check for interactions
				// Interact with object / entity
				if (keyH.isInteract()) {
					// Check object collision
					int objIndex = gp.getCollisionH().checkObject(this, true);
					interactObject(objIndex);

					if (gp.getCollisionH().checkNpc(this, true) && interactCount == 15) {
						interactCount = 0;
						interactNpc(1);
					}
				}

				// If movement key is pressed
				// Check for collision
				// Change player's facing direction
				// Flag to move player
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

					// Check npc collision
					gp.getCollisionH().checkNpc(this, true);
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
	}

	// Action when object is interacted with
	public void interactObject(int index) {
		// If an object
		if (index != -1) {
			// Name of object to interact
			String objName = gp.getObjArray()[index].getName();

			switch (objName) {
			// Open chest
			// Play open sound effect (i = 2)
			case "chest":
				gp.playSE(2);
				gp.getObjArray()[index] = null;
				gp.getUiH().showMessage("Player opened a chest!");
				break;
			// Use a key on door
			// Play open door sound effect (i = 2)
			case "door":
				if (keyBag > 0) {
					gp.playSE(2);
					gp.getObjArray()[index] = null;
					gp.getUiH().showMessage("Player used a key!");
					keyBag--;
				} else {
					gp.getUiH().showMessage("The door is locked...");
				}
				break;
			}
		}
	}

	// Action when object is collided with
	public void pickUpObject(int index) {
		// If an object
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
				gp.getUiH().showMessage("Player got a Key!");
				break;
			}
		}
	}

	// Action when npc is interacted with
	public void interactNpc(int index) {
		if (interactCount < 15) {
			// If an Npc
			if (index != -1) {
				gp.getNpc().facePlayer();
				gp.getNpc().talkToPlayer();
				gp.setGameMode(gp.getDialogueMode());
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
		frame.drawImage(image, screenX, screenY, null);
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
