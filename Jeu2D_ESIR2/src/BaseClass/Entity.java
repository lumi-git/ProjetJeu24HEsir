package BaseClass;

import java.awt.image.BufferedImage;

import Utils.Rect;
import Utils.Vector2;
import Utils.Vector2Float;
import main.GamePanel;

public class Entity {
	public Rect Hitbox;
	public Rect rect;
	public float speed;
	public BufferedImage idleImage;
	public GamePanel gp;

	public Entity() {
		rect = new Rect(0,0, 0, 0);
		Hitbox = new Rect(0,0, 0, 0);
		gp = GamePanel.getInstance();
	}

	public Vector2Float getCenterPos(){

		return new Vector2Float(rect.x + rect.width/2, rect.y + rect.height/2);
	}

	public Vector2Float getPos(){

		return new Vector2Float(rect.x , rect.y );
	}

	public Rect getRect(){
		return rect;
	}


}
