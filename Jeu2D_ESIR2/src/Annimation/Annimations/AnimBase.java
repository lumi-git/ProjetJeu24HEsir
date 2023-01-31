package Annimation.Annimations;

import BaseClass.PrintableEntity;
import main.GamePanel;

import java.awt.image.BufferedImage;

public class AnimBase {
    GamePanel gp;

    public AnimBase() {
        gp = GamePanel.getInstance();
    }

    //code this in the upper layer classes to get the sprite based on the state of the entity
    public BufferedImage getSprite() {
        return null;
    }
}