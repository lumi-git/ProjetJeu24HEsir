package Annimation.Annimations;

import RessourceLibrary.ImageLibrary;
import Utils.ImgTool;
import Utils.Vector2Float;
import entity.UsableObject.Shuriken;
import main.GamePanel;

import java.awt.image.BufferedImage;

public class Anim_Shuriken extends AnimBase {
    Shuriken go;
    int CounterRotation = 0;
    public Anim_Shuriken(Shuriken go_) {
        super();
        go = go_;

    }

    public BufferedImage getSprite() {
        CounterRotation++;


        return ImgTool.rotateImage(ImageLibrary.Shuriken,go.direction.getAngle()-(float)Math.toRadians(CounterRotation*6));
    }
}
