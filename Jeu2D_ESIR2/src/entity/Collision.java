package entity;
import BaseClass.GameObject;
import Utils.Rect;
import Utils.pair;
import main.GamePanel;

import java.util.ArrayList;

public class Collision {
	ArrayList<GameObject> m_array_entity ;
	int rad;
	GamePanel m_gp;
	
	public Collision( ArrayList<GameObject> array_entity){
		m_array_entity = array_entity;
		m_gp = GamePanel.getInstance();
	}
	
	public pair<GameObject,Boolean> Iscolling(Rect rect){

		for(GameObject o2 : m_array_entity){
			if(rect.intersects(o2.Hitbox)){
				return new pair<GameObject,Boolean>(o2,true);
			}
		}
		return new pair<GameObject,Boolean>(new GameObject(false),false);
	}

}
