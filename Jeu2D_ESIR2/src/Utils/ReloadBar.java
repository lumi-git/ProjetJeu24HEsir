package Utils;

import BaseClass.GameObject;
import BaseClass.LivingEntity;
import main.GamePanel;

import java.awt.*;

public class ReloadBar extends GameObject {

    LivingEntity owner;

    public ReloadBar( LivingEntity owner_) {
        super( false);
        owner = owner_;
        rect.height = 5;
    }

    // call these methode in the update of the owner to display it's ammo count
    public void update() {
        Hitbox =  rect.copy();
        rect.width = (float)(owner.getWeapon().getAmmo()/(owner.getWeapon().getMaxAmmo()*1.0))*100;
        super.update();

    }

    public void draw(Graphics2D g2) {
        g2.drawRect((int)owner.Tracked_Rect.x,(int)owner.Tracked_Rect.y-40,(int)Tracked_Rect.width,(int)Tracked_Rect.height);
        g2.setColor(Color.YELLOW);
        g2.fillRect((int)owner.Tracked_Rect.x,(int)owner.Tracked_Rect.y-40,(int)Tracked_Rect.width,(int)Tracked_Rect.height);
        g2.setColor(Color.darkGray);

        if(gp.getConfig().showHitbox)
            g2.drawRect((int)Tracked_Hitbox.x,(int)Tracked_Hitbox.y,(int)Tracked_Hitbox.width,(int)Tracked_Hitbox.height);

    }
}
