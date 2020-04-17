import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;

import shapes.*;

public class Board extends JPanel {

      public ArrayList<Shape> shapes = new ArrayList<Shape>();

      public Board(){
            shapes.add(new Rectangle(100, 100, 100, 100));
            shapes.add(new Ellipse(300, 300, 100, 100));

            addMouseListener(new MouseAdapter(){
                  public void mousePressed(MouseEvent e){
                      moveSquare(e.getX(),e.getY());
                  }
            });
      
            addMouseMotionListener(new MouseAdapter(){
                  public void mouseDragged(MouseEvent e){
                      moveSquare(e.getX(),e.getY());
                  }
            });
      }

      private void moveSquare(int x, int y){
            for(int i = 0; i < this.shapes.size(); i++){

                  Shape shape = this.shapes.get(i);
      
                  if (shape.getIfSelected(x, y)) {
      
                  // The square is moving, repaint background 
                  // over the old square location. 
                  // repaint(CURR_X, CURR_Y, CURR_W+OFFSET, CURR_H+OFFSET);
      
                  // Update coordinates.
                  this.shapes.get(i).x = (x - this.shapes.get(i).width / 2);
                  this.shapes.get(i).y = (y - this.shapes.get(i).height / 2);;
      
                  // Repaint the square at the new location.
                  repaint();
                  }
            }
      }
    

      public Dimension getPreferredSize(){
            return new Dimension(600, 600);
      }

      public void paintComponent(Graphics g) {
            super.paintComponent(g);       
            for(Shape shape : this.shapes){
                  shape.draw(g);
            }
        }  
}