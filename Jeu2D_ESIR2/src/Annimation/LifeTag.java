package Annimation;

import Utils.Rect;
import main.GamePanel;

import java.awt.*;

public class LifeTag extends Tagbase {
    float m_life;

    public LifeTag( Rect rect_, float damage_){
        super(rect_);
        lifeTime = 60;
        m_life = damage_;

    }


    public void draw(Graphics2D g2) {
        //super.draw(g2);
        g2.setColor(Color.green);
        g2.drawString("+"+m_life, (int)Tracked_Rect.x, (int)Tracked_Rect.y);
        g2.setColor(Color.gray);
        //new font for the g2
        

        




    }
}
