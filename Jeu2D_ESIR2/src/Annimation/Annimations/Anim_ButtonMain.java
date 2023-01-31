package Annimation.Annimations;

import RessourceLibrary.ImageLibrary;
import Utils.Button;
import Utils.ImgTool;
import main.GamePanel;

import java.awt.image.BufferedImage;

public class Anim_ButtonMain extends AnimBase {
    Button go;
    public Anim_ButtonMain(Button tile) {
        super();
        go = tile;
    }

    public BufferedImage getSprite() {

        return ImageLibrary.Button_Main;
    }

}

