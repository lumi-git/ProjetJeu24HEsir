package entity;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import Annimation.Annimations.*;
import BaseClass.GameObject;
import RessourceLibrary.ImageLibrary;
import Utils.MyCursor;
import Utils.Rect;
import Utils.Button;
import entity.UsableObject.AutoShuriken;
import entity.UsableObject.Bow;
import entity.UsableObject.Sword;
import BaseClass.Weaponbase;
import main.GamePanel;

public class Hud extends GameObject {


	ArrayList<Integer> life;

	MyCursor m_cursor;

	Button SwitchToBowButton;
	Button SwitchToSwordButton;
	Button SwitchToShurikenButton;
	Rect InventoryRect;


	BufferedImage heartFilled;
	BufferedImage heartVoid;

	public Hud( MyCursor cursor) {
		super(false);

		heartFilled = ImageLibrary.HUD_health_filled;
		heartVoid = ImageLibrary.HUD_health_void;


		life = new ArrayList<Integer>();
		for(int i=0;i<gp.getPlayer().maxLife;i++) {
			life.add(1);
		}
		InventoryRect = new Rect(10,50,70,180);
		m_cursor = cursor;
		SwitchToBowButton = new Button(new Rect(InventoryRect.x+10,InventoryRect.y,50,50),m_cursor);
		SwitchToBowButton.ChangeShapeWhenHover(true);
		SwitchToBowButton.setAnimator(new Anim_BowButton(SwitchToBowButton));

		SwitchToSwordButton = new Button(new Rect(InventoryRect.x+10,InventoryRect.y+60,50,50),m_cursor);
		SwitchToSwordButton.ChangeShapeWhenHover(true);
		SwitchToSwordButton.setAnimator(new Anim_SwordButton(SwitchToSwordButton));

		SwitchToShurikenButton = new Button(new Rect(InventoryRect.x+10,InventoryRect.y+120,50,50),m_cursor);
		SwitchToShurikenButton.ChangeShapeWhenHover(true);
		SwitchToShurikenButton.setAnimator(new Anim_ShurikenButton(SwitchToShurikenButton));
	}

	
	
	public void draw(Graphics2D g2) {
		// r�cup�re l'image du joueur
		BufferedImage image = idleImage;
		g2.setColor(new Color(100,100,100,100));
		g2.fillRect((int)InventoryRect.x, (int)InventoryRect.y, (int)InventoryRect.width, (int)InventoryRect.height);

		SwitchToBowButton.draw(g2);
		SwitchToSwordButton.draw(g2);
		SwitchToShurikenButton.draw(g2);
		// affiche le personnage avec l'image "image", avec les coordonn�es x et y, et de taille tileSize (16x16) sans �chelle, et 48x48 avec �chelle)
		int i=0;
		for(int l : life) {
			if(l==1) {
				g2.drawImage(heartFilled, i*25, 0, 20, 20, null);
			}
			else {
				g2.drawImage(heartVoid, i*25, 0, 20, 20, null);
			}
			i++;
		}

		if(gp.getConfig().showFPS)
		{
			g2.setFont(new Font("Arial", Font.BOLD, 40));
			g2.drawString(((int)gp.getCurrentFps())+"",gp.screenWidth-100,gp.screenHeight-10);
		}

	}
	

	public void update() {
		SwitchToBowButton.update();
		SwitchToSwordButton.update();
		SwitchToShurikenButton.update();
		if(SwitchToBowButton.isPressed()) {
			Weaponbase w  = new Bow(false,gp.getPlayer());
			gp.getPlayer().TakeNewWeapon(w);
		}
		if(SwitchToSwordButton.isPressed()) {
			Weaponbase w  = new Sword(false,gp.getPlayer());
			w.setDamage(5);
			gp.getPlayer().TakeNewWeapon(w);
		}
		if(SwitchToShurikenButton.isPressed()) {
			Weaponbase w  = new AutoShuriken(false,gp.getPlayer());
			w.setDamage(5);
			gp.getPlayer().TakeNewWeapon(w);
		}

		int i=0;
		for(i=0;i<gp.getPlayer().maxLife;i++) {
			
			if(i<gp.getPlayer().getLife()) {
				life.set(i, 1);
			}else {
				life.set(i, 0);
			}
		}

		
	}
}
