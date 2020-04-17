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
                  public void mouseClicked(MouseEvent e){
                      select(e.getX(), e.getY());
                  }
            });
      
            addMouseMotionListener(new MouseAdapter(){
                  public void mouseDragged(MouseEvent e){
                      drag(e.getX(),e.getY());
                  }
            });
      }

      private void select(int x, int y){
            System.out.println("Got it");
            for(int i = 0; i < this.shapes.size(); i++){
                  Shape shape = this.shapes.get(i);
                  shape.deselect();
                  repaint();

                  if (shape.getIfSelected(x, y)) {
                        shape.select();
                        repaint();
                  }
            }
      }

      private void drag(int x, int y){
            for(int i = 0; i < this.shapes.size(); i++){
                  Shape shape = this.shapes.get(i);
                  shape.deselect();
                  repaint();
      
                  if (shape.getIfSelected(x, y)) {
                        shape.select();
                        shape.x = (x - shape.width / 2);
                        shape.y = (y - shape.height / 2);;
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