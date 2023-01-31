package Annimation.Annimations;

import RessourceLibrary.ImageLibrary;
import entity.Players.Player;
import main.GamePanel;

import java.awt.image.BufferedImage;

public class Anim_Player extends AnimBase {


    Player player;
    public Anim_Player(Player pe_) {
        super();
        player = pe_;
    }

    public BufferedImage getSprite() {
        if(player.IsInvicible()){
            if(gp.GetFrame()%20==0){
                return ImageLibrary.Player_Invincible;
            }

            else
                return ImageLibrary.Player;
        }
        else
            return ImageLibrary.Player;

    }

}
