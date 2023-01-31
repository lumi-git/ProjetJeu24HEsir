package tile;

import BaseClass.GameObject;
import Utils.Rect;
import main.GamePanel;

public class Tile extends GameObject{

	private boolean  playerIsOnTile;



	public Tile( boolean Collidable,float x,float y) {
		super(Collidable);
		TileManager.tiles.add(this);
		rect = new Rect(x,y,gp.tileSize,gp.tileSize);

	}

	public boolean PlayerOnTile() {
		return playerIsOnTile;
	}


	public Tile copy(){
		Tile tile = new Tile(collidable,rect.x,rect.y);
		tile.animator =animator;
		return tile;
	}

	public void update(){
		Hitbox =  rect.copy();
		super.update();
		if(gp.getPlayer().getRect().intersects(rect)){
			playerIsOnTile = true;
		}
		else{
			playerIsOnTile = false;
		}

	}


}
