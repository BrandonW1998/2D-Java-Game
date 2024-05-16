package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class Obj_Key extends Obj {

	public Obj_Key() {
		setName("key");
		try {
			setImage(ImageIO.read(getClass().getResourceAsStream("/object/key.png")));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
