package entity.Enemis;

import Annimation.Annimations.Anim_Enemis1;
import BaseClass.EnemisBase;
import Utils.Rect;

import entity.Players.Player;
import entity.UsableObject.Sword;
import main.GamePanel;

public class FlyingEnnemi extends EnemisBase {


	public FlyingEnnemi( Player player, float x0, float y0) {
		super(false,player);
		animator = new Anim_Enemis1(this);
		initHitbox();
		rect = new Rect(x0,y0,gp.tileSize,gp.tileSize);
		m_weapon = new Sword(false,this);
		m_weapon.setRadius(10);
		m_weapon.setAmmo(2);
		m_weapon.setMaxAmmo(2);
		speed = 0.6f;
		InvincibilityFrames = 1;
		life = 20;
		maxLife=20;

	}


	public void update() {
		Hitbox =  rect.copy();
		super.update();
		target(m_target.getPos());
		rect.x -= direction.getX()*speed;
		rect.y -= direction.getY()*speed;


	}



}
