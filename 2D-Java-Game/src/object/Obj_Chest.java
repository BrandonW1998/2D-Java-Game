package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class Obj_Chest extends Obj {

	private final GamePanel gp;

	// Chest Constructor
	public Obj_Chest(GamePanel gp) {
		this.gp = gp;
		// Name of object
		setName("chest");
		try {
			// Load and up-scale chest image
			setImage(ImageIO.read(getClass().getResourceAsStream("/object/chest.png")));
			getuTool().scaleImage(getImage(), gp.getTileSize(), gp.getTileSize());
		} catch (IOException e) {
			e.printStackTrace();
		}
		// Chests have collision
		setCollision(true);
	}

	public GamePanel getGp() {
		return gp;
	}
}
