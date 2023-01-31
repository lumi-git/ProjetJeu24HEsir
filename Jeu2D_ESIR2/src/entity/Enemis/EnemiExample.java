package entity.Enemis;

import Annimation.Annimations.Anim_Enemis1;
import BaseClass.EnemisBase;
import BaseClass.Weaponbase;
import Utils.Rect;
import entity.Players.Player;
import entity.UsableObject.AutoShuriken;
import entity.UsableObject.Bow;
import entity.UsableObject.Sword;
import entity.UsableObject.hands;
import main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class EnemiExample extends EnemisBase {
    Rect minRange;
    Weaponbase m_weapon2;


    public EnemiExample( Player player, float x0, float y0) {
        super(false,player);

        initHitbox();
        minRange = new Rect(x0,y0,100,100);
        rect = new Rect(x0,y0,gp.tileSize,gp.tileSize);
        m_weapon = new AutoShuriken(false,this);
        m_weapon.setRadius(10);
        m_weapon.setAmmo(2);
        m_weapon.setMaxAmmo(2);
        m_weapon2 = new hands(false,this);
        m_weapon2.setRadius(10);
        m_weapon2.setAmmo(2);
        m_weapon2.setMaxAmmo(2);
        speed = 0.1f;
        InvincibilityFrames = 1;
        life = 10;
        maxLife=10;
        animator = new Anim_Enemis1(this);
    }

    @Override
    public void attack(){
        m_weapon.Use();
        m_weapon2.Use();
    }

    public void update() {
        Hitbox =  rect.copy();
        super.update();
        m_weapon2.update();
        m_weapon2.direction = direction.normalize();
        target(m_target.getPos());
        if(!minRange.intersects(m_target.getRect())) {
            rect.x -= direction.getX()*speed;
            rect.y -= direction.getY()*speed;
            minRange.x = rect.x-minRange.width/2;
            minRange.y = rect.y-minRange.height/2;
        }
        else{
            attack();
        }
    }

    public void draw(Graphics2D g2) {
        super.draw(g2);
        m_weapon2.draw(g2);
    }
}