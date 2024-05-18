package util;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import main.GamePanel;
import tile.Tile;

public class TileHandler {

	// SYSTEM / TOOLS
	private final GamePanel gp;

	// Tile holder
	private Tile[] tile;

	// Map of tile values
	private int[][] tileMap;

	// TileHandler Constructor
	public TileHandler(GamePanel gp) {
		this.gp = gp;
		tile = new Tile[10];
		tileMap = new int[gp.getMaxWorldCol()][gp.getMaxWorldRow()];

		loadTileImage();
		loadMap("/map/map1.txt");
	}

	// Set images of tiles
	public void loadTileImage() {
		setup(0, "floor", false);
		setup(1, "grass", false);
		setup(2, "tree", true);
		setup(3, "wall", true);
		setup(4, "water", true);
		setup(5, "wood", false);
	}

	// Load and up-scale image of tile
	public void setup(int index, String imageName, boolean collision) {
		UtilityTool uTool = new UtilityTool();

		try {
			tile[index] = new Tile();
			tile[index].setImage(ImageIO.read(getClass().getResourceAsStream("/tile/" + imageName + ".png")));
			tile[index].setImage(uTool.scaleImage(tile[index].getImage(), gp.getTileSize(), gp.getTileSize()));
			tile[index].setCollision(collision);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Load map from file
	public void loadMap(String filePath) {
		try {
			InputStream inStream = getClass().getResourceAsStream(filePath);
			BufferedReader bReader = new BufferedReader(new InputStreamReader(inStream));

			int col = 0;
			int row = 0;

			// For all integers in map file
			while (col < gp.getMaxWorldCol() && row < gp.getMaxWorldRow()) {
				String line = bReader.readLine();

				// For each column
				// Set each corresponding map tile
				while (col < gp.getMaxWorldCol()) {
					String numbers[] = line.split(" ");

					int num = Integer.parseInt(numbers[col]);

					tileMap[col][row] = num;
					col++;
				}
				if (col == gp.getMaxWorldCol()) {
					col = 0;
					row++;
				}
			}
			bReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Draw tiles on frame
	public void draw(Graphics2D frame) {

		int worldCol = 0;
		int worldRow = 0;

		// For all tiles
		while (worldCol < gp.getMaxWorldCol() && worldRow < gp.getMaxWorldRow()) {

			// Get tile's number
			int tileNum = tileMap[worldCol][worldRow];

			// Get tile's relative positions
			int worldX = worldCol * gp.getTileSize();
			int worldY = worldRow * gp.getTileSize();
			int screenX = worldX - gp.getPlayer().getWorldX() + gp.getPlayer().getScreenX();
			int screenY = worldY - gp.getPlayer().getWorldY() + gp.getPlayer().getScreenY();

			// If on screen
			// Draw tile at relative position
			if (worldX + gp.getTileSize() > gp.getPlayer().getWorldX() - gp.getPlayer().getScreenX()
					&& worldX - gp.getTileSize() < gp.getPlayer().getWorldX() + gp.getPlayer().getScreenX()
					&& worldY + gp.getTileSize() > gp.getPlayer().getWorldY() - gp.getPlayer().getScreenY()
					&& worldY - gp.getTileSize() < gp.getPlayer().getWorldY() + gp.getPlayer().getScreenY()) {
				frame.drawImage(tile[tileNum].getImage(), screenX, screenY, null);
			}
			worldCol++;

			if (worldCol == gp.getMaxWorldCol()) {
				worldCol = 0;
				worldRow++;
			}
		}
	}

	// GETTER / SETTER METHODS
	public Tile[] getTile() {
		return tile;
	}

	public void setTile(Tile[] tile) {
		this.tile = tile;
	}

	public int[][] getTileMap() {
		return tileMap;
	}

	public void setTileMap(int[][] tileMap) {
		this.tileMap = tileMap;
	}

	public GamePanel getGp() {
		return gp;
	}
}
