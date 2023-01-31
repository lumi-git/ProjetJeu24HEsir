package CameraPackage.Annimations;

import BaseClass.Entity;
import BaseClass.TrackedEntity;
import CameraPackage.AnnimationState;
import CameraPackage.Camera;
import CameraPackage.CameraAnimBase;
import Utils.Vector2Float;
import main.GamePanel;

public class CameraFromAToB extends CameraAnimBase {

    TrackedEntity m_A;
    TrackedEntity m_B;
    float SpeedDx = 0.1f;
    float SpeedDy = 0.1f;

    Vector2Float tracker ;

    public CameraFromAToB( TrackedEntity A, TrackedEntity B) {
        super();
        m_A = A;
        m_B = B;
        Camera.SetTargettingState(false);
        tracker = new Vector2Float(Camera.position.getX(), Camera.position.getY());
    }


    @Override
    public void update() {
        super.update();

        tracker = tracker.add( new Vector2Float(m_A.rect.x,m_A.rect.y).sub(tracker).mul(0.01f));


        Camera.position = tracker;

        System.out.println("-----------------Camera--------------");
        System.out.println(Camera.position.getX());
        System.out.println(Camera.position.getY());
        System.out.println("---------------------Tracker-----------------");
        System.out.println(tracker.getX());
        System.out.println(tracker.getY());

        if (tracker.getX() > m_A.Tracked_Rect.x-100 && tracker.getX() < m_A.Tracked_Rect.x+100) {
            if (tracker.getY() > m_A.Tracked_Rect.y-100 && tracker.getY() < m_A.Tracked_Rect.y+100) {
                state = AnnimationState.End;
                Camera.target = m_A;
                Camera.SetTargettingState(true);
            }
        }


    }
}
