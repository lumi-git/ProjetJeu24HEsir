package entity.UsableObject;

import Annimation.Annimations.Anim_Arrow;
import BaseClass.ProjectileBase;
import BaseClass.Weaponbase;
import Utils.States;
import entity.Players.Player;
import main.GamePanel;

public class Arrow extends ProjectileBase {


    public Arrow(boolean Collidable, Weaponbase owner_) {
        super(Collidable);
        animator = new Anim_Arrow(this);
        direction = owner_.direction;

        gp.addEntity(this);
        rect.width = 32;
        rect.height = 32;
        m_owner = owner_;
        rect.x = owner_.getPos().getX();
        rect.y = owner_.getPos().getY();
        m_damage = 1;

        Hitbox = rect;
        speed = 5f;
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

