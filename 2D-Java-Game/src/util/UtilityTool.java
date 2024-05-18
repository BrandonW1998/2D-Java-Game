package util;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class UtilityTool {

	public BufferedImage scaleImage(BufferedImage original, int width, int height) {

		BufferedImage scaledImage = new BufferedImage(width, height, original.getType());
		Graphics2D graphic2d = scaledImage.createGraphics();
		graphic2d.drawImage(original, 0, 0, width, height, null);
		graphic2d.dispose();

		return scaledImage;
	}
}
