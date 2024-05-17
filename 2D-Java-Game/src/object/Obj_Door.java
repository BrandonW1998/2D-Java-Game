package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class Obj_Door extends Obj {

	// Door Constructor
	public Obj_Door() {
		// Name of object
		setName("door");
		try {
			// Load door image
			setImage(ImageIO.read(getClass().getResourceAsStream("/object/door.png")));
		} catch (IOException e) {
			e.printStackTrace();
		}
		// Door has collision
		setCollision(true);
	}
}
