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
	private boolean moving;

	public Player(GamePanel gp, KeyHandler keyH) {
		this.gp = gp;
		this.keyH = keyH;

		setDefaultValues();
		getPlayerImage();
	}

	public void setDefaultValues() {
		setX(100);
		setY(100);
		setSpeed(4);
		setDirection("down");
		setMoving(false);
	}

	public void getPlayerImage() {

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

		if (keyH.isUp() == true || keyH.isDown() == true || keyH.isLeft() == true || keyH.isRight() == true) {
			if (keyH.isUp() == true) {
				setDirection("up");
				setY(getY() - getSpeed());
			}
			if (keyH.isDown() == true) {
				setDirection("down");
				setY(getY() + getSpeed());
			}
			if (keyH.isLeft() == true) {
				setDirection("left");
				setX(getX() - getSpeed());
			}
			if (keyH.isRight() == true) {
				setDirection("right");
				setX(getX() + getSpeed());
			}
			setMoving(true);

			setSpriteCount(getSpriteCount() + 1);
			if (getSpriteCount() > 15) {
				if (getSprite() == 1) {
					setSprite(2);
				} else if (getSprite() == 2) {
					setSprite(1);
				}
				setSpriteCount(0);
			}
		} else {
			setMoving(false);
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

		frame.drawImage(image, getX(), getY(), gp.getTileSize(), gp.getTileSize(), null);
	}

	public boolean isMoving() {
		return moving;
	}

	public void setMoving(boolean moving) {
		this.moving = moving;
	}
}
