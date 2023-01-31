package Annimation.Annimations;

import RessourceLibrary.ImageLibrary;
import main.GamePanel;
import tile.Tile;
import tile.Tiles.TileWater;

import java.awt.image.BufferedImage;

public class Anim_WaterTile extends AnimBase{

    Tile go;
    public Anim_WaterTile(Tile tile) {
        super();
        go = tile;
    }

    public BufferedImage getSprite() {

        if (go.PlayerOnTile()) {
            return ImageLibrary.Player;

        }
        return ImageLibrary.TileWater;
    }


}
