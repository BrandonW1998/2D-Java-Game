package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class Obj_Door extends Obj {

	private final GamePanel gp;

	// Key Constructor
	public Obj_Door(GamePanel gp) {
		this.gp = gp;
		// Name of object
		setName("door");
		try {
			// Load key image
			setImage(ImageIO.read(getClass().getResourceAsStream("/object/door.png")));
			getuTool().scaleImage(getImage(), gp.getTileSize(), gp.getTileSize());
		} catch (IOException e) {
			e.printStackTrace();
		}
		setCollision(true);
	}

	public GamePanel getGp() {
		return gp;
	}
}
