package BaseClass;

import Utils.States;
import main.GamePanel;

public class EnemisBase extends LivingEntity {

    protected LivingEntity m_target;


    public EnemisBase( boolean Collidable, LivingEntity target) {
        super(Collidable);
        gp.NewMob(this);
        m_target = target;
        hasReloadBar = true;
        hasLifeBar = true;
    }

    public void initHitbox(){
        Hitbox.x = rect.x;
        Hitbox.y = rect.y;
        Hitbox.width = rect.width;
        Hitbox.height = rect.height;
    }

    public LivingEntity getTarget(){
        return m_target;
    }


    public void update(){
        super.update();
        m_weapon.update();
        m_weapon.direction = direction.normalize();
        if(getLife()<=0){
            state = States.DESTROY;
        }

        //si le joueur est touché par l'enemis, le joueur perd des points de vie

        if(m_target.Hitbox.intersects(m_weapon.Hitbox)){
            super.attack();
        }

    }
}
