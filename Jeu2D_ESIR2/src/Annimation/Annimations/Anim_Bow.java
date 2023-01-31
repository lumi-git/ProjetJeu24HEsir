package Annimation.Annimations;

import RessourceLibrary.ImageLibrary;
import Utils.ImgTool;
import entity.UsableObject.Bow;
import main.GamePanel;

import java.awt.image.BufferedImage;

public class Anim_Bow extends AnimBase {
    Bow go;

    public Anim_Bow( Bow go_) {
        super();
        go = go_;

    }

    public BufferedImage getSprite() {
        return ImgTool.rotateImage(ImageLibrary.Bow,go.direction.getAngle()-(float)Math.toRadians(-180.0));
    }

}

