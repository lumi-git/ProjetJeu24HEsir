package entity.UsableObject;

import Annimation.Annimations.Anim_Bow;
import BaseClass.LivingEntity;
import BaseClass.Weaponbase;
import main.GamePanel;

public class Bow extends Weaponbase {
    float Range;

    public Bow( boolean Collidable, LivingEntity owner_) {
        super( Collidable, owner_);
        Range=15;
        Hitbox.height=32;
        Hitbox.width=32;
        animator = new Anim_Bow(this);

    }

    public void update() {
        Hitbox = rect.copy();
        rect.x = m_owner.getPos().getX()-direction.getX()*Radius; ;
        rect.y = m_owner.getPos().getY()-direction.getY()*Radius; ;
        super.update();
    }

    @Override
    public void Fire() {
        new Arrow(false,this);

    }

}
