package tile;


import Utils.Vector2Float;
import Utils.pair;

import java.util.ArrayList;

public class Room {


    //temporari, may be removed
    //allow us to create aenemi at a certain position with a character
    ArrayList<pair<Character, Vector2Float>> m_ennnemis;


    int[][] m_map;
    int[][] m_closedMap;

    int m_EnnemisNumber;

    //does the player cleared the room (no enemy remaining)
    boolean RoomCleared = false;

    Room(int length,int width,int EnnemisNumber) {
        m_ennnemis = new ArrayList<>();
        m_EnnemisNumber = EnnemisNumber;

        m_map = new int[length][width];
    }

    public boolean isCleard() {
        return RoomCleared;
    }

    public void setCleared(boolean b){
        RoomCleared = b;
    }

    public void addEntity(char entityNum,float x,float y) {
        m_ennnemis.add(new pair<>(entityNum,new Vector2Float(x,y)));
    }

    public ArrayList<pair<Character, Vector2Float>> getEntities() {
        return m_ennnemis;
    }

    public pair<Character, Vector2Float> getEnnemis(int i) {
        return m_ennnemis.get(i);
    }


    public int[][] getMap() {
        return m_map;
    }

    public int[][] getClosedMap(){
        return m_closedMap;
    }

    public void setMap(int[][] map) {
        this.m_map = map;
    }

    public void setClosedMap(int[][] map) {
        this.m_closedMap = map;
    }

    public int getEnemisNumber() {
        return m_EnnemisNumber;
    }
}
