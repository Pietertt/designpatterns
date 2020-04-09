import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import shapes.*;
import shapes.Rectangle;

class App extends JFrame implements MouseListener {
      private JPanel board = new JPanel();
      private JPanel UI = new JPanel();

      public App(){
            Rectangle rect = new Rectangle(100, 200, 100, 100);

            add(rect);

            setDefaultCloseOperation(EXIT_ON_CLOSE);
            setSize(600, 600);
            setVisible(true);
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

      public static void main(String[] args) {
            App app = new App();
      }  
}