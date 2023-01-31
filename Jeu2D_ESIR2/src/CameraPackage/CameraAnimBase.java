package CameraPackage;

import main.GamePanel;

//enum of two states of the camera



public class CameraAnimBase {
    protected GamePanel gp;
    public AnnimationState state;

    public CameraAnimBase(){
        gp = GamePanel.getInstance();
        state = AnnimationState.Running;

    }

    public void update() {
        //do nothing
    }

}
