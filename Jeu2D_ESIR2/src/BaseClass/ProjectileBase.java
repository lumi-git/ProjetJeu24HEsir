package BaseClass;

import BaseClass.GameObject;
import BaseClass.Weaponbase;
import Utils.ImgTool;
import Utils.States;
import Utils.Vector2Float;
import main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ProjectileBase extends GameObject {

    protected int m_damage;
    protected int LifeTime;
    protected int LifeTimeCounter = 0;
    protected Weaponbase m_owner;


    public ProjectileBase( boolean Collidable) {
        super( Collidable);

    }

    public void setDirection(Vector2Float direction) {
        this.direction = direction;
    }

    public void update() {
        Hitbox =  rect.copy();
        super.update();
        LifeTimeCounter++;
        if (LifeTimeCounter >= LifeTime)
            state = States.DESTROY;

        rect.x -= direction.getX() * speed;
        rect.y -= direction.getY() * speed;
    }


}
