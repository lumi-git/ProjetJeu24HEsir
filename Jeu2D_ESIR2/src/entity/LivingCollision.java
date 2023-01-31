package entity;

import BaseClass.GameObject;
import BaseClass.LivingEntity;
import Utils.Rect;
import Utils.pair;
import main.GamePanel;

import java.util.ArrayList;

public class LivingCollision {
    ArrayList<LivingEntity> m_array_entity ;
    int rad;
    GamePanel m_gp;

    public LivingCollision( ArrayList<LivingEntity> array_entity){
        m_array_entity = array_entity;
        m_gp = GamePanel.getInstance();
    }

    public pair<LivingEntity,Boolean> Iscolling(Rect rect){

        for(LivingEntity o2 : m_array_entity){
            if(rect.intersects(o2.Hitbox)){
                return new pair<LivingEntity,Boolean>(o2,true);
            }
        }
        return new pair<LivingEntity,Boolean>(new LivingEntity(false),false);
    }

}
