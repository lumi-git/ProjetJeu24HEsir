package main;

import CameraPackage.Camera;
import RessourceLibrary.ImageLibrary;
import RessourceLibrary.SoundLibrary;
import Utils.MouseHandler;

import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame window = new JFrame();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.setTitle("***THE GAME***");

		ImageLibrary imageLibrary = new ImageLibrary();
		SoundLibrary soundLibrary = new SoundLibrary();

		imageLibrary.loadImages();
		soundLibrary.loadSounds();

		//GamePanel gamePanel = new GamePanel(new MouseHandler());
		GamePanel.getInstance().init(new MouseHandler());
		Camera camera = new Camera(GamePanel.getInstance(),GamePanel.getInstance().player);
		//Camera camera = new Camera(gamePanel,gamePanel.player);

		GamePanel.getInstance().setCamera(camera);
		//gamePanel.setCamera(camera);

		window.add(GamePanel.getInstance());
		//window.add(gamePanel);
		window.pack();
		
		window.setLocationRelativeTo(null);
		window.setVisible(true);

		GamePanel.getInstance().startGameThread();


		//gamePanel.startGameThread();

	}

}
