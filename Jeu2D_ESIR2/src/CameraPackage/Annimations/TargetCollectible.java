package CameraPackage.Annimations;

import BaseClass.Entity;
import BaseClass.TrackedEntity;
import CameraPackage.*;
import main.GamePanel;

public class TargetCollectible extends CameraAnimBase {

    int TimeWait = 0;
    int m_targettingTime = 0;
    TrackedEntity m_target;

    public TargetCollectible( int targettingTime, TrackedEntity target) {
        super();
        m_targettingTime = targettingTime;
        CameraAnimator.NewAnnimation(new ChainedCameraAnim( new ZoomIn( 0.008f, 2f), new ZoomOut( 0.01f, Camera.zoom.getX())));
        Camera.target = target;
        m_target = target;
    }

    public void update() {
        TimeWait++;

        if(TimeWait > m_targettingTime){
            state = AnnimationState.End;
            //CameraAnimator.NewAnnimation(new CameraFromAToB(gp, gp.getPlayer(), m_target));
            Camera.target = gp.getPlayer();
            TimeWait = 0;
        }

    }


}
