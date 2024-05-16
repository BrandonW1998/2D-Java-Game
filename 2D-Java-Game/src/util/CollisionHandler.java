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

	public int checkObject(Entity entity, boolean isPlayer) {
		int index = -1;

		// COLLISION BOX
		for (int i = 0; i < gp.getObjArray().length; i++) {

			if (gp.getObjArray()[i] != null) {

				entity.solidArea.x = entity.getWorldX() + entity.solidArea.x;
				entity.solidArea.y = entity.getWorldY() + entity.solidArea.y;

				gp.getObjArray()[i].solidArea.x = gp.getObjArray()[i].getWorldX() + gp.getObjArray()[i].solidArea.x;
				gp.getObjArray()[i].solidArea.y = gp.getObjArray()[i].getWorldY() + gp.getObjArray()[i].solidArea.y;

				switch (entity.getDirection()) {
				case "up":
					entity.solidArea.y -= entity.getSpeed();
					if (entity.solidArea.intersects(gp.getObjArray()[i].solidArea)) {
						if (gp.getObjArray()[i].isCollision()) {
							entity.setCollisionOn(true);
						}
						if (isPlayer) {
							index = i;
						}
					}
					break;
				case "down":
					entity.solidArea.y += entity.getSpeed();
					if (entity.solidArea.intersects(gp.getObjArray()[i].solidArea)) {
						if (gp.getObjArray()[i].isCollision()) {
							entity.setCollisionOn(true);
						}
						if (isPlayer) {
							index = i;
						}
					}
					break;
				case "left":
					entity.solidArea.x -= entity.getSpeed();
					if (entity.solidArea.intersects(gp.getObjArray()[i].solidArea)) {
						if (gp.getObjArray()[i].isCollision()) {
							entity.setCollisionOn(true);
						}
						if (isPlayer) {
							index = i;
						}
					}
					break;
				case "right":
					entity.solidArea.x += entity.getSpeed();
					if (entity.solidArea.intersects(gp.getObjArray()[i].solidArea)) {
						if (gp.getObjArray()[i].isCollision()) {
							entity.setCollisionOn(true);
						}
						if (isPlayer) {
							index = i;
						}
					}
					break;
				}
				entity.solidArea.x = entity.solidAreaDefX;
				entity.solidArea.y = entity.solidAreaDefY;
				gp.getObjArray()[i].solidArea.x = gp.getObjArray()[i].solidAreaDefX;
				gp.getObjArray()[i].solidArea.y = gp.getObjArray()[i].solidAreaDefY;
			}
		}
		return index;
	}
}
