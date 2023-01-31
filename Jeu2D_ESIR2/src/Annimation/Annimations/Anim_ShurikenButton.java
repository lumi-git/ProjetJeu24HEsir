package Annimation.Annimations;

import RessourceLibrary.ImageLibrary;
import Utils.Button;
import main.GamePanel;

import java.awt.image.BufferedImage;

public class Anim_ShurikenButton extends AnimBase {
    Button go;
    public Anim_ShurikenButton(Button tile) {
        super();
        go = tile;
    }

    public BufferedImage getSprite() {
        return ImageLibrary.Shuriken;
    }

}

