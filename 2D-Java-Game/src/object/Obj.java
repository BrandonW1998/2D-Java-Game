package object;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import main.GamePanel;
import util.UtilityTool;

public class Obj {

	// Object variables
	private BufferedImage image; // Image of object
	private String name; // Name of object
	private boolean collision = false; // Collision flag
	private int worldX, worldY; // Position in world (map)
	private final UtilityTool uTool = new UtilityTool();

	// Draw object on frame
	public void draw(Graphics2D frame, GamePanel gp) {
		// Screen position offset
		int screenX = worldX - gp.getPlayer().getWorldX() + gp.getPlayer().getScreenX();
		int screenY = worldY - gp.getPlayer().getWorldY() + gp.getPlayer().getScreenY();

		// If in player view
		if (worldX + gp.getTileSize() > gp.getPlayer().getWorldX() - gp.getPlayer().getScreenX()
				&& worldX - gp.getTileSize() < gp.getPlayer().getWorldX() + gp.getPlayer().getScreenX()
				&& worldY + gp.getTileSize() > gp.getPlayer().getWorldY() - gp.getPlayer().getScreenY()
				&& worldY - gp.getTileSize() < gp.getPlayer().getWorldY() + gp.getPlayer().getScreenY()) {
			// Draw object on screen
			frame.drawImage(image, screenX, screenY, gp.getTileSize(), gp.getTileSize(), null);
		}
	}

	// GETTER / SETTER METHODS
	public BufferedImage getImage() {
		return image;
	}

	public void setImage(BufferedImage image) {
		this.image = image;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isCollision() {
		return collision;
	}

	public void setCollision(boolean collision) {
		this.collision = collision;
	}

	public int getWorldX() {
		return worldX;
	}

	public void setWorldX(int worldX) {
		this.worldX = worldX;
	}

	public int getWorldY() {
		return worldY;
	}

	public void setWorldY(int worldY) {
		this.worldY = worldY;
	}

	public UtilityTool getuTool() {
		return uTool;
	}
}
