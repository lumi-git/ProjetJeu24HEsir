package Annimation.Annimations;

import RessourceLibrary.ImageLibrary;
import Utils.Button;
import Utils.ImgTool;

import java.awt.image.BufferedImage;

public class Anim_ButtonParam extends AnimBase {
    Button go;
    float rotation = 0f;
    float rotationSpeed = 6;
    public Anim_ButtonParam(Button tile) {
        super();
        go = tile;
    }

    public BufferedImage getSprite() {
        if (go.isOver()){
            if (rotation < 360)
                rotation += rotationSpeed;
            else
                rotation = 360;
        }
        else{
            if (rotation > 0)
                rotation -= rotationSpeed;
            else
                rotation = 0;
        }


        return ImgTool.rotateImage(ImageLibrary.Button_Param, (float)Math.toRadians(rotation));

    }
}
