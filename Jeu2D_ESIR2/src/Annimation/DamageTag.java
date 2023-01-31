package Annimation;

import Utils.Rect;
import main.GamePanel;

import java.awt.*;

public class DamageTag extends Tagbase {

    float m_damage;

    public DamageTag( Rect rect_, float damage_){
        super(rect_);
        lifeTime = 60;
        m_damage = damage_;
    }

    public void draw(Graphics2D g2) {
        g2.setColor(Color.RED);
        g2.drawString("-"+m_damage, (int)Tracked_Rect.x, (int)Tracked_Rect.y);
        g2.setColor(Color.gray);
    }





}
