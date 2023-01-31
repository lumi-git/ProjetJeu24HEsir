package tile;

import BaseClass.Animable;
import BaseClass.Updateable;
import Utils.States;
import main.GamePanel;
import tile.Tiles.TileWater;

import java.awt.*;
import java.util.ArrayList;

public class TileManager implements Animable, Updateable {

    public static ArrayList<Tile> tiles = new ArrayList<Tile>();
    boolean PlayerIsOnWater = false;

    public TileManager() {

    }


    public static void clearTiles(){
        tiles.clear();
    }

    public void update() {

        PlayerIsOnWater = false;


        for (Tile t : tiles) {
            t.update();

            if(TileWater.PlayerIsOnWater){
                PlayerIsOnWater = true;

            }

        }
        if(PlayerIsOnWater){

            GamePanel.getInstance().getPlayer().speed = 1f;
        }
        else{
            GamePanel.getInstance().getPlayer().speed = 3f;
        }

        tiles.removeIf(tile -> tile.state == States.DESTROY);

    }

    public void draw(Graphics2D g2) {
        for(int i = 0; i < tiles.size(); i++) {
            tiles.get(i).draw(g2);
        }
    }



}
