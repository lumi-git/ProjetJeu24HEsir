package CameraPackage;

import BaseClass.Entity;
import BaseClass.TrackedEntity;
import Utils.Rect;
import Utils.Vector2Float;
import entity.Players.Player;
import main.GamePanel;

public class Camera {
    public static Vector2Float position;

    public static Vector2Float zoom;

    public static float Rotation=90;

    public static GamePanel gp;
    public static Entity target;

    //tmp
    static boolean m_TargettingState = true;

    Player player;

    public Camera(GamePanel gp_, Entity target_){
        position =  new Vector2Float(0,0);
        zoom = new Vector2Float((float)1.2,(float)1.2);
        gp = gp_;
        player = gp.getPlayer();
        target =  target_;
    }

    public static  void  SetTargettingState(boolean state){
        m_TargettingState = state;
    }

    public static Vector2Float getCentredPosition() {
        return new Vector2Float(position.getX() +gp.screenWidth/2, position.getY() +gp.screenHeight/2);
    }


    public void update() {

        if(m_TargettingState){
            position.setX( position.getX()+ ( (target.rect.x*Camera.zoom.getX()-(float)(gp.getWidth())/2) - position.getX())*0.02f);
            position.setY( position.getY()+ ( (target.rect.y*Camera.zoom.getY()-(float)(gp.getHeight())/2) - position.getY())*0.02f);
        }

        if(position.getX()<0)
            position.setX(0);

        if(position.getY()<0)
            position.setY(0);

        if(position.getX()>gp.getWidth()*Camera.zoom.getX()-gp.getWidth())
            position.setX(gp.getWidth()*Camera.zoom.getX()-gp.getWidth());

        if(position.getY()>gp.getHeight()*Camera.zoom.getY()-gp.getHeight())
            position.setY(gp.getHeight()*Camera.zoom.getY()-gp.getHeight());
    }
}
