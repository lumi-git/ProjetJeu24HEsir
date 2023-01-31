package Utils;

import Annimation.Annimations.AnimBase;
import Annimation.Annimations.Anim_ButtonPlay;
import Annimation.Annimations.Anim_TileWall;
import BaseClass.Entity;
import BaseClass.PrintableEntity;
import BaseClass.TrackedEntity;
import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class Button extends PrintableEntity {

    public boolean collision = false;

    MyCursor m_cursor;
    Rect HooverRect;
    Rect NormalRect;
    boolean changeShapeWhenHover;
    boolean Isboxed;


    public Button(Rect rect_,MyCursor cursor) {
        super();
        changeShapeWhenHover = false;
        Isboxed = false;
        NormalRect = rect_;
        rect = rect_;
        float ZoomWhenHover = 10;
        HooverRect = new Rect(rect.x-ZoomWhenHover,rect.y-ZoomWhenHover,rect.width+2*ZoomWhenHover,rect.height+2*ZoomWhenHover);
        m_cursor = cursor;
    }


    public void setAnimator(AnimBase animator_) {
        animator = animator_;
    }

    public void ChangeShapeWhenHover(boolean b) {
        changeShapeWhenHover = b;
    }

    public void setIsboxed(boolean isboxed) {
        Isboxed = isboxed;
    }

    //setter et getters pour x et y
    public void setX(int x) {
        this.rect.x = x;
    }

    public void setY(int y) {
        this.rect.y = y;
    }

    public float getX() {
        return rect.x;
    }

    public float getY() {
        return rect.y;
    }


    public void getBtImage(URL url) {
        try {
            //get the ressource

            //getThe image url and put it in the variable idleImage
            idleImage = ImageIO.read(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean isOver() {
        return  m_cursor.Hitbox.x > Hitbox.x && m_cursor.Hitbox.x < Hitbox.x + getWidth() && m_cursor.Hitbox.y > Hitbox.y && m_cursor.Hitbox.y < Hitbox.y + getHeight();
    }

    public float getWidth() {
        return  Hitbox.width;
    }
    public float getHeight() {
        return Hitbox.height;
    }


    public boolean isPressed() {

        return isOver() && m_cursor.isClicked(1);
    }

    public boolean isDragging() {
        return isOver() && isPressed();
    }

    public void update() {
        Hitbox = rect.copy();

    }

    public void draw(Graphics2D g2) {
        if(animator == null){
            throw new NullPointerException("animator is null");
        }

        if(isOver() && changeShapeWhenHover) {
            rect = HooverRect;
        }
        else
            rect = NormalRect;

        if(Isboxed){
            g2.setColor(Color.BLACK);
            g2.drawRect((int)rect.x, (int)rect.y,(int) rect.width,(int) rect.height);
        }
        super.draw(g2);

    }
}
