package entity.UsableObject;

import Annimation.Annimations.Anim_AutoShuriken;
import BaseClass.LivingEntity;
import BaseClass.Weaponbase;
import Utils.Vector2Float;
import main.GamePanel;

public class AutoShuriken extends Weaponbase {

    float Range;
    public AutoShuriken(boolean Collidable, LivingEntity owner_) {
        super(Collidable, owner_);
        animator = new Anim_AutoShuriken(this);
        Range=15;
        Hitbox.height=32;
        Hitbox.width=32;
    }

    public void update() {
        Hitbox = rect.copy();
        super.update();
        rect.x = m_owner.getPos().getX()+20;
        rect.y = m_owner.getPos().getY()+20;

        if(gp.GetFrame()%300==0)
            Fire();
    }

    @Override
    public void Fire() {
        for(int i =0;i<2;i++){
            for(int j =0;j<2;j++){
                Shuriken a = new Shuriken(false,this);
                a.setDirection(new Vector2Float((float)Math.pow(-1,j),(float)Math.pow(-1,i)));
            }
        }

    }


}
