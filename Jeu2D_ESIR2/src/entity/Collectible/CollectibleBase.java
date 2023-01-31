package entity.Collectible;

import BaseClass.GameObject;
import CameraPackage.Annimations.TargetCollectible;
import CameraPackage.CameraAnimator;
import Utils.Rect;
import Utils.States;
import entity.Players.Player;
import main.GamePanel;

public class CollectibleBase extends GameObject {

    protected Player m_player;
    public CollectibleBase( boolean Collidable, Rect rect_) {
        super(Collidable);
        gp.NewCollectible(this);
        m_player = gp.getPlayer();
        rect = rect_;



        //the camera will target the collectible during a certain time
        CameraAnimator.NewAnnimation(new TargetCollectible(300,this));



    }

    //use this methode to update the basic comportement of the collectible
    public void update(){
        Hitbox = rect.copy();
        super.update();

        if(Hitbox.intersects(m_player.Hitbox)){
            Use();
            state = States.DESTROY;
        }
    }
    //code this methode to implement the behaviour of the collectible you want
    public void Use(){

    }

}
