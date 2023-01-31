package CameraPackage.Annimations;

import main.GamePanel;

public class ZoomInOut extends ChainedCameraAnim {


    public ZoomInOut() {
        super( new ZoomIn( 0.005f, 1.5f), new ZoomOut( 0.01f, 1.2f));

    }

    @Override
    public void update() {
        super.update();
    }

}