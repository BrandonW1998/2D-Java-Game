package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class Obj_Key extends Obj {

	// Key Constructor
	public Obj_Key() {
		// Name of object
		setName("key");
		try {
			// Load key image
			setImage(ImageIO.read(getClass().getResourceAsStream("/object/key.png")));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
