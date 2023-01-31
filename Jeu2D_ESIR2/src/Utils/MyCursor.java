package Utils;

import BaseClass.GameObject;
import main.GamePanel;

import java.awt.*;

//cursor created to get easily the mouse states (button and location on the screen)
public class MyCursor extends GameObject {

    MouseHandler m_mouseHandler;
    public MyCursor( boolean Collidable,MouseHandler mouseHandler) {
        super( Collidable);
        m_mouseHandler = mouseHandler; ;
        rect.width = 10;
        rect.height = 10;
    }

    public void update() {

        rect.x =  (float)MouseInfo.getPointerInfo().getLocation().getX() - gp.getLocationOnScreen().x;
        rect.y =  (float)MouseInfo.getPointerInfo().getLocation().getY() - gp.getLocationOnScreen().y;
        Hitbox =  rect.copy();

    }

    public boolean isClicked(int bt) {

        return m_mouseHandler.isPressed(bt);
    }

    @Override
    public void draw(Graphics2D g2) {
        super.draw(g2);
        g2.drawOval((int)rect.x,(int)rect.y,(int)rect.width,(int)rect.height);
    }
}
