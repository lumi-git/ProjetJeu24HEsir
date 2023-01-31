package entity.Collectible;

import Annimation.Annimations.Anim_Collectible_Life;
import Annimation.LifeTag;
import Utils.Rect;
import main.GamePanel;

public class Colectible_Life extends CollectibleBase{

    int m_soin = 1;

    public Colectible_Life( Rect rect_, boolean Collidable) {
        super( Collidable,rect_);
        animator = new Anim_Collectible_Life(this);
    }

    public void Use(){

        new LifeTag(m_player.getRect(),m_soin);

        m_player.setLife(m_player.getLife()+m_soin);

    }

}
