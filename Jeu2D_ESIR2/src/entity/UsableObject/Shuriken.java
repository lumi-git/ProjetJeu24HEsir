package entity.UsableObject;

import Annimation.Annimations.Anim_Shuriken;
import BaseClass.ProjectileBase;
import BaseClass.Weaponbase;
import Utils.States;
import entity.Players.Player;
import main.GamePanel;

public class Shuriken extends ProjectileBase {

    public Shuriken(boolean Collidable, Weaponbase owner_) {
        super(Collidable);
        animator = new Anim_Shuriken(this);
        //ImageRotated = ImgTool.rotateImage(idleImage,direction.getAngle()-(float)Math.toRadians(90.0));
        gp.addEntity(this);
        rect.width = 32;
        rect.height = 32;
        m_owner = owner_;
        rect.x = owner_.getPos().getX();
        rect.y = owner_.getPos().getY();
        m_damage = 3;
        direction = owner_.direction;
        Hitbox = rect;
        speed = 3f;
        LifeTime = 100;
    }

    public void update() {
        super.update();

        if(m_owner.m_owner instanceof Player){
            if(gp.getMobCollision().Iscolling(Hitbox).getSecond()){
                gp.getMobCollision().Iscolling(Hitbox).getFirst().RecieveAttak(m_damage);
                state = States.DESTROY;
            }
        }
        else
        if (gp.getPlayer().getRect().intersects(Hitbox)) {
            gp.getPlayer().RecieveAttak(m_damage);
            state = States.DESTROY;
        }
    }

}

