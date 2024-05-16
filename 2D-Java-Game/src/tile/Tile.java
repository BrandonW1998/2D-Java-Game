package tile;

import java.awt.image.BufferedImage;

public class Tile {

	private BufferedImage image;
	private boolean collision = false;

	// GETTER / SETTER METHODS
	public BufferedImage getImage() {
		return image;
	}

	public void setImage(BufferedImage image) {
		this.image = image;
	}

	public boolean isCollision() {
		return collision;
	}

	public void setCollision(boolean collision) {
		this.collision = collision;
	}
}
