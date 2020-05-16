package visitor;

import java.awt.event.*;
import java.awt.*;
import java.awt.Cursor;
import javax.swing.*;

import shapes.*;
import shapes.Shape;
import commands.*;

public class resizeVisitor  {
      public void visit(Shape shape){

      }

      public void visit(Group group){
            
      }
      // public void visitRectangle(Rectangle rectangle){
      //       rectangle.addMouseListener(new MouseAdapter(){
      //             public void mouseReleased(MouseEvent e){
      //                   if (rectangle.resizing) {
      //                         Location location = new Location(rectangle.getX(), rectangle.getY(), rectangle.getWidth(), rectangle.getHeight());
      //                         Order drag = new ResizeShapeCommand(rectangle, location);
      //                         rectangle.invoker.execute(drag);
      //                         rectangle.resizing = false;
      //                   }
      //             }
      //       });

      //       rectangle.addMouseMotionListener(new MouseAdapter(){
      //             public void mouseDragged(MouseEvent e){
      //                   if (rectangle.start != null) {
      //                         if(rectangle.cursor == Cursor.SE_RESIZE_CURSOR){
      //                               rectangle.resizing = true;
      //                               rectangle.dragging = false;
      
      //                               rectangle.setBounds(rectangle.getX(), rectangle.getY(), rectangle.getWidth() + e.getX() - rectangle.start.x, rectangle.getHeight() + e.getY() - rectangle.start.y);
      //                               rectangle.start = e.getPoint();
      //                               rectangle.width = rectangle.getWidth();
      //                               rectangle.height = rectangle.getHeight();
      //                               rectangle.repaint();
      //                         }
      //                   }
      //             }
      //       });   
      // }

      // public void visitEllipse(Ellipse ellipse){
      //       ellipse.addMouseListener(new MouseAdapter(){
      //             public void mouseReleased(MouseEvent e){
      //                   if(ellipse.resizing){
      //                         Location location = new Location(ellipse.getX(), ellipse.getY(), ellipse.getWidth(), ellipse.getHeight());
      //                         Order drag = new ResizeShapeCommand(ellipse, location);
      //                         ellipse.invoker.execute(drag);
      //                         ellipse.resizing = false;
      //                   }
      //             }
      //       });

      //       ellipse.addMouseMotionListener(new MouseAdapter(){
      //             public void mouseDragged(MouseEvent e){
      //                   if (ellipse.start != null) {
      //                         if(ellipse.cursor == Cursor.SE_RESIZE_CURSOR){
      //                               ellipse.resizing = true;
      //                               ellipse.dragging = false;
      
      //                               ellipse.setBounds(ellipse.getX(), ellipse.getY(), ellipse.getWidth() + e.getX() - ellipse.start.x, ellipse.getHeight() + e.getY() - ellipse.start.y);
      //                               ellipse.start = e.getPoint();
      //                               ellipse.width = ellipse.getWidth();
      //                               ellipse.height = ellipse.getHeight();
      //                               ellipse.repaint();
      //                         }
      //                   }
      //             }
      //       });   
      // }
}