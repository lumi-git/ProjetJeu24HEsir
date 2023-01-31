package CameraPackage.Annimations;

import CameraPackage.AnnimationState;
import CameraPackage.CameraAnimBase;
import main.GamePanel;

public class ChainedCameraAnim extends CameraAnimBase {


    CameraAnimBase First = new ZoomIn(0.005f,1.5f);
    CameraAnimBase Second = new ZoomOut(0.01f,1.2f);

    public ChainedCameraAnim(CameraAnimBase First_,CameraAnimBase Second_) {
        super();
        First = First_;
        Second = Second_;
    }


    @Override
    public void update() {
        super.update();
        if(First.state == AnnimationState.Running){
            First.update();
        }

        if(First.state == AnnimationState.End){
            Second.update();
        }

        if(Second.state == AnnimationState.End){
            state = AnnimationState.End;


        }
    }


}
