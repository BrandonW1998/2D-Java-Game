package util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import main.GamePanel;
import object.Obj_Key;

public class UIHandler {

	private final GamePanel gp;
	private final Font arial_24;
	private final BufferedImage keyImage;
	private boolean messageOn = false;
	private String message = "";
	private int messageCount = 0;
//	private final int playerLocX;
	private final int playerLocY;

	public UIHandler(GamePanel gp) {
		this.gp = gp;
		arial_24 = new Font("Arial", Font.PLAIN, 24);
		Obj_Key key = new Obj_Key();
		keyImage = key.getImage();
//		playerLocX = gp.getScreenWidth() / 2 - (gp.getTileSize() / 2);
		playerLocY = gp.getScreenHeight() / 2 - (gp.getTileSize() / 2);
	}

	public void showMessage(String text) {
		message = text;
		messageOn = true;
	}

	public void draw(Graphics2D frame) {
		frame.setFont(arial_24);
		frame.drawImage(keyImage, 6, 3, gp.getTileSize() / 2, gp.getTileSize() / 2, null);
		frame.setColor(Color.black);
		frame.drawString("x " + gp.getPlayer().getKeyBag(), 36 + 1, 24 + 1);
		frame.setColor(Color.white);
		frame.drawString("x " + gp.getPlayer().getKeyBag(), 36, 24);

		if (messageOn) {
			frame.setColor(Color.black);
			frame.drawString(message, 0 + 1, playerLocY + 1);
			frame.setColor(Color.white);
			frame.drawString(message, 0, playerLocY);
			messageCount++;
			if (messageCount > 60) {
				messageCount = 0;
				messageOn = false;
			}
		}
	}
}
