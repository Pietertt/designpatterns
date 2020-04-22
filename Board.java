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

public class Board extends JPanel implements MouseListener {
      public JFrame frame;
      public Invoker invoker = new Invoker();

      public ArrayList<Shape> shapes = new ArrayList<Shape>();

      public Board(JFrame frame){
            this.frame = frame;

            addMouseListener(this);
            super.setFocusable(true);
            
            for(int i = 0; i < 5; i++){
                  Rectangle rect = new Rectangle(50 + i * 75, 200, 50, 50);
                  frame.add(rect);
                  shapes.add(rect);

                  frame.revalidate();
                  frame.repaint();
            }
      }

      public void mouseClicked(MouseEvent e){
            for(Shape shape : this.shapes){
                  if(shape.getIfSelected(e.getX(), e.getY())){
                        SelectShapeCommand select = new SelectShapeCommand(shape);
                        this.invoker.execute(select);
                  }

                  if(!shape.getIfSelected(e.getX(), e.getY())){
                        if(shape.selected){
                              DeselectShapeCommand deselect = new DeselectShapeCommand(shape);
                              this.invoker.execute(deselect);
                        }
                  }
            }
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

      public void paintComponent(Graphics g) {
            for(Shape shape : this.shapes){
                  shape.draw(g);
                  revalidate();
                  repaint();
            }
      }  
}