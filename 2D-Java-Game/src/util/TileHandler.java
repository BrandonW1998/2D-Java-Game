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

	// Load images of tiles
	public void loadTileImage() {
		try {
			// Floor
			tile[0] = new Tile();
			tile[0].setImage(ImageIO.read(getClass().getResourceAsStream("/tile/floor.png")));

			// Grass
			tile[1] = new Tile();
			tile[1].setImage(ImageIO.read(getClass().getResourceAsStream("/tile/grass.png")));

			// Tree (has collision)
			tile[2] = new Tile();
			tile[2].setImage(ImageIO.read(getClass().getResourceAsStream("/tile/tree.png")));
			tile[2].setCollision(true);

			// Wall (has collision)
			tile[3] = new Tile();
			tile[3].setImage(ImageIO.read(getClass().getResourceAsStream("/tile/wall.png")));
			tile[3].setCollision(true);

			// Water (has collision)
			tile[4] = new Tile();
			tile[4].setImage(ImageIO.read(getClass().getResourceAsStream("/tile/water.png")));
			tile[4].setCollision(true);

			// Wood
			tile[5] = new Tile();
			tile[5].setImage(ImageIO.read(getClass().getResourceAsStream("/tile/wood.png")));
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
				frame.drawImage(tile[tileNum].getImage(), screenX, screenY, gp.getTileSize(), gp.getTileSize(), null);
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
