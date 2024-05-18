package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class Obj_Key extends Obj {

	private final GamePanel gp;

	// Key Constructor
	public Obj_Key(GamePanel gp) {
		this.gp = gp;
		// Name of object
		setName("key");
		try {
			// Load and up-scale key image
			setImage(ImageIO.read(getClass().getResourceAsStream("/object/key.png")));
			getuTool().scaleImage(getImage(), gp.getTileSize(), gp.getTileSize());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public GamePanel getGp() {
		return gp;
	}
}
