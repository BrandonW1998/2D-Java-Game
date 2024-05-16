package util;

import entity.Entity;
import main.GamePanel;

public class CollisionHandler {

	private final GamePanel gp;

	public CollisionHandler(GamePanel gp) {
		this.gp = gp;
	}

	public void checkTile(Entity entity) {
		// Entity's tile position (x, y)
		int entTileX = entity.getWorldX() / gp.getTileSize();
		int entTileY = entity.getWorldY() / gp.getTileSize();

		int tileUp = gp.getTileH().getTileMap()[entTileX][entTileY - 1];
		int tileDown = gp.getTileH().getTileMap()[entTileX][entTileY + 1];
		int tileLeft = gp.getTileH().getTileMap()[entTileX - 1][entTileY];
		int tileRight = gp.getTileH().getTileMap()[entTileX + 1][entTileY];

		switch (entity.getDirection()) {
		case "up":
			if (gp.getTileH().getTile()[tileUp].isCollision()) {
				entity.setCollisionOn(true);
			}
			break;
		case "down":
			if (gp.getTileH().getTile()[tileDown].isCollision()) {
				entity.setCollisionOn(true);
			}
			break;
		case "left":
			if (gp.getTileH().getTile()[tileLeft].isCollision()) {
				entity.setCollisionOn(true);
			}
			break;
		case "right":
			if (gp.getTileH().getTile()[tileRight].isCollision()) {
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
				// Entity's tile position (x, y)
				int entTileX = entity.getWorldX() / gp.getTileSize();
				int entTileY = entity.getWorldY() / gp.getTileSize();

				// Object's tile position (x, y)
				int objTileX = gp.getObjArray()[i].getWorldX() / gp.getTileSize();
				int objTileY = gp.getObjArray()[i].getWorldY() / gp.getTileSize();

				switch (entity.getDirection()) {
				case "up":
					if (entTileX == objTileX && entTileY - 1 == objTileY) {
						if (gp.getObjArray()[i].isCollision()) {
							entity.setCollisionOn(true);
						}
						if (isPlayer) {
							index = i;
						}
					}
					break;
				case "down":
					if (entTileX == objTileX && entTileY + 1 == objTileY) {
						if (gp.getObjArray()[i].isCollision()) {
							entity.setCollisionOn(true);
						}
						if (isPlayer) {
							index = i;
						}
					}
					break;
				case "left":
					if (entTileX - 1 == objTileX && entTileY == objTileY) {
						if (gp.getObjArray()[i].isCollision()) {
							entity.setCollisionOn(true);
						}
						if (isPlayer) {
							index = i;
						}
					}
					break;
				case "right":
					if (entTileX + 1 == objTileX && entTileY == objTileY) {
						if (gp.getObjArray()[i].isCollision()) {
							entity.setCollisionOn(true);
						}
						if (isPlayer) {
							index = i;
						}
					}
					break;
				}
			}
		}
		return index;
	}
}
