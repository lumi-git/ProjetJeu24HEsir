package Annimation.Annimations;

import BaseClass.EnemisBase;
import RessourceLibrary.ImageLibrary;
import main.GamePanel;

import java.awt.image.BufferedImage;

public class Anim_Enemis1 extends AnimBase {

    EnemisBase go;
    public Anim_Enemis1(EnemisBase go_) {
        super();
        go = go_;

    }

    public BufferedImage getSprite() {
        return ImageLibrary.Enemi1;
    }

}

