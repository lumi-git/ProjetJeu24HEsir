package CameraPackage.Annimations;

import CameraPackage.AnnimationState;
import CameraPackage.Camera;
import CameraPackage.CameraAnimBase;
import main.GamePanel;

public class ZoomOut extends CameraAnimBase {
    private float zoomOutLimit = 1f;
    private float zoomOutSpeed = 0.001f;

    public ZoomOut(float speed,float limit) {
        super();
        zoomOutLimit = limit;
        zoomOutSpeed = speed;
    }

    @Override
    public void update() {
        super.update();
        zoomOut();
        if (Camera.zoom.getX() <= zoomOutLimit) {
            Camera.zoom.setX(zoomOutLimit);
            Camera.zoom.setY(zoomOutLimit);
            state = AnnimationState.End;
        }

    }


    public void zoomOut(){

        Camera.zoom.setX(Camera.zoom.getX()-zoomOutSpeed);
        Camera.zoom.setY(Camera.zoom.getY()-zoomOutSpeed);

    }



}
