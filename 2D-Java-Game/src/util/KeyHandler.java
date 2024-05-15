package util;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

	public boolean up, down, left, right, interact;

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// Capture key press
		int code = e.getKeyCode();

		if (code == KeyEvent.VK_W)
			up = true;
		if (code == KeyEvent.VK_S)
			down = true;
		if (code == KeyEvent.VK_A)
			left = true;
		if (code == KeyEvent.VK_D)
			right = true;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// Capture key press
		int code = e.getKeyCode();

		if (code == KeyEvent.VK_W)
			up = false;
		if (code == KeyEvent.VK_S)
			down = false;
		if (code == KeyEvent.VK_A)
			left = false;
		if (code == KeyEvent.VK_D)
			right = false;
	}
}
