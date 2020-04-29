package visitor;

import java.awt.event.*;
import java.awt.*;
import java.awt.Cursor;
import javax.swing.*;

import shapes.*;
import shapes.Rectangle;

public class moveVisitor implements Visitor {

      public void visitRectangle(Rectangle rectangle){
            rectangle.addMouseListener(new MouseAdapter(){
                  // public void mouseDragged(MouseEvent e){
                  //       System.out.println(rectangle.cursor);
                  //       if (rectangle.start != null) {
                  //             int x = rectangle.getX();
                  //             int y = rectangle.getY();
                  //             int width = rectangle.getWidth();
                  //             int height = rectangle.getHeight();
                  //             int dx = e.getX() - rectangle.start.x;
                  //             int dy = e.getY() - rectangle.start.y;
            
                  //             switch (rectangle.cursor) {
                  //                   case Cursor.N_RESIZE_CURSOR:
                  //                         rectangle.resizing = true;
                  //                         rectangle.dragging = false;
            
                  //                         rectangle.setBounds(x, y + dy, width, height - dy);
                  //                         rectangle.width = width;
                  //                         rectangle.height = height;
                  //                         rectangle.repaint();
                  //                         break;
                  //                   case Cursor.S_RESIZE_CURSOR:
                  //                         rectangle.resizing = true;
                  //                         rectangle.dragging = false;
            
                  //                         rectangle.setBounds(x, y, width, height + dy);
                  //                         rectangle.start = e.getPoint();
                  //                         rectangle.width = width;
                  //                         rectangle.height = height;
                  //                         rectangle.repaint();
                  //                         break;
                  //                   case Cursor.W_RESIZE_CURSOR:
                  //                         rectangle.resizing = true;
                  //                         rectangle.dragging = false;
            
                  //                         rectangle.setBounds(x + dx, y, width - dx, height);
                  //                         rectangle.width = width;
                  //                         rectangle.height = height;
                  //                         rectangle.repaint();
                  //                         break;
                  //                   case Cursor.E_RESIZE_CURSOR:
                  //                         rectangle.resizing = true;
                  //                         rectangle.dragging = false;
            
                  //                         rectangle.setBounds(x, y, width + dx, height);
                  //                         rectangle.start = e.getPoint();
                  //                         rectangle.width = width;
                  //                         rectangle.height = height;
                  //                         rectangle.repaint();
                  //                         break;
                  //                   case Cursor.NW_RESIZE_CURSOR:
                  //                         rectangle.resizing = true;
                  //                         rectangle.dragging = false;
            
                  //                         rectangle.setBounds(x + dx, y + dy, width - dx, height - dy);
                  //                         rectangle.width = width;
                  //                         rectangle.height = height;
                  //                         rectangle.repaint();
                  //                         break;
                  //                   case Cursor.NE_RESIZE_CURSOR:
                  //                         rectangle.resizing = true;
                  //                         rectangle.dragging = false;
            
                  //                         rectangle.setBounds(x, y + dy, width + dx, height - dy);
                  //                         rectangle.start = new Point(e.getX(), rectangle.start.y);
                  //                         rectangle.width = width;
                  //                         rectangle.height = height;
                  //                         rectangle.repaint();
                  //                         break;
                  //                   case Cursor.SW_RESIZE_CURSOR:
                  //                         rectangle.resizing = true;
                  //                         rectangle.dragging = false;
            
                  //                         rectangle.setBounds(x + dx, y, width - dx, height + dy);
                  //                         rectangle.start = new Point(rectangle.start.x, e.getY());
                  //                         rectangle.width = width;
                  //                         rectangle.height = height;
                  //                         rectangle.repaint();
                  //                         break;
                  //                   case Cursor.SE_RESIZE_CURSOR:
                  //                         rectangle.resizing = true;
                  //                         rectangle.dragging = false;
            
                  //                         rectangle.setBounds(x, y, width + dx, height + dy);
                  //                         rectangle.start = e.getPoint();
                  //                         rectangle.width = width;
                  //                         rectangle.height = height;
                  //                         rectangle.repaint();
                  //                         break;
                  //                   case Cursor.MOVE_CURSOR:    
                  //                         rectangle.resizing = false;  
                  //                         rectangle.dragging = true;   
                                          
                  //                         System.out.println(rectangle.cursor);
            
                  //                         java.awt.Rectangle bounds = rectangle.getBounds();
                  //                         bounds.translate(dx, dy);
                  //                         rectangle.setBounds(bounds);
                  //                         rectangle.width = rectangle.getWidth();
                  //                         rectangle.height = rectangle.getHeight();
                  //                         rectangle.repaint();
            
                  //             }
                  //             rectangle.setCursor(Cursor.getPredefinedCursor(rectangle.cursor));
                  //       } 
                  // }
            });
      }

      public void visitEllipse(Ellipse ellipse){

      }
}