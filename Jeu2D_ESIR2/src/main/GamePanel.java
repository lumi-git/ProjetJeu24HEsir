package main;

import java.awt.*;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

import BaseClass.GameObject;
import BaseClass.LivingEntity;
import CameraPackage.Camera;
import CameraPackage.CameraAnimator;
import Menus.EndScreen;
import Utils.*;
import Menus.MainMenu;
import Utils.Button;
import entity.*;
import entity.Players.Player;
import entity.UsableObject.Sword;
import tile.Floor;
import tile.RoomManager;
import tile.TileManager;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;


public class GamePanel extends JPanel implements Runnable{
	// create a boolean
	static GamePanel instance;

	public static GamePanel getInstance() {
		if(instance == null){
			instance = new GamePanel();

		}
		return instance;
	}


	private boolean running ;
	int originalTileSize ;
	int scale ;
	public int tileSize;
	public int maxScreenCol;
	public int maxScreenRow ;
	public int screenWidth ;
	public int screenHeight ;

	Config config ;
	//camera du jeu
	Camera camera ;

	int Frame;
	// FPS : taux de rafraichissement
	public int FPS ;
	// Création des différentes instances (Player, KeyHandler, TileManager, GameThread ...)
	KeyHandler keyH ;

	Thread gameThread;
	Collision ObCollision;
	Collision EnCollision;
	LivingCollision MobCollision;
	Player player ;

	public  ArrayList<GameObject> TabEntity ;
	public  ArrayList<GameObject> Obstacles;
	public  ArrayList<LivingEntity> ennemis ;
	public  ArrayList<GameObject> Collectible;
	public  ArrayList<GameObject> TMPTabEntity ;

	//in this order :
	TileManager tileM;
	Floor floor ;
	RoomManager RoomM ;

	MyCursor m_cursor ;
	MainMenu m_menuMain ;
	EndScreen m_menuDeath;

	CameraAnimator cameraAnimator ;

	StateEnum m_Mode = StateEnum.Main;


	double CurrentFps = 0;

	BufferedImage CursorImg;
	Hud hud ;

	private GamePanel() {
	}

	public void init(MouseHandler mouseH) {
		keyH = new KeyHandler();
		config = new Config();

		originalTileSize = 16; // une tuile de taille 16x16
		scale = 3; // échelle utilisée pour agrandir l'affichage
		tileSize = originalTileSize * scale; // 48x48
		maxScreenCol = 16;
		maxScreenRow = 12; // ces valeurs donnent une résolution 4:3
		screenWidth = tileSize * maxScreenCol; //768 pixels
		screenHeight = tileSize * maxScreenRow; //576 pixels
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
		this.setFocusable(true);
		addMouseListener(mouseH);
		addMouseMotionListener(mouseH);

		running = false;


		//Paramètres de l'écran

		FPS = 120;
		TabEntity =   new ArrayList<>();
		Obstacles =   new ArrayList<>();
		ennemis =   new ArrayList<>();
		Collectible =   new ArrayList<>();
		TMPTabEntity =   new ArrayList<>();

		cameraAnimator = new CameraAnimator();

		Frame = 0;
		ObCollision = new Collision(Obstacles);
		EnCollision = new Collision( TabEntity);
		MobCollision = new LivingCollision( ennemis);
		m_cursor = new MyCursor(false,mouseH);
		m_menuMain = new MainMenu(m_cursor);
		m_menuDeath = new EndScreen(m_cursor);
		player = new Player( keyH,ObCollision,EnCollision,MobCollision,m_cursor);
		player.TakeNewWeapon(new Sword(false,player));
		//pauseButton = new Button(new Rect(100, 100,50,50),m_cursor, this,getClass().getResource("/IMG/Boutons/PlayButton.png"));
		//new floor
		tileM = new TileManager();
		floor = new Floor(30,2,6);
		RoomM = new RoomManager( floor);
		hud = new Hud(m_cursor);
		ChangeCursor(getClass().getResource("/IMG/Cursor/Cursor.png"));
	}

	public void setCamera(Camera camera) {
		this.camera = camera;
	}


	public void ChangeCursor(URL url) {
		try {
			CursorImg = ImageIO.read(url);
			CursorImg.getScaledInstance(3, tileSize, Image.SCALE_SMOOTH);

		} catch (IOException e) {
			e.printStackTrace();
		}

		Cursor blankCursor = Toolkit.getDefaultToolkit().createCustomCursor( CursorImg, new Point(0, 0), "blank cursor");
		setCursor(blankCursor);
	}

	public void startGameThread() {
		gameThread = new Thread(this);
		gameThread.start();
	}

	public double getCurrentFps() {
		return CurrentFps;
	}

	public void initialize(){
		clearEntity();
		clearObstacles();
		clearMob();
		clearCollectible();
		player.setDefaultValues();
		floor.renew(30,3,6);
		RoomM.renew();
	}

	//get the obstacle object colision
	public Collision getObstacleCollision() {
		return ObCollision;
	}

	//get the entity object colision
	public Collision getEntityCollision() {
		return EnCollision;
	}

	public LivingCollision getMobCollision() {
		return MobCollision;
	}

	//get the enemis tab
	public ArrayList<LivingEntity> getEnnemisTab() {
		return ennemis;
	}


	public ArrayList<GameObject> getCollectibleTab() {
		return Collectible;
	}

	//use this to add an entity during a update of an other entity (avoid comodifications)
	public void addAsynchroneEntity(GameObject entity) {
		TMPTabEntity.add(entity);
	}

	public void addEntity(GameObject entity){
		TabEntity.add(entity);
	}

	public void NewObstacle(GameObject ob){
		Obstacles.add(ob);
	}

	public void NewMob(LivingEntity mob){
		ennemis.add(mob);
	}

	public void NewCollectible(GameObject collectible){
		Collectible.add(collectible);
	}

	public void removeEntity(GameObject entity){
		TabEntity.remove(entity);
	}

	public void clearMob(){
		ennemis.clear();
	}

	public void removeMob(GameObject mob){
		ennemis.remove(mob);
	}

	public void clearEntity(){
		System.out.println("clear entiti "+TabEntity.size());

		TabEntity.clear();
	}

	public void clearObstacles(){
		System.out.println("clear obstacles "+Obstacles.size());
		Obstacles.clear();
	}

	public void clearCollectible(){
		System.out.println("clear collectible "+Collectible.size());
		Collectible.clear();
	}

	public Config getConfig() {
		return config;
	}


	public void run() {
		double drawInterval = 1000000000/FPS; // rafraichissement chaque 0.0166666 secondes
		double nextDrawTime = System.nanoTime() + drawInterval; 
		
		while(gameThread != null) { //Tant que le thread du jeu est actif
			double now = System.nanoTime();
			DeleteDeathEntities();
			//Permet de mettre � jour les diff�rentes variables du jeu
			update();
			//Dessine sur l'�cran le personnage et la map avec les nouvelles informations. la m�thode "paintComponent" doit obligatoirement �tre appel�e avec "repaint()"
			repaint();
			//permet de detruire les entités inutiles

			//Calcule le temps de pause du thread

			
			try {
				double remainingTime = nextDrawTime - System.nanoTime();
				remainingTime = remainingTime/1000000;

				if(remainingTime < 0) {
					remainingTime = 0;
				}
				
				Thread.sleep((long)remainingTime);
				nextDrawTime += drawInterval;
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			CurrentFps = 1000000000/(System.nanoTime()-now);

		}
	}

	public void update() {

		Frame++;
		if(Frame > 100*FPS)
			Frame = 0;

		m_cursor.update();
		if(Objects.equals(m_Mode, StateEnum.Play)) {

			tileM.update();

			if(TMPTabEntity.size() !=0){
				TabEntity.addAll(TMPTabEntity);
				TMPTabEntity.clear();
			}

			for (GameObject entity : TabEntity) {
				entity.update();
			}

			for (GameObject mon : ennemis) {
				mon.update();
			}

			for (GameObject ob : Obstacles) {
				ob.update();
			}

			for (GameObject collect : Collectible) {
				collect.update();
			}


			if(player.getLife()<=0) {
				m_Mode = StateEnum.GameOver;
				initialize();
			}

			if (RoomM.GetCurrentRoomIsCleared() && RoomM.IsDoorTrigger(player) !=0) {
				System.out.println("A door has been triggered");
				RoomM.switchRoom();
				m_Mode = StateEnum.Translate;
			}
			player.update();
			camera.update();
			cameraAnimator.update();
			RoomM.update();
			hud.update();
		}

		else if (Objects.equals(m_Mode, StateEnum.Main)) {
			m_menuMain.update();
		}
		else if (Objects.equals(m_Mode, StateEnum.GameOver)){
			m_menuDeath.update();
		}
		else if (Objects.equals(m_Mode, StateEnum.Translate)){
			RoomM.UpdateTransition();
		}
	}

	public void DeleteDeathEntities(){
		TabEntity.removeIf(entity -> entity.state == States.DESTROY);
		Obstacles.removeIf(ob -> ob.state == States.DESTROY);
		ennemis.removeIf(mob -> mob.state == States.DESTROY);
		Collectible.removeIf(col -> col.state == States.DESTROY);

	}

	public void setState(StateEnum Mode) {
		m_Mode = Mode;
	}

	public StateEnum getState() {
		return m_Mode;
	}

	public void paintComponent(Graphics g) {

		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;

		if(Objects.equals(m_Mode, StateEnum.Play)){

			tileM.draw(g2);
			player.draw(g2);
			hud.draw(g2);

			for(int i = 0; i < TabEntity.size(); i++) {
				TabEntity.get(i).draw(g2);
			}

			for(int i = 0; i < ennemis.size(); i++) {
				ennemis.get(i).draw(g2);
			}

			for(int i = 0; i < Collectible.size(); i++) {
				Collectible.get(i).draw(g2);
			}
			g2.dispose();
		}
		else if (Objects.equals(m_Mode, StateEnum.Main)) {
			m_menuMain.draw(g2);
		}
		else if (Objects.equals(m_Mode, StateEnum.GameOver)){
			m_menuDeath.draw(g2);
		}
		else if (Objects.equals(m_Mode, StateEnum.Translate)){
			RoomM.DrawTransition(g2);
		}
	}

	public int getOriginalTileSize() {
		return originalTileSize;
	}
	
	public int GetFrame() {
		return Frame;
	}

	public Player getPlayer()
	{
		return player;
	}

}


//public void run() {
//double drawInterval = 1000000000/FPS;
//double delta = 0;
//long lastTime = System.nanoTime();
//long currentTime;
//long timer = 0;
//long drawCount = 0;
//
//while (gameThread != null) {
//	
//	currentTime = System.nanoTime();
//	
//	delta += (currentTime - lastTime) / drawInterval;
//	timer += (currentTime - lastTime);
//	lastTime = currentTime;
//	
//	if(delta >= 1) {
//		update();
//		repaint();
//		delta--;
//		drawCount++;
//	}
//		
//	if(timer >= 1000000000) {
//		System.out.println("FPS:" + drawCount);
//		drawCount = 0;
//		timer = 0;
//	}
//}
//}
