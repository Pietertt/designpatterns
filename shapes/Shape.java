package shapes;

import javax.swing.*;
import java.awt.*;

public class Shape extends JPanel {
    public int x;
    public int y;
    public int[] color;

    public Shape(int x, int y, int[] color){
        this.x = x;
        this.y = y;
        this.color = color;
    }

    protected void paintComponent(Graphics g) {

    }
}
