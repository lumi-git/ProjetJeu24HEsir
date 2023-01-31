package entity.Players;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import Annimation.Annimations.Anim_Player;
import BaseClass.GameObject;
import BaseClass.LivingEntity;
import CameraPackage.*;
import RessourceLibrary.SoundLibrary;
import Utils.*;

import entity.Collision;
import BaseClass.Weaponbase;
import entity.LivingCollision;
import main.GamePanel;

public class Player extends LivingEntity {

	KeyHandler keyH;
	Collision ObstaclesCollision;
	Collision EntityCollision;
	LivingCollision MobCollision ;
	MyCursor cursor;

	//for the deceleration
	Vector2Float TrueSpeed;
	Vector2Float SpeedNormalisation;
	Vector2Float directionMemory;
	float deceleration;
	float dy;
	float dx;

	public Player( KeyHandler keyH,Collision ObCollision_,Collision EnCollision_,LivingCollision mobCollision , MyCursor cursor_) {
		super(false);
		ObstaclesCollision = ObCollision_;
		EntityCollision = EnCollision_;
		MobCollision = mobCollision;
		this.keyH = keyH;
		cursor = cursor_;

		TrueSpeed = new Vector2Float(0,0);
		directionMemory = new Vector2Float(0,0);
		SpeedNormalisation =TrueSpeed;
		deceleration = 0.2f;

		hasReloadBar = true;
		setDefaultValues();

		animator = new Anim_Player( this);

	}

	public void setDefaultValues() {
		// Initialise les valeurs par défaut
		rect = new Rect(100,100,40,40);
		speed = 2.5f;
		life = 20;
		maxLife=20;

	}

	@Override
	public LivingEntity getTarget() {

		return MobCollision.Iscolling(m_weapon.Hitbox).getFirst();
	}

	public MyCursor getCursor() {
		return cursor;
	}

	public void TakeNewWeapon(Weaponbase weapon_) {
		m_weapon = weapon_;
		m_weapon.setRadius(50);
		m_weapon.setMaxAmmo(10);
		m_weapon.setAmmo(10);
		m_weapon.setDamage(50);
	}



	public void update() {

		Hitbox =  rect.copy();
		super.update();

		m_weapon.direction = getTrackedPos().sub(getCursor().getPos()).normalize();
		m_weapon.update();

		direction.setZeros();
		//get the input and set the speed and the direction
		if(keyH.GetKey(KeyEvent.VK_Z)) {
			direction.setY(-1);
			directionMemory.setY(-1);
			TrueSpeed.setY(speed);
		}

		if (keyH.GetKey(KeyEvent.VK_Q)) {
			direction.setX(-1);
			directionMemory.setX(-1);

			TrueSpeed.setX(speed);
		}
		if (keyH.GetKey(KeyEvent.VK_D)) {
			direction.setX(1);
			directionMemory.setX(1);
			TrueSpeed.setX(speed);
		}

		if (keyH.GetKey(KeyEvent.VK_S)) {
			direction.setY(1);
			directionMemory.setY(1);
			TrueSpeed.setY(speed);
		 }

		//deceleration
		TrueSpeed.setX(TrueSpeed.getX()-deceleration);
		TrueSpeed.setY(TrueSpeed.getY()-deceleration);



		if(TrueSpeed.getX()<0)
			TrueSpeed.setX(0);
		if(TrueSpeed.getY()<0)
			TrueSpeed.setY(0);

		SpeedNormalisation = TrueSpeed.normalize();
		dx=TrueSpeed.getX()*directionMemory.getX()*SpeedNormalisation.getX();
		dy=TrueSpeed.getY()*directionMemory.getY()*SpeedNormalisation.getY();
		rect.x += dx;
		rect.y += dy;
		Hitbox =  rect.copy();


		//collision with the obstacles
		if(ObstaclesCollision.Iscolling(Hitbox).getSecond()){

			if(directionMemory.getX()==-1 || directionMemory.getX()==1)
				rect.x-=dx;
			Hitbox = rect.copy();
			if(directionMemory.getY()==-1 || directionMemory.getY()==1)
				rect.y-=dy;
			Hitbox =  rect.copy();
		}

		 //attack if the button left of the mouse is triggered
		 if(cursor.isClicked(1)) {
			 attack();
		 }

	}



}
