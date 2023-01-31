package Annimation.Annimations;

import RessourceLibrary.ImageLibrary;
import Utils.ImgTool;
import entity.UsableObject.Arrow;
import main.GamePanel;

import java.awt.image.BufferedImage;

public class Anim_Arrow extends AnimBase {
    Arrow go;

    public Anim_Arrow(Arrow go_) {
        super();
        go = go_;

    }

    public BufferedImage getSprite() {

        return ImgTool.rotateImage(ImageLibrary.Arrow,go.direction.getAngle()-(float)Math.toRadians(90));
    }
}
