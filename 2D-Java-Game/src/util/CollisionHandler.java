package util;

import entity.Entity;
import main.GamePanel;

public class CollisionHandler {

	// SYSTEMS / TOOLS
	private final GamePanel gp;

	// CollisionHandler Constructor
	public CollisionHandler(GamePanel gp) {
		this.gp = gp;
	}

	// Check tiles surrounding entity
	public void checkTile(Entity entity) {
		// Entity's tile position (x, y)
		int entTileX = entity.getWorldX() / gp.getTileSize();
		int entTileY = entity.getWorldY() / gp.getTileSize();

		// Tiles surrounding entity
		int tileUp = gp.getTileH().getTileMap()[entTileX][entTileY - 1];
		int tileDown = gp.getTileH().getTileMap()[entTileX][entTileY + 1];
		int tileLeft = gp.getTileH().getTileMap()[entTileX - 1][entTileY];
		int tileRight = gp.getTileH().getTileMap()[entTileX + 1][entTileY];

		// Get tile in front of entity
		// If collision is found in direction
		// Set collisionOn flag
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

	// Check objects for collisions
	public int checkObject(Entity entity, boolean isPlayer) {
		// Default fail case
		int index = -1;

		// For all objects
		for (int i = 0; i < gp.getObjArray().length; i++) {
			// If object is present
			if (gp.getObjArray()[i] != null) {
				// Entity's tile position (x, y)
				int entTileX = entity.getWorldX() / gp.getTileSize();
				int entTileY = entity.getWorldY() / gp.getTileSize();

				// Object's tile position (x, y)
				int objTileX = gp.getObjArray()[i].getWorldX() / gp.getTileSize();
				int objTileY = gp.getObjArray()[i].getWorldY() / gp.getTileSize();

				// Check tile in front of entity
				// If object collided
				// Set collisionOn flag
				// If entity is player
				// Return object's index
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
		// Default fail case
		return index;
	}

	// Check objects for collisions
	public boolean checkNpc(Entity entity, boolean isPlayer) {
		// Default fail case
		boolean isNpc = false;

		// Entity's tile position (x, y)
		int entTileX = entity.getWorldX() / gp.getTileSize();
		int entTileY = entity.getWorldY() / gp.getTileSize();

		// Npc's tile position (x, y)
		int npcTileX = gp.getNpc().getWorldX() / gp.getTileSize();
		int npcTileY = gp.getNpc().getWorldY() / gp.getTileSize();

		// Check tile in front of entity
		// If object collided
		// Set collisionOn flag
		// If entity is player
		// Return object's index
		switch (entity.getDirection()) {
		case "up":
			if (entTileX == npcTileX && entTileY - 1 == npcTileY) {
				entity.setCollisionOn(true);
				if (isPlayer) {
					isNpc = true;
				}
			}
			break;
		case "down":
			if (entTileX == npcTileX && entTileY + 1 == npcTileY) {
				entity.setCollisionOn(true);
				if (isPlayer) {
					isNpc = true;
				}
			}
			break;
		case "left":
			if (entTileX - 1 == npcTileX && entTileY == npcTileY) {
				entity.setCollisionOn(true);
				if (isPlayer) {
					isNpc = true;
				}
			}
			break;
		case "right":
			if (entTileX + 1 == npcTileX && entTileY == npcTileY) {
				entity.setCollisionOn(true);
				if (isPlayer) {
					isNpc = true;
				}
			}
			break;
		}

		return isNpc;
	}
}
