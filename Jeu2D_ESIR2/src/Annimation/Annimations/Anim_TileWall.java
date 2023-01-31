package Annimation.Annimations;

import RessourceLibrary.ImageLibrary;
import main.GamePanel;
import tile.Tile;

import java.awt.image.BufferedImage;

public class Anim_TileWall extends AnimBase{
    Tile go;
    public Anim_TileWall(Tile tile) {
        super();
        go = tile;
    }

    public BufferedImage getSprite() {
        return ImageLibrary.TileWall;
    }

}
