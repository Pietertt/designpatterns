import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import java.io.*;
import javax.imageio.ImageIO;

import shapes.*;
import shapes.Rectangle;

class App extends JFrame implements MouseListener {
      private JPanel board = new JPanel();
      private JPanel UI = new JPanel();

      private JButton rectangle = new JButton();
      private JButton ellipse = new JButton();

      public App() throws IOException {

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

            

            getContentPane().add(this.board);
            getContentPane().add(this.UI, BorderLayout.SOUTH);

            setDefaultCloseOperation(EXIT_ON_CLOSE);
            setSize(600, 600);
            setVisible(true);

            board.setFocusable(true);
            board.addMouseListener(this);
      }

      
      public void mouseClicked(MouseEvent e){
            System.out.println("Click");
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
      }  
}