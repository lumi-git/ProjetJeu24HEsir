package Annimation.Annimations;

import RessourceLibrary.ImageLibrary;
import Utils.Button;
import Utils.ImgTool;
import main.GamePanel;
import tile.Tile;

import java.awt.image.BufferedImage;

public class Anim_ButtonPlay extends AnimBase {
    Button go;

    public Anim_ButtonPlay(Button tile) {
        super();
        go = tile;
    }

    public BufferedImage getSprite() {


        return ImageLibrary.Button_Play;

    }
}
