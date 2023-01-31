package Annimation.Annimations;

import RessourceLibrary.ImageLibrary;
import entity.UsableObject.hands;
import main.GamePanel;

import java.awt.image.BufferedImage;

public class Anim_hands extends AnimBase {
    hands go;

    public Anim_hands(hands go_) {
        super();
        go = go_;

    }

    public BufferedImage getSprite() {
        return ImageLibrary.Player;
    }

}

