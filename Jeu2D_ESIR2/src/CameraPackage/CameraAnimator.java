package CameraPackage;

import main.GamePanel;

import java.util.ArrayList;

public class CameraAnimator {

    GamePanel gp;
    ArrayList<CameraAnimBase> cameraAnims;
    static ArrayList<CameraAnimBase> cameraAnimsTMP;


    public CameraAnimator(){
        gp = GamePanel.getInstance();
        cameraAnims = new ArrayList<CameraAnimBase>();
        cameraAnimsTMP = new ArrayList<CameraAnimBase>();
        //cameraAnims.add(new ZoomInOut(gp_));
    }

    public static void QueuAnnimation(CameraAnimBase cameraAnim_){
        cameraAnimsTMP.add(cameraAnim_);
    }

    public static void NewAnnimation(CameraAnimBase cameraAnim_){
        cameraAnimsTMP.add(cameraAnim_);
    }

    public void update(){

        if(cameraAnimsTMP.size() > 0){
            cameraAnims.addAll(cameraAnimsTMP);
            cameraAnimsTMP.clear();
        }
        for(CameraAnimBase cameraAnim : cameraAnims){
            cameraAnim.update();
        }
        cameraAnims.removeIf(cameraAnim -> cameraAnim.state == AnnimationState.End);



    }

}
