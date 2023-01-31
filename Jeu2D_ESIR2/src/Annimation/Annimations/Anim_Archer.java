package Annimation.Annimations;

import RessourceLibrary.ImageLibrary;
import entity.Enemis.Archer;
import main.GamePanel;

import java.awt.image.BufferedImage;

public class Anim_Archer extends AnimBase {
    Archer go;

    public Anim_Archer( Archer go_) {
        super();
        go = go_;
    }

    public BufferedImage getSprite() {
        return ImageLibrary.Enemi1;
    }
}