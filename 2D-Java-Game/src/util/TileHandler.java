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

	private final GamePanel gp;
	private Tile[] tile;
	private int[][] tileMap;

	public TileHandler(GamePanel gp) {
		this.gp = gp;
		tile = new Tile[10];
		tileMap = new int[gp.getMaxScreenCol()][gp.getMaxScreenRow()];

		loadTileImage();
		loadMap("/map/map1.txt");
	}

	public void loadTileImage() {
		try {
			tile[0] = new Tile();
			tile[0].setImage(ImageIO.read(getClass().getResourceAsStream("/tile/floor.png")));
			tile[0].setCollision(false);

			tile[1] = new Tile();
			tile[1].setImage(ImageIO.read(getClass().getResourceAsStream("/tile/grass.png")));
			tile[1].setCollision(false);

			tile[2] = new Tile();
			tile[2].setImage(ImageIO.read(getClass().getResourceAsStream("/tile/tree.png")));
			tile[2].setCollision(true);

			tile[3] = new Tile();
			tile[3].setImage(ImageIO.read(getClass().getResourceAsStream("/tile/wall.png")));
			tile[3].setCollision(true);

			tile[4] = new Tile();
			tile[4].setImage(ImageIO.read(getClass().getResourceAsStream("/tile/water.png")));
			tile[4].setCollision(true);

			tile[5] = new Tile();
			tile[5].setImage(ImageIO.read(getClass().getResourceAsStream("/tile/wood.png")));
			tile[5].setCollision(false);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void loadMap(String filePath) {

		try {
			InputStream inStream = getClass().getResourceAsStream(filePath);
			BufferedReader bReader = new BufferedReader(new InputStreamReader(inStream));

			int col = 0;
			int row = 0;

			while (col < gp.getMaxScreenCol() && row < gp.getMaxScreenRow()) {
				String line = bReader.readLine();

				while (col < gp.getMaxScreenCol()) {
					String numbers[] = line.split(" ");

					int num = Integer.parseInt(numbers[col]);

					tileMap[col][row] = num;
					col++;
				}
				if (col == gp.getMaxScreenCol()) {
					col = 0;
					row++;
				}
			}
			bReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void draw(Graphics2D frame) {

		int col = 0;
		int row = 0;
		int x = 0;
		int y = 0;

		while (col < gp.getMaxScreenCol() && row < gp.getMaxScreenRow()) {

			int tileNum = tileMap[col][row];

			frame.drawImage(tile[tileNum].getImage(), x, y, gp.getTileSize(), gp.getTileSize(), null);
			col++;
			x += gp.getTileSize();

			if (col == gp.getMaxScreenCol()) {
				col = 0;
				x = 0;
				row++;
				y += gp.getTileSize();
			}
		}
	}
}
