package Annimation.Annimations;

import RessourceLibrary.ImageLibrary;
import entity.Collectible.Colectible_Life;
import main.GamePanel;

import java.awt.image.BufferedImage;

public class Anim_Collectible_Life extends AnimBase {
    Colectible_Life go;
    public Anim_Collectible_Life( Colectible_Life go_) {
        super();
        go = go_;

    }

    public BufferedImage getSprite() {

        return ImageLibrary.Colectible_Life;
    }

}
