package Menus;

import Annimation.Annimations.Anim_ButtonMain;
import Annimation.Annimations.Anim_ButtonPlay;
import Utils.Button;
import Utils.MyCursor;
import Utils.Rect;
import Utils.StateEnum;
import main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class EndScreen extends Menu {
    BufferedImage idleImage;


    Button butonMain;


    Font MenuFont;
    FontMetrics metrics;
    String Title;
    MyCursor m_cursor;
    //constructor
    public EndScreen( MyCursor cursor) {
        super(cursor);

        rect =  new Rect(0,0,gp.screenWidth - gp.maxScreenCol*gp.tileSize-20,gp.screenHeight-20);
        m_cursor = cursor;
        MenuFont = new Font("Arial",Font.BOLD,40);
        gp.setFont(MenuFont);
        metrics = gp.getFontMetrics(MenuFont);
        Title = "You Died..";
        butonMain = new Button(new Rect((gp.screenWidth-128)/2, (gp.screenHeight-64)/2+120,128,64), cursor);
        butonMain.setAnimator(new Anim_ButtonMain(butonMain));
        butonMain.ChangeShapeWhenHover(true);
    }

    public void update()
    {
        butonMain.update();

        if(butonMain.isPressed())
        {
            gp.setState(StateEnum.Main);
        }
    }

    public void draw(Graphics2D g2) {
        g2.setFont(MenuFont);

        g2.drawString(Title,(gp.screenWidth-metrics.stringWidth(Title))/2,200);

        g2.setColor(Color.red);
        g2.drawRoundRect((int)rect.x,(int)rect.y,(int)rect.width,(int)rect.height,10,10);
        butonMain.draw(g2);

    }



}
