package tile;

import java.awt.image.BufferedImage;

public class Tile {

	// Tile variables
	private BufferedImage image; // Image of tile
	private boolean collision = false; // Collision flag

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
