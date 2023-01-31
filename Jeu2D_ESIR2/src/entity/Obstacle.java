package entity;

import BaseClass.GameObject;
import Utils.Rect;
import main.GamePanel;

public class Obstacle extends GameObject {

    public Obstacle(float x0,float y0) {
        super(true);
        rect = new Rect(x0,y0,gp.tileSize,gp.tileSize);
    }


}

