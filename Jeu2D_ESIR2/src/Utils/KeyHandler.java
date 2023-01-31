package Utils;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener{
	boolean[] touche;
	
	public KeyHandler(){
		touche = new boolean[256];
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// récupère le code du boutton appuyé
		touche [e.getKeyCode()] = true;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		touche [e.getKeyCode()] = false;
	}
	
	public boolean GetKey(int KeyCode) {
		return touche[KeyCode];
	}

	public boolean KeyReleased(int keycode) {
		return touche[keycode];
	}
}
