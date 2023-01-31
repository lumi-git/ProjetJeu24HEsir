package BaseClass;

import Annimation.Annimations.AnimBase;
import BaseClass.Entity;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

//possible to invok the draw methode with this class
public class PrintableEntity extends Entity implements Animable {


    protected AnimBase animator;
    public PrintableEntity() {
        super();

    }


    public void draw(Graphics2D g2) {
        if (animator != null) {
            g2.drawImage(animator.getSprite(), (int) rect.x, (int) rect.y, (int) rect.width, (int) rect.height,null);
        }
    }
}
