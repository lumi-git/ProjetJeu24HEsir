package Menus;

import Annimation.Annimations.Anim_ButtonParam;
import Annimation.Annimations.Anim_ButtonPlay;
import Utils.*;
import Utils.Button;
import main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class MainMenu extends Menu{

    BufferedImage idleImage;


    Button butonPLay;
    Button butonParam;

    Font MenuFont;
    FontMetrics metrics;
    String Title;
    MyCursor m_cursor;
    //constructor
    public MainMenu(MyCursor cursor) {
        super(cursor);
        rect =  new Rect(0,0,gp.screenWidth - gp.maxScreenCol*gp.tileSize-20,gp.screenHeight-20);
        m_cursor = cursor;
        MenuFont = new Font("Arial",Font.BOLD,40);
        gp.setFont(MenuFont);
        metrics = gp.getFontMetrics(MenuFont);
        Title = "Game";
        butonPLay = new Button(new Rect((gp.screenWidth-128)/2, (gp.screenHeight-64)/2,128,64), cursor);
        butonPLay.ChangeShapeWhenHover(true);
        butonPLay.setAnimator(new Anim_ButtonPlay(butonPLay));

        butonParam = new Button(new Rect(10, 10,32,32), cursor);
        butonParam.ChangeShapeWhenHover(true);
        butonParam.setAnimator( new Anim_ButtonParam(butonParam));

    }

    public void update()
    {
        butonPLay.update();
        butonParam.update();
        if(butonPLay.isPressed())
        {
            gp.setState(StateEnum.Play);
        }

        if(butonParam.isPressed())
        {
            System.out.println("Param OMG");
        }
    }


    public void draw(Graphics2D g2) {
        g2.setColor(new Color(50,50,50,100));
        g2.fillRect(0,0,gp.screenWidth,gp.screenHeight);
        g2.setFont(MenuFont);

        g2.drawString(Title,(gp.screenWidth-metrics.stringWidth(Title))/2,200);
        BufferedImage image = m_Cursor.idleImage;
        // affiche le personnage avec l'image "image", avec les coordonnées x et y, et de taille tileSize (16x16) sans échelle, et 48x48 avec échelle)
        g2.drawImage(image, (int)m_Cursor.rect.x, (int)m_Cursor.rect.y, gp.tileSize, gp.tileSize, null);
        g2.setColor(Color.red);
        g2.drawRoundRect((int)rect.x,(int)rect.y,(int)rect.width,(int)rect.height,10,10);
        butonPLay.draw(g2);
        butonParam.draw(g2);

    }
}
