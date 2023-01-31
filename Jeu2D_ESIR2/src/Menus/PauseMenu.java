package Menus;

import Annimation.Annimations.Anim_ButtonPlay;
import Utils.*;
import Utils.Button;
import main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class PauseMenu extends Menu {

    BufferedImage idleImage;


    Button butonPLay;



    Cursor defaultCursor = new Cursor(Cursor.DEFAULT_CURSOR);
    Cursor hooverCursor = new Cursor(Cursor.HAND_CURSOR);


    MyCursor m_cursor;
    //constructor
    public PauseMenu(  MyCursor cursor) {
        super( cursor);
        rect =  new Rect(0,0,gp.screenWidth - gp.maxScreenCol*gp.tileSize-20,gp.screenHeight-20);

        butonPLay = new Button(new Rect(gp.getWidth()/2, gp.getHeight()/2,50,50), cursor);
        butonPLay.setAnimator(new Anim_ButtonPlay(butonPLay));
        m_cursor = cursor;

    }
    public void update()
    {
        butonPLay.update();

        if(butonPLay.isPressed())
        {
            System.out.println("play");
            gp.setState(StateEnum.Play);
        }

    }


    public void draw(Graphics2D g2) {
        g2.drawString("main Menu woahhh",600,200);
        BufferedImage image = m_Cursor.idleImage;
        // affiche le personnage avec l'image "image", avec les coordonnées x et y, et de taille tileSize (16x16) sans échelle, et 48x48 avec échelle)
        g2.drawImage(image, (int) m_Cursor.rect.x, (int) m_Cursor.rect.y, gp.tileSize, gp.tileSize, null);
        g2.setColor(Color.red);
        g2.drawRoundRect((int) rect.x,(int) rect.y,(int) rect.width,(int) rect.height,10,10);

        butonPLay.draw(g2);

    }


}
