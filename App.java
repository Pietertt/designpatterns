import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import java.io.*;
import javax.imageio.ImageIO;

import shapes.*;
import shapes.Rectangle;

class App extends JPanel implements MouseListener {
      public JPanel board = new JPanel();
      private JPanel UI = new JPanel();
      public JFrame frame = new JFrame();

      private JButton rectangle = new JButton();
      private JButton ellipse = new JButton();

      public JPanel init() throws IOException {

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

            // this.board.add(new Rectangle(100, 100, 100, 100));

            return this.UI;

      }
      
      public void mouseClicked(MouseEvent e){
            // this.frame.getContentPane().add(new Rectangle(100, 100, 100, 100));
            // this.frame.revalidate();
            // this.frame.repaint();
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

            app.frame.getContentPane().add(app.init(), BorderLayout.SOUTH);

            app.board.add(new Rectangle(100, 100, 100, 100));

            app.frame.getContentPane().add(app.board);

            app.frame.setDefaultCloseOperation(app.frame.EXIT_ON_CLOSE);
            app.frame.setSize(600, 600);
            app.frame.setVisible(true);
      }  
}
