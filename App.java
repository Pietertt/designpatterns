import javax.swing.*;
import java.awt.*;

import shapes.Shape;

class App extends JPanel {

      public void paint(Graphics g) {
            int[] BLUE = {0, 0, 0};
            Shape rect = new Shape(100, 100, 100, 100, BLUE);
            rect.place();
      }

      public static void main(String[] args) {
            JFrame frame = new JFrame();
            frame.add(new App());

            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(600, 600);
            frame.setVisible(true);
      }     
}