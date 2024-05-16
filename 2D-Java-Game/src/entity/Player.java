package entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import util.KeyHandler;

public class Player extends Entity {

	private final GamePanel gp;
	private final KeyHandler keyH;

	private final int screenX; // Center of screen X value
	private final int screenY; // Center of screen Y value
	private boolean moving;
	private int pixelCount;

	private int keyBag = 0;

	public Player(GamePanel gp, KeyHandler keyH) {
		this.gp = gp;
		this.keyH = keyH;

		screenX = gp.getScreenWidth() / 2 - (gp.getTileSize() / 2);
		screenY = gp.getScreenHeight() / 2 - (gp.getTileSize() / 2);

		setDefaultValues();
		loadPlayerImage();
	}

	public void setDefaultValues() {
		setWorldX(gp.getTileSize() * 10);
		setWorldY(gp.getTileSize() * 8);
		setSpeed(3);
		setDirection("down");
		setMoving(false);
	}

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

	public void update() {

		if (!isMoving()) {
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
				setMoving(true);

				// Check tile collision
				setCollisionOn(false);
				gp.getCollisionH().checkTile(this);

				// Check object collision
				int objIndex = gp.getCollisionH().checkObject(this, true);
				pickUpObject(objIndex);
			}
		}
		if (isMoving()) {
			// If collision is false, player moves
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
			pixelCount += getSpeed();
			if (pixelCount == 48) {
				moving = false;
				altSprite();
				pixelCount = 0;
			}
		}
	}

	public void pickUpObject(int index) {

		if (index != -1) {

			String objName = gp.getObjArray()[index].getName();

			switch (objName) {
			case "key":
				keyBag++;
				gp.getObjArray()[index] = null;
				break;
			case "door":
				if (keyBag > 0) {
					gp.getObjArray()[index] = null;
					keyBag--;
				}
				break;
			}
		}
	}

	public void draw(Graphics2D frame) {
		BufferedImage image = null;

		switch (getDirection()) {
		case "up":
			if (!isMoving()) {
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
			if (!isMoving()) {
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
			if (!isMoving()) {
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
			if (!isMoving()) {
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

	public boolean isMoving() {
		return moving;
	}

	public void setMoving(boolean moving) {
		this.moving = moving;
	}

	public int getPixelCount() {
		return pixelCount;
	}

	public void setPixelCount(int pixelCount) {
		this.pixelCount = pixelCount;
	}

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
