import javax.swing.*;
import java.awt.*;

import shapes.*;
import shapes.Rectangle;

class App extends JPanel {

      public void paint(Graphics g){
            
      }

      public static void main(String[] args) {
            JFrame frame = new JFrame();
            Rectangle rect = new Rectangle(100, 100, 100, 100);
            frame.add(rect);

            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(600, 600);
            frame.setVisible(true);
      }     
}