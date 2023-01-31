package tile;

import java.awt.Graphics2D;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import CameraPackage.CameraAnimator;

import Utils.Rect;
import Utils.StateEnum;
import BaseClass.Entity;
import Utils.Vector2Float;
import Utils.pair;
import entity.Collectible.Colectible_Life;
import entity.Enemis.Archer;
import entity.Enemis.EnemiExample;
import entity.Enemis.FlyingEnnemi;
import entity.Obstacle;
import main.GamePanel;
import tile.Tiles.TileGround;
import tile.Tiles.TileWall;
import tile.Tiles.TileWater;

public class RoomManager {
	GamePanel gp;
	char m_CurrentMap= 'A';
	Room m_CurrentRoom;
	Room m_LastRoom;

	boolean m_MemoRoomClear;

	Tile[] tile;
	int maxTiles = 10;
	int x=3,y=3;
	Tile mapTile[][];
	Tile LastMapTile[][];
	Floor m_floor;
	Random m_random;

	int LastDoorTrigger=0;
	int DoorWatchDog = 100;
	int AnnimationDx=0;
	int AnnimationDy=0;
	int AnnimationDirX=0;
	int AnnimationDirY=0;
	int AnnimationAccelX=1;
	int AnnimationAccelY=1;
	int AnnimationSpeed=1;
	
	public RoomManager( Floor floor) {
		this.gp = GamePanel.getInstance();

		m_random = new Random();
		mapTile = new Tile[gp.maxScreenCol][gp.maxScreenRow];
		LastMapTile= new Tile[gp.maxScreenCol][gp.maxScreenRow];
		tile = new Tile[maxTiles];
		m_MemoRoomClear = false;
		getTileInstance();
		m_floor = floor;
		renew();

	}

	//renew the components of the room manager
	public void renew(){
		x=3;
		y=3;
		m_MemoRoomClear = false;
		m_CurrentMap = m_floor.getFileForRoom(x,y);
		m_CurrentRoom = m_floor.getRoom(x,y);
		m_LastRoom = m_floor.getRoom(x,y);
		m_CurrentRoom.setCleared(true);
		loadTiles();
		loadEntity();
		loadTiles();

	}

	//load images from files
	public void getTileInstance() {
		// Charge les différentes tuiles dans le vecteur tile[]

		tile[0] = new TileGround(0,0);

		tile[1] = new TileWall(0,0);

		tile[2] = new TileGround(0,0);

		tile[3] = new TileGround(0,0);

		tile[4] = new TileGround(0,0);

		tile[5] = new TileGround(0,0);

	}

	public Tile getTile(int tile,float x_,float y_){
		switch (tile) {
			case 0:
				return new TileGround(x_,y_);
			case 1:
				return new TileWall(x_,y_);
			case 2:
				return new TileWater(x_,y_);


		}
		return new TileWall(0,0);
	}

	//does a door is trigered by the entity p ?
	public int IsDoorTrigger(Entity p){

		if((MapToTile((int)p.getCenterPos().getX()) ==8 || MapToTile((int)p.getCenterPos().getX()) == 7 )&& MapToTile((int)p.getCenterPos().getY()) == 1){
			if(m_floor.getFileForRoom(x,y-1) != '0'){
			LastDoorTrigger=1;
				AnnimationDirY=1;
			return 1;
			}
		}

		if((MapToTile((int)p.getCenterPos().getX()) ==8 || MapToTile((int)p.getCenterPos().getX()) == 7 )&& MapToTile((int)p.getCenterPos().getY()) == gp.maxScreenRow-2){
			if(m_floor.getFileForRoom(x,y+1) != '0'){
				LastDoorTrigger=2;
				AnnimationDirY=-1;
				return 2;
			}
		}

		if((MapToTile((int)p.getCenterPos().getX()) ==1 )&&( MapToTile((int)p.getCenterPos().getY()) == 6 || MapToTile((int)p.getCenterPos().getY()) == 5)  ){
			if(m_floor.getFileForRoom(x-1,y) != '0'){
				LastDoorTrigger=3;
				AnnimationDirX=1;
				return 3;
			}

		}

		if(( MapToTile((int)p.getCenterPos().getX()) == 14 )&&( MapToTile((int)p.getCenterPos().getY()) == 6 || MapToTile((int)p.getCenterPos().getY()) == 5))
		{
			if(m_floor.getFileForRoom(x+1,y) != '0'){
				LastDoorTrigger=4;
				AnnimationDirX=-1;
				return 4;
			}

		}
		LastDoorTrigger = 0;
		return 0;
	}

	//setter and getters for x,y
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}


	public void OpenRoom( ){
		TileManager.clearTiles();
		loadTiles();

	}

	//when a door is trigered, the GamePannel call this functiion to switch the room
	public void switchRoom(){

		//si y'a plus d'enemis, alors on mark comme visitée
		if(gp.getEnnemisTab().size() == 0){
			m_floor.markRoomAsVisited(x,y);
		}

		setPosInFloor();

		m_CurrentMap = m_floor.getFileForRoom(x,y);
		m_LastRoom = m_CurrentRoom;

		m_CurrentRoom = m_floor.getRoom(x,y);
		m_MemoRoomClear = m_CurrentRoom.isCleard();

		gp.clearEntity();
		gp.clearObstacles();
		gp.clearMob();
		TileManager.clearTiles();
		loadEntity();
		loadMobs();
		loadTiles();



		//System.out.println("LastDoorTrigger : "+LastDoorTrigger);

		//System.out.println("position in the floor : "+x+" "+y);


		if(LastDoorTrigger == 3 ){
			gp.getPlayer().rect.x += ((gp.maxScreenCol-2)*gp.tileSize) -DoorWatchDog; ;
		}
		else if (LastDoorTrigger == 4){
			gp.getPlayer().rect.x -= ((gp.maxScreenCol-2)*gp.tileSize)-DoorWatchDog; ;
		}
		else if (LastDoorTrigger == 1){
			gp.getPlayer().rect.y += ((gp.maxScreenRow-2)*gp.tileSize)-DoorWatchDog;
		}
		else if (LastDoorTrigger == 2){
			gp.getPlayer().rect.y -= ((gp.maxScreenRow-2)*gp.tileSize)-DoorWatchDog;
		}

		LastDoorTrigger = 0;
	}

	public void loadEntity() {

	}

	public void loadTiles(){
		int col = 0;
		int row = 0;
		int x = 0;
		int y = 0;
		while (col < gp.maxScreenCol && row < gp.maxScreenRow) {
			//int tileNum = LastTileNum[col][row];
			//int tileNumNext = mapTileNum[col][row];


			int  tileNum= m_CurrentRoom.getMap()[col][row];
			int tileNumNext = m_LastRoom.getMap()[col][row];

			if(m_CurrentRoom.isCleard()) tileNum = m_CurrentRoom.getMap()[col][row];
			else tileNum = m_CurrentRoom.getClosedMap()[col][row];

			//mapTile[col][row] = tile[tileNum].copy();
			mapTile[col][row] = getTile(tileNum,x,y);




			col ++;
			x += gp.tileSize;
			if (col == gp.maxScreenCol) {
				col = 0;
				row ++;
				x = 0;
				y += gp.tileSize;
			}
		}
	}

	public void loadMobs(){
		if( ! m_floor.roomIsVisited(x,y)){
			for (int i = 0; i < m_CurrentRoom.getEnemisNumber(); i++) {
				pair<Character, Vector2Float> entity = m_CurrentRoom.getEnnemis(i);
				if(entity.getFirst() == '1'){
					new FlyingEnnemi( gp.getPlayer(), entity.getSecond().getX(), entity.getSecond().getY());
				}
				else if (entity.getFirst() == '2'){
					new Archer( gp.getPlayer(), entity.getSecond().getX(), entity.getSecond().getY());
				}
				else if(entity.getFirst() == '3'){
					new EnemiExample( gp.getPlayer(), entity.getSecond().getX(), entity.getSecond().getY());
				}
			}
		}

	}


	public boolean GetCurrentRoomIsCleared(){
		return m_CurrentRoom.isCleard();
	}


	public void setPosInFloor(){
		if( LastDoorTrigger ==1)
			y--;
		else if( LastDoorTrigger ==2)
			y++;
		else if( LastDoorTrigger ==3)
			x--;
		else if( LastDoorTrigger ==4)
			x++;
	}


	public int MapToTile(int v) {

		return 1000*(int) v / (gp.tileSize*1000);

	}

	//transition between rooms smoothly with acceleration etc.
	public void UpdateTransition(){
		AnnimationAccelX+=AnnimationSpeed;
		AnnimationAccelY+=AnnimationSpeed;
		AnnimationDx+=AnnimationDirX*AnnimationAccelX;
		AnnimationDy+=AnnimationDirY*AnnimationAccelX;
		if(AnnimationDx >= gp.screenWidth || AnnimationDx <= -gp.screenWidth || AnnimationDy >= gp.screenHeight || AnnimationDy <= -gp.screenHeight){
			gp.setState(StateEnum.Play);
			AnnimationDx = 0;
			AnnimationDy = 0;
			AnnimationDirX =0;
			AnnimationDirY=0;
			AnnimationAccelY=0;
			AnnimationAccelX=0;
		}
	}

	//permite to verify if the room is cleared (it's a low trigger on the enemy tab of the game panel)
	public void update(){
		//verifie si la room est clear et declanche une selle fois la condition
		boolean comp =gp.getEnnemisTab().size() == 0;
		if( comp != m_MemoRoomClear  && comp ){

			m_CurrentRoom.setCleared(true);
			OpenRoom();
			//si la salle est finie, un nouveau coeur spawn

			new Colectible_Life(new Rect((m_random.nextInt(gp.maxScreenCol-2)+1)*gp.tileSize,(m_random.nextInt(gp.maxScreenRow-2)+1)*gp.tileSize,32,32),false);
		}
		m_MemoRoomClear=comp;

	}

	//permite to draw the smooth transition beteen rooms
	public void DrawTransition(Graphics2D g2) {
		int col = 0;
		int row = 0;
		int x = 0;
		int y = 0;
/*
		while (col < gp.maxScreenCol && row < gp.maxScreenRow) {

			mapTile[col][row].getRect().x = x+AnnimationDx;
			mapTile[col][row].getRect().y = y+AnnimationDy;
			mapTile[col][row].draw(g2);

			LastMapTile[col][row].getRect().x = x+AnnimationDx-(gp.maxScreenCol*gp.tileSize)*AnnimationDirX;
			LastMapTile[col][row].getRect().y = y+AnnimationDy-(gp.maxScreenRow*gp.tileSize)*AnnimationDirY;
			LastMapTile[col][row].draw(g2);


			//g2.drawImage(tile[tileNum].image, x+AnnimationDx, y+AnnimationDy, gp.tileSize, gp.tileSize, null);
			//g2.drawImage(tile[tileNumNext].image, x+AnnimationDx-(gp.maxScreenCol*gp.tileSize)*AnnimationDirX, y+AnnimationDy-(gp.maxScreenRow*gp.tileSize)*AnnimationDirY, gp.tileSize, gp.tileSize, null);
			col ++;
			x += gp.tileSize;
			if (col == gp.maxScreenCol) {
				col = 0;
				row ++;
				x = 0;
				y += gp.tileSize;
			}
		}*/
	}


}
