import javax.swing.SwingUtilities;

import shapes.Rectangle;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseMotionAdapter;

public class App {

      private void init(){
            JFrame frame = new JFrame();
            frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
            frame.add(new Rectangle(50, 50, 20, 20));
            frame.pack();
            frame.setVisible(true);
      }
      public static void main(String[] args) {
            SwingUtilities.invokeLater(new Runnable(){
                  public void run(){
                        App app = new App();
                        app.init();
                  }
            });
      }
}