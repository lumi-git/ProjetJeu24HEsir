package Annimation;

import BaseClass.GameObject;
import Utils.Rect;
import Utils.States;
import main.GamePanel;

public class Tagbase extends GameObject {

    float speed;
    float lifeTime;//in frame
    public Tagbase(Rect rect_) {
        super( false);

        gp.addAsynchroneEntity(this);
        rect.x = rect_.x;
        rect.y = rect_.y;
    }

    public void update() {
        Hitbox =  rect.copy();
        super.update();
        if (lifeTime>0) {
            lifeTime--;
            rect.y -= 0.5;
        }
        else {
            state = States.DESTROY;
        }

    }
}
