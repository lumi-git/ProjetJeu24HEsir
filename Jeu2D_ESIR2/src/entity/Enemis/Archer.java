package entity.Enemis;

import Annimation.Annimations.Anim_Archer;
import BaseClass.EnemisBase;
import Utils.Rect;
import entity.Players.Player;
import entity.UsableObject.Bow;
import main.GamePanel;

public class Archer extends EnemisBase {

    Rect minRange;

    public Archer( Player player, float x0, float y0) {
        super(false,player);
        animator = new Anim_Archer(this);
        rect = new Rect(x0,y0,gp.tileSize,gp.tileSize);
        initHitbox();
        minRange = new Rect(x0,y0,500,500);
        m_weapon = new Bow(false,this);
        m_weapon.setRadius(10);
        m_weapon.setAmmo(2);
        m_weapon.setMaxAmmo(2);
        m_weapon.setReloadSpeed(300);
        speed = 0.2f;
        InvincibilityFrames = 1;
        life = 10;
        maxLife=10;


    }

    public void update() {
        Hitbox =  rect.copy();
        super.update();
        target(m_target.getPos());
        if( ! minRange.intersects(m_target.Hitbox)){
            rect.x -= direction.getX()*speed;
            rect.y -= direction.getY()*speed;

            minRange.x = rect.x-minRange.width/2;
            minRange.y = rect.y-minRange.height/2;
        }
        else{
            attack();
        }

    }
}
