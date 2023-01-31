package Annimation.Annimations;

import RessourceLibrary.ImageLibrary;
import entity.UsableObject.AutoShuriken;
import main.GamePanel;

import java.awt.image.BufferedImage;

public class Anim_AutoShuriken extends AnimBase {
    AutoShuriken go;

    public Anim_AutoShuriken(AutoShuriken go_) {
        super();
        go = go_;

    }

    public BufferedImage getSprite() {
        return ImageLibrary.AutoShuriken;
    }

}
