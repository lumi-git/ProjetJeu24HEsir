package BaseClass;

import Annimation.Annimations.AnimBase;
import BaseClass.PrintableEntity;
import BaseClass.Updateable;
import CameraPackage.Camera;
import Utils.Rect;
import Utils.Vector2Float;
import main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class TrackedEntity extends PrintableEntity implements Updateable {

    protected AnimBase animator;

    public BufferedImage idleImage;
    public Rect Tracked_Rect= new Rect(0,0,0,0);
    public Rect Tracked_Hitbox = new Rect(0,0,0,0);

    public TrackedEntity() {
        super();
    }

    @Override
    public void draw(Graphics2D g2) {
        if(Tracked_Rect.x+Tracked_Rect.width > 0 && Tracked_Rect.y+ Tracked_Rect.height>0 && Tracked_Rect.x < gp.screenWidth && Tracked_Rect.y < gp.screenHeight){
            idleImage = animator.getSprite();
            // affiche le personnage avec l'image "image", avec les coordonn�es x et y, et de taille tileSize (16x16) sans �chelle, et 48x48 avec �chelle)
            g2.drawImage(idleImage, (int)Tracked_Rect.x, (int)Tracked_Rect.y, (int)Tracked_Rect.width, (int)Tracked_Rect.height, null);
            if(gp.getConfig().showHitbox)
                g2.drawRect((int)Tracked_Hitbox.x,(int)Tracked_Hitbox.y,(int)Tracked_Hitbox.width,(int)Tracked_Hitbox.height);
        }
    }

    public void update(){
        Tracked_Rect = new Rect((rect.x* Camera.zoom.getX())-(int)Camera.position.getX(),rect.y*Camera.zoom.getY()-(int)Camera.position.getY()  ,rect.width*Camera.zoom.getX(),rect.height*Camera.zoom.getY());
        Tracked_Hitbox  = new Rect(Hitbox.x*Camera.zoom.getX()-(int)Camera.position.getX(),Hitbox.y*Camera.zoom.getY()-(int)Camera.position.getY(),Hitbox.width*Camera.zoom.getX(),Hitbox.height*Camera.zoom.getY());
    }

    public Vector2Float getTrackedPos(){
        return new Vector2Float(Tracked_Rect.x , Tracked_Rect.y );
    }

    //a function that floor a float with a given precision

    public float floor(float f, int precision){
        float p = (float)Math.pow(10, precision);
        return (float)Math.floor(f*p)/p;
    }



}
