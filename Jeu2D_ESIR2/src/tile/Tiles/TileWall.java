package tile.Tiles;

import Annimation.Annimations.Anim_TileGround;
import Annimation.Annimations.Anim_TileWall;
import main.GamePanel;
import tile.Tile;

public class TileWall extends Tile {
    public TileWall(float x, float y) {
        super(true,x,y);
        animator = new Anim_TileWall(this);
    }

}
