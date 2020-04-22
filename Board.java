import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;

import shapes.*;
import commands.Order;
import commands.DeselectShapeCommand;
import commands.SelectShapeCommand;
import commands.DragShapeCommand;
import commands.PlaceShapeCommand;

public class Board extends JPanel implements MouseListener, MouseMotionListener {
      private JFrame frame;

      public Invoker invoker = new Invoker();

      public ArrayList<Shape> shapes = new ArrayList<Shape>();

      public Board(JFrame frame){
            super.setFocusable(true);
            addMouseListener(this);
            this.frame = frame;
            // shapes.add(new Rectangle(100, 100, 100, 100));
            // shapes.add(new Ellipse(300, 300, 100, 100));
            // shapes.add(new Ellipse(500, 500, 100, 100));

            this.shapes.add(new Rectangle(100, 100, 100, 100));
            this.shapes.add(new Ellipse(300, 300, 100, 100));
            // this.frame.revalidate();
            // this.frame.repaint();
      }

      public void mouseClicked(MouseEvent e){
            
      }

      public void mouseExited(MouseEvent e){

      }

      public void mouseEntered(MouseEvent e){

      } 

      public void mouseReleased(MouseEvent e){

      }

      public void mousePressed(MouseEvent e){
            for(int i = 0; i < this.shapes.size(); i++){
                  Shape shape = this.shapes.get(i);

                  if (shape.getIfSelected(e.getX(), e.getY())) {
                        Order select = new SelectShapeCommand(shape);
                        this.invoker.execute(select);
                        repaint();

                        if(shape.selected){
                              Order drag = new DragShapeCommand(shape);
                              this.invoker.execute(drag);
                        }
                        
                  } else if(shape.selected){
                        Order deselect = new DeselectShapeCommand(shape);
                        this.invoker.execute(deselect);
                        repaint();
                  }
            }
      }

      @Override
      public void mouseDragged(MouseEvent e){
            
      }

      @Override
      public void mouseMoved(MouseEvent e){

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