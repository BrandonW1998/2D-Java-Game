package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class Obj_Chest extends Obj {

	// Chest Constructor
	public Obj_Chest() {
		// Name of object
		setName("chest");
		try {
			// Load chest image
			setImage(ImageIO.read(getClass().getResourceAsStream("/object/chest.png")));
		} catch (IOException e) {
			e.printStackTrace();
		}
		// Chest has collision
		setCollision(true);
	}
}
