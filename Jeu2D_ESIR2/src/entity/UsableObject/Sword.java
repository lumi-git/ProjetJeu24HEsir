package entity.UsableObject;

import Annimation.Annimations.Anim_Sword;
import BaseClass.LivingEntity;
import BaseClass.Weaponbase;
import main.GamePanel;

public class Sword extends Weaponbase {

    public Sword(boolean Collidable, LivingEntity owner_) {
        super(Collidable, owner_);
        Hitbox.height=32;
        Hitbox.width=32;
        animator = new Anim_Sword(this);
    }

    public void update() {

        rect.x = m_owner.getPos().getX()-direction.getX()*Radius; ;
        rect.y = m_owner.getPos().getY()-direction.getY()*Radius; ;
        Hitbox.x =rect.x-direction.getX()*Radius*0.5f;
        Hitbox.y =rect.y-direction.getY()*Radius*0.5f;
        super.update();
    }

    @Override
    protected void Fire(){
        boolean p = Hitbox.intersects(m_owner.getTarget().Hitbox);
        if(p){
            m_owner.getTarget().RecieveAttak(m_damage);
        }
    }

}