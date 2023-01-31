package RessourceLibrary;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class ImageLibrary {
    static public BufferedImage Player;
    static public BufferedImage Enemi1;
    static public BufferedImage FlyingEnnemi;
    static public BufferedImage Sword;
    static public BufferedImage AutoShuriken;
    static public BufferedImage Arrow;
    static public BufferedImage Shuriken;
    static public BufferedImage Bow;
    static public BufferedImage HUD_health_void;
    static public BufferedImage HUD_health_filled;
    static public BufferedImage Colectible_Life;
    static public BufferedImage Player_Invincible;
    static public BufferedImage TileGround;
    static public BufferedImage TileWall;
    static public BufferedImage TileWater;
    static public BufferedImage TileLava;
    static public BufferedImage TileSand;
    static public BufferedImage TileSnow;
    static public BufferedImage Button_Play;
    static public BufferedImage Button_Main;
    static public BufferedImage Button_Param;


    public void loadImages(){
        Player = getImage(getClass().getResource("/IMG/player/personnage.png"));
        Enemi1 = getImage(getClass().getResource("/IMG/Mob/enemi1.png"));
        FlyingEnnemi = getImage(getClass().getResource("/IMG/Mob/enemi1.png"));
        Sword = getImage(getClass().getResource("/IMG/Weapons/Sword.png"));
        AutoShuriken = getImage(getClass().getResource("/IMG/Projectile/Shuriken.png"));
        Arrow = getImage(getClass().getResource("/IMG/Projectile/Arrow.png"));
        Shuriken = getImage(getClass().getResource("/IMG/Projectile/Shuriken.png"));
        Bow = getImage(getClass().getResource("/IMG/Weapons/Bow.png"));
        HUD_health_void = getImage(getClass().getResource("/IMG/HUD/health_bar_1.png"));
        HUD_health_filled = getImage(getClass().getResource("/IMG/HUD/health_bar_0.png"));
        Colectible_Life = getImage(getClass().getResource("/IMG/Collectible/HearthCollectible.png"));
        Player_Invincible = getImage(getClass().getResource("/IMG/Player/personnage_invincible.png"));
        TileGround = getImage(getClass().getResource("/IMG/assets/sprite/Room/tiles_ground_0.png"));
        TileWall = getImage(getClass().getResource("/IMG/assets/sprite/Room/tiles_wall_0.png"));
        TileWater = getImage(getClass().getResource("/IMG/tiles/WATER.png"));
        TileLava = getImage(getClass().getResource("/IMG/tiles/LAVA.png"));
        TileSand = getImage(getClass().getResource("/IMG/tiles/SAND.png"));
        TileSnow = getImage(getClass().getResource("/IMG/tiles/SNOW.png"));
        Button_Main = getImage(getClass().getResource("/IMG/Boutons/MainMenuButton.png"));
        Button_Play = getImage(getClass().getResource("/IMG/Boutons/PlayButton_hd.png"));
        Button_Param = getImage(getClass().getResource("/IMG/Boutons/ParamButton.png"));
    }

    public static BufferedImage getImage(URL url) {
        BufferedImage image = null;
        try {
            image = ImageIO.read(url);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;

    }

}

//enumeration of the differents images of the game





