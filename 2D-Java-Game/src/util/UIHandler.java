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
	private int messageLength;
	private int messageCount = 0;
	private final int centerScreenX;
	private final int centerScreenY;
	private int msgPopupLocX;
	private int msgPopupLocY;

	public UIHandler(GamePanel gp) {
		this.gp = gp;
		arial_24 = new Font("Arial", Font.PLAIN, 24);
		Obj_Key key = new Obj_Key(gp);
		keyImage = key.getImage();
		centerScreenX = gp.getScreenWidth() / 2;
		centerScreenY = gp.getScreenHeight() / 2;
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
			messageLength = (int) frame.getFontMetrics().getStringBounds(message, frame).getWidth();
			msgPopupLocX = centerScreenX - messageLength / 2;
			msgPopupLocY = centerScreenY - gp.getTileSize() / 2;

			frame.setColor(Color.black);
			frame.drawString(message, msgPopupLocX + 1, msgPopupLocY - (messageCount / 2) + 1);
			frame.setColor(Color.white);
			frame.drawString(message, msgPopupLocX, msgPopupLocY - (messageCount / 2));

			messageCount++;
			if (messageCount > 60) {
				messageCount = 0;
				messageOn = false;
			}
		}
	}
}
