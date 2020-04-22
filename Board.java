import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

      public ArrayList<Rectangle> shapes = new ArrayList<Rectangle>();

      public Board(JFrame frame){
            super.setFocusable(true);
            addMouseListener(this);
            this.frame = frame;

            // Rectangle rectangle = new Rectangle(100, 100, 100, 100);
            // Shape ellipse = new Ellipse(400, 300, 100, 100);

            // this.frame.add(rectangle);

            // this.frame.revalidate();
            // this.frame.repaint();
            // this.frame.pack();
      }

      public void mouseClicked(MouseEvent e){
            Rectangle rect = new Rectangle(e.getX(), e.getY(), 100, 100);

            frame.add(rect);
            shapes.add(rect);
            System.out.println(shapes.size());
            frame.revalidate();
            repaint();
      }

      public void mouseExited(MouseEvent e){

      }

      public void mouseEntered(MouseEvent e){

      } 

      public void mouseReleased(MouseEvent e){

      }

      public void mousePressed(MouseEvent e){
            // for(int i = 0; i < this.shapes.size(); i++){
            //       Shape shape = this.shapes.get(i);

            //       if (shape.getIfSelected(e.getX(), e.getY())) {
            //             Order select = new SelectShapeCommand(shape);
            //             this.invoker.execute(select);
            //             repaint();

            //             if(shape.selected){
            //                   Order drag = new DragShapeCommand(shape);
            //                   this.invoker.execute(drag);
            //             }
                        
            //       } else if(shape.selected){
            //             Order deselect = new DeselectShapeCommand(shape);
            //             this.invoker.execute(deselect);
            //             repaint();
            //       }
            // }
      }

      @Override
      public void mouseDragged(MouseEvent e){
            
      }

      @Override
      public void mouseMoved(MouseEvent e){

      }    

      public void paintComponent(Graphics g) {
            for(Rectangle shape : this.shapes){
                  shape.draw(g);
                  revalidate();
                  repaint();
            }
      }  
}