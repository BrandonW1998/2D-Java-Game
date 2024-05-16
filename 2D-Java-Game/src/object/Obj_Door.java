package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class Obj_Door extends Obj {

	public Obj_Door() {
		setName("door");
		try {
			setImage(ImageIO.read(getClass().getResourceAsStream("/object/door.png")));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
