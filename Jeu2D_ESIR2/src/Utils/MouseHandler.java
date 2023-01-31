package Utils;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseHandler extends MouseAdapter {
    private boolean dragging = false;
    private boolean pressedButton[] = new boolean[3];
    @Override
    public void mousePressed(MouseEvent e) {
        pressedButton[e.getButton()-1] = true;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        pressedButton[e.getButton()-1] = false;
        String text = "";
        int button = e.getButton();
        if (button == MouseEvent.BUTTON1) {
            text = "Button 1";
        } else if (button == MouseEvent.BUTTON2) {
            text = "Button 2";
        } else if (button == MouseEvent.BUTTON3) {
            text = "Button 3";
        }
        dragging = false;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        dragging = true;
    }

    public boolean isPressed(int button) {
        return pressedButton[button-1];
    }

}