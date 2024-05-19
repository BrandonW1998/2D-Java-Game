package util;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

	// Directional button flags
	private boolean up, down, left, right;

	// Interaction button flag
	private boolean interact;

	@Override
	public void keyTyped(KeyEvent e) {
	}

	// Listen for key press
	@Override
	public void keyPressed(KeyEvent e) {
		// Capture key press
		int code = e.getKeyCode();

		// (WASD) Movement
		if (code == KeyEvent.VK_W)
			up = true;
		if (code == KeyEvent.VK_S)
			down = true;
		if (code == KeyEvent.VK_A)
			left = true;
		if (code == KeyEvent.VK_D)
			right = true;

		// (J) Interaction
		if (code == KeyEvent.VK_J)
			interact = true;
	}

	// Listen for key release
	@Override
	public void keyReleased(KeyEvent e) {
		// Capture key press
		int code = e.getKeyCode();

		// (WASD) Movement
		if (code == KeyEvent.VK_W)
			up = false;
		if (code == KeyEvent.VK_S)
			down = false;
		if (code == KeyEvent.VK_A)
			left = false;
		if (code == KeyEvent.VK_D)
			right = false;

		// (J) Interaction
		if (code == KeyEvent.VK_J)
			interact = false;
	}

	// GETTER / SETTER METHODS
	public boolean isUp() {
		return up;
	}

	public void setUp(boolean up) {
		this.up = up;
	}

	public boolean isDown() {
		return down;
	}

	public void setDown(boolean down) {
		this.down = down;
	}

	public boolean isLeft() {
		return left;
	}

	public void setLeft(boolean left) {
		this.left = left;
	}

	public boolean isRight() {
		return right;
	}

	public void setRight(boolean right) {
		this.right = right;
	}

	public boolean isInteract() {
		return interact;
	}

	public void setInteract(boolean interact) {
		this.interact = interact;
	}
}
