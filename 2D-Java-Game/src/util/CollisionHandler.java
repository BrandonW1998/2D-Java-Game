package util;

import entity.Entity;
import main.GamePanel;

public class CollisionHandler {

	private final GamePanel gp;

	public CollisionHandler(GamePanel gp) {
		this.gp = gp;
	}

	public void checkTile(Entity entity) {

		// COLLISION BOX
		int entityLeftWorldX = entity.getWorldX() + entity.solidArea.x;
		int entityRightWorldX = entity.getWorldX() + entity.solidArea.x + entity.solidArea.width;
		int entityTopWorldY = entity.getWorldY() + entity.solidArea.y;
		int entityBottomWorldY = entity.getWorldY() + entity.solidArea.y + entity.solidArea.height;
		int entityLeftCol = entityLeftWorldX / gp.getTileSize();
		int entityRightCol = entityRightWorldX / gp.getTileSize();
		int entityTopRow = entityTopWorldY / gp.getTileSize();
		int entityBottomRow = entityBottomWorldY / gp.getTileSize();

		int tileNum1, tileNum2;

		switch (entity.getDirection()) {
		case "up":
			entityTopRow = (entityTopWorldY - entity.getSpeed()) / gp.getTileSize();
			tileNum1 = gp.getTileH().getTileMap()[entityLeftCol][entityTopRow];
			tileNum2 = gp.getTileH().getTileMap()[entityRightCol][entityTopRow];
			if (gp.getTileH().getTile()[tileNum1].isCollision() || gp.getTileH().getTile()[tileNum2].isCollision()) {
				entity.setCollisionOn(true);
			}
			break;
		case "down":
			entityBottomRow = (entityBottomWorldY + entity.getSpeed()) / gp.getTileSize();
			tileNum1 = gp.getTileH().getTileMap()[entityLeftCol][entityBottomRow];
			tileNum2 = gp.getTileH().getTileMap()[entityRightCol][entityBottomRow];
			if (gp.getTileH().getTile()[tileNum1].isCollision() || gp.getTileH().getTile()[tileNum2].isCollision()) {
				entity.setCollisionOn(true);
			}
			break;
		case "left":
			entityLeftCol = (entityLeftWorldX - entity.getSpeed()) / gp.getTileSize();
			tileNum1 = gp.getTileH().getTileMap()[entityLeftCol][entityTopRow];
			tileNum2 = gp.getTileH().getTileMap()[entityLeftCol][entityBottomRow];
			if (gp.getTileH().getTile()[tileNum1].isCollision() || gp.getTileH().getTile()[tileNum2].isCollision()) {
				entity.setCollisionOn(true);
			}
			break;
		case "right":
			entityRightCol = (entityRightWorldX + entity.getSpeed()) / gp.getTileSize();
			tileNum1 = gp.getTileH().getTileMap()[entityRightCol][entityTopRow];
			tileNum2 = gp.getTileH().getTileMap()[entityRightCol][entityBottomRow];
			if (gp.getTileH().getTile()[tileNum1].isCollision() || gp.getTileH().getTile()[tileNum2].isCollision()) {
				entity.setCollisionOn(true);
			}
			break;

		}
	}
}
