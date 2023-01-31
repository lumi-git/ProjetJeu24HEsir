package CameraPackage.Annimations;

import CameraPackage.AnnimationState;
import CameraPackage.Camera;
import CameraPackage.CameraAnimBase;
import main.GamePanel;

public class ZoomIn extends CameraAnimBase {
    private float zoomInLimit = 1.2f;
    private float zoomInSpeed = 0.001f;
    public ZoomIn(float speed,float limit) {
        super();
        zoomInLimit = limit;
        zoomInSpeed = speed;
    }

    @Override
    public void update() {
        super.update();
        zoomIn();
        if (Camera.zoom.getX() >= zoomInLimit) {
            Camera.zoom.setX(zoomInLimit);
            Camera.zoom.setY(zoomInLimit);
            state = AnnimationState.End;
        }
    }

    public void zoomIn(){
        Camera.zoom.setX(Camera.zoom.getX()+zoomInSpeed);
        Camera.zoom.setY(Camera.zoom.getY()+zoomInSpeed);
    }


}
