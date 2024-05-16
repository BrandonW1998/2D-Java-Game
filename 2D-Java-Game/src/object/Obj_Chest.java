package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class Obj_Chest extends Obj {

	public Obj_Chest() {
		setName("chest");
		try {
			setImage(ImageIO.read(getClass().getResourceAsStream("/object/chest.png")));
		} catch (IOException e) {
			e.printStackTrace();
		}
		setCollision(true);
	}
}
