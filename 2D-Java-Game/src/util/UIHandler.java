package util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import main.GamePanel;
import object.Obj_Key;

public class UIHandler {

	// SYSTEMS / TOOLS
	private final GamePanel gp;

	// FONTS
	private final Font arial_24;

	// UI variables
	private int textLength;
	private final int centerScreenX;
	private final int centerScreenY;
	private final BufferedImage keyImage;

	// Message variables
	private String message = "";
	private boolean messageOn = false;
	private int msgPopupLocX;
	private int msgPopupLocY;
	private int messageCount = 0;

	// Dialogue variables
	private String dialogue = "";
	private boolean dialogueOn = false;
	private int dlgPopupLocX;
	private int dlgPopupLocY;

	public UIHandler(GamePanel gp) {
		this.gp = gp;
		arial_24 = new Font("Arial", Font.PLAIN, 24);
		Obj_Key key = new Obj_Key(gp);
		keyImage = key.getImage();
		centerScreenX = gp.getScreenWidth() / 2;
		centerScreenY = gp.getScreenHeight() / 2;
	}

	public void showDialogue(String text) {
		dialogue = text;
		dialogueOn = true;
	}

	public void exitDialogue() {
		dialogueOn = false;
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
			textLength = (int) frame.getFontMetrics().getStringBounds(message, frame).getWidth();
			msgPopupLocX = centerScreenX - textLength / 2;
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
		if (dialogueOn) {
			textLength = (int) frame.getFontMetrics().getStringBounds(dialogue, frame).getWidth();
			dlgPopupLocX = centerScreenX - textLength / 2;
			dlgPopupLocY = centerScreenY - gp.getTileSize();

			frame.setColor(Color.black);
			frame.drawString(dialogue, dlgPopupLocX + 1, dlgPopupLocY + 1);
			frame.setColor(Color.white);
			frame.drawString(dialogue, dlgPopupLocX, dlgPopupLocY);
		}
	}
}
