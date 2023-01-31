package entity.UsableObject;

import Annimation.Annimations.Anim_hands;
import BaseClass.LivingEntity;
import BaseClass.Weaponbase;
import main.GamePanel;

public class hands extends Weaponbase {

    public hands( boolean Collidable, LivingEntity owner_) {
        super( Collidable, owner_);
        animator = new Anim_hands(this);;//may cause bug
        m_damage = 1;
        Hitbox = rect;

    }

    public void update() {

        rect.x = m_owner.getPos().getX()-direction.getX()*Radius;
        rect.y = m_owner.getPos().getY()-direction.getY()*Radius;
        Hitbox.x =rect.x-direction.getX()*Radius ;
        Hitbox.y =rect.y-direction.getY()*Radius;
        super.update();
    }



}
