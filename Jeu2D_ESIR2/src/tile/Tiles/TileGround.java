package tile.Tiles;

import Annimation.Annimations.Anim_TileGround;
import main.GamePanel;
import tile.Tile;

public class TileGround extends Tile {

    public TileGround(float x,float y) {
        super( false,x,y);
        animator = new Anim_TileGround(this);
    }




}

