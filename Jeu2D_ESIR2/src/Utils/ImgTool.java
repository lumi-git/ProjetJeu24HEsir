package Utils;

import java.awt.*;
import java.awt.image.BufferedImage;

//permite to rotate a buffered image
//used to rotate the image of weapons
public class ImgTool {
    public static BufferedImage rotateImage(BufferedImage imageToRotate,float angle) {
        int widthOfImage = imageToRotate.getWidth();
        int heightOfImage = imageToRotate.getHeight();
        int typeOfImage = imageToRotate.getType();

        BufferedImage newImageFromBuffer = new BufferedImage(widthOfImage, heightOfImage, typeOfImage);

        Graphics2D graphics2D = newImageFromBuffer.createGraphics();

        graphics2D.rotate(angle, widthOfImage / 2, heightOfImage / 2);
        graphics2D.drawImage(imageToRotate, null, 0, 0);

        return newImageFromBuffer;
    }
}
