package Utils;

import BaseClass.GameObject;
import BaseClass.LivingEntity;
import main.GamePanel;

import java.awt.*;

public class LifeBar extends GameObject {

    LivingEntity owner;

    public LifeBar(LivingEntity owner_) {
        super(false);
        owner = owner_;

        rect.height = 5;
    }


    //call these methode in the update of the owner to display it's life
    public void update() {
        rect.width = (owner.getLife()/owner.maxLife)*100;
        Hitbox =  rect.copy();
        super.update();

    }

    public void draw(Graphics2D g2) {
        g2.drawRect((int)owner.Tracked_Rect.x,(int)owner.Tracked_Rect.y-50,(int)Tracked_Rect.width,(int)Tracked_Rect.height);
        g2.setColor(Color.GREEN);
        g2.fillRect((int)owner.Tracked_Rect.x,(int)owner.Tracked_Rect.y-50,(int)Tracked_Rect.width,(int)Tracked_Rect.height);
        g2.setColor(Color.darkGray);

        if(gp.getConfig().showHitbox)
            g2.drawRect((int)Tracked_Hitbox.x,(int)Tracked_Hitbox.y,(int)Tracked_Hitbox.width,(int)Tracked_Hitbox.height);

    }





}
