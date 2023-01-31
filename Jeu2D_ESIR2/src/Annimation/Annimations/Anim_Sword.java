package Annimation.Annimations;

import RessourceLibrary.ImageLibrary;
import Utils.ImgTool;
import entity.UsableObject.Sword;
import main.GamePanel;

import java.awt.image.BufferedImage;

public class Anim_Sword extends AnimBase {
    Sword go;
    public Anim_Sword( Sword go_) {
        super();
        go = go_;

    }

    public BufferedImage getSprite() {
        return ImgTool.rotateImage(ImageLibrary.Sword,go.direction.getAngle()-(float)Math.toRadians(90.0));
    }

}

