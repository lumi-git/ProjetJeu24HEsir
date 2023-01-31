package tile.Tiles;

import Annimation.Annimations.Anim_TileGround;
import Annimation.Annimations.Anim_WaterTile;
import main.GamePanel;
import tile.Tile;

public class TileWater extends Tile {

    public static boolean PlayerIsOnWater = false;


    public TileWater(float x, float y) {
        super( false, x, y);
        animator = new Anim_WaterTile( this);
    }

    @Override
    public void update() {
        super.update();
        if(gp.getPlayer().getRect().intersects(rect)){
            PlayerIsOnWater = true;
        }
        else{
            PlayerIsOnWater = false;
        }

    }


}
