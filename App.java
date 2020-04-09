import javax.swing.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.*;

import shapes.*;
import shapes.Rectangle;

public class App implements MouseListener {
      private JPanel UI = new JPanel();
      private JPanel board = new JPanel();
      private JFrame frame = new JFrame();
      private JButton rectangle = new JButton();
      private JButton ellipse = new JButton();

      public JFrame generateFrame() {
            return this.frame;
      }

      public JPanel generateUI() throws IOException {
            this.rectangle.setIcon(new ImageIcon(ImageIO.read(new File("img/rectangle.png"))));
            this.ellipse.setIcon(new ImageIcon(ImageIO.read(new File("img/ellipse.png"))));

            this.rectangle.setBorderPainted(false);
            this.rectangle.setFocusPainted(false);
            this.rectangle.setContentAreaFilled(false);

            this.ellipse.setBorderPainted(false);
            this.ellipse.setFocusPainted(false);
            this.ellipse.setContentAreaFilled(false);

            this.UI.add(this.rectangle);
            this.UI.add(this.ellipse);

            return this.UI;
      }

      public JPanel generateBoard() {
            this.board.add(new Rectangle(100, 100, 50, 50));
            this.board.setFocusable(true);
            this.board.addMouseListener(this);
            return this.board;
      }

      public void mouseClicked(MouseEvent e){
            this.board.add(new Rectangle(50, 50, 50, 50));
            this.frame.revalidate();
            this.frame.repaint();
      }

      public void mouseExited(MouseEvent e){

      }

      public void mouseEntered(MouseEvent e){

      } 

      public void mouseReleased(MouseEvent e){

      }

      public void mousePressed(MouseEvent e){

      }

      public void mouseDragged(MouseEvent e){

      }

      public void MouseMoved(MouseEvent e){

      }

      public static void main(String[] args) throws IOException {
            App app = new App();
            JFrame frame = app.generateFrame();

            frame.getContentPane().add(app.generateBoard());
            frame.getContentPane().add(app.generateUI(), BorderLayout.SOUTH);

            frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
            frame.setSize(600, 600);
            frame.setVisible(true);
      }
}