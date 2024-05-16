package util;

import main.GamePanel;
import object.Obj_Chest;
import object.Obj_Door;
import object.Obj_Key;

public class AssetHandler {

	GamePanel gp;

	public AssetHandler(GamePanel gp) {
		this.gp = gp;
	}

	public void setObject() {

		gp.getObjArray()[0] = new Obj_Key();
		gp.getObjArray()[0].setWorldX(gp.getTileSize() * 8);
		gp.getObjArray()[0].setWorldY(gp.getTileSize() * 8);

		gp.getObjArray()[1] = new Obj_Door();
		gp.getObjArray()[1].setWorldX(gp.getTileSize() * 10);
		gp.getObjArray()[1].setWorldY(gp.getTileSize() * 10);

		gp.getObjArray()[2] = new Obj_Chest();
		gp.getObjArray()[2].setWorldX(gp.getTileSize() * 13);
		gp.getObjArray()[2].setWorldY(gp.getTileSize() * 6);
	}
}
