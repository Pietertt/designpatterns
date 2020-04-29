package visitor;

import java.awt.event.*;
import java.awt.*;
import java.awt.Cursor;
import javax.swing.*;

import shapes.*;
import shapes.Rectangle;
import shapes.Shape;
import commands.*;

public class resizeVisitor implements Visitor {
      public void visitRectangle(Rectangle rectangle){
            rectangle.addMouseListener(new MouseAdapter(){
                  public void mouseReleased(MouseEvent e){
                        if(rectangle.resizing){
                              Location location = new Location(rectangle.getX(), rectangle.getY(), rectangle.getWidth(), rectangle.getHeight());
                              Order drag = new ResizeShapeCommand(rectangle, location);
                              rectangle.invoker.execute(drag);
                              rectangle.resizing = false;
                        }
                  }
            });

            rectangle.addMouseMotionListener(new MouseAdapter(){
                  public void mouseDragged(MouseEvent e){
                        if (rectangle.start != null) {
                              int x = rectangle.getX();
                              int y = rectangle.getY();
                              int width = rectangle.getWidth();
                              int height = rectangle.getHeight();
                              int dx = e.getX() - rectangle.start.x;
                              int dy = e.getY() - rectangle.start.y;

                              if(rectangle.cursor == Cursor.SE_RESIZE_CURSOR){
                                    rectangle.resizing = true;
                                    rectangle.dragging = false;
      
                                    rectangle.setBounds(x, y, width + dx, height + dy);
                                    rectangle.start = e.getPoint();
                                    rectangle.width = width;
                                    rectangle.height = height;
                                    rectangle.repaint();
                              }

                        }
                  }
            });   
      }

      public void visitEllipse(Ellipse ellipse){
            ellipse.addMouseListener(new MouseAdapter(){
                  public void mouseReleased(MouseEvent e){
                        if(ellipse.resizing){
                              Location location = new Location(ellipse.getX(), ellipse.getY(), ellipse.getWidth(), ellipse.getHeight());
                              Order drag = new ResizeShapeCommand(ellipse, location);
                              ellipse.invoker.execute(drag);
                              ellipse.resizing = false;
                        }
                  }
            });

            ellipse.addMouseMotionListener(new MouseAdapter(){
                  public void mouseDragged(MouseEvent e){
                        if (ellipse.start != null) {
                              int x = ellipse.getX();
                              int y = ellipse.getY();
                              int width = ellipse.getWidth();
                              int height = ellipse.getHeight();
                              int dx = e.getX() - ellipse.start.x;
                              int dy = e.getY() - ellipse.start.y;

                              if(ellipse.cursor == Cursor.SE_RESIZE_CURSOR){
                                    ellipse.resizing = true;
                                    ellipse.dragging = false;
      
                                    ellipse.setBounds(x, y, width + dx, height + dy);
                                    ellipse.start = e.getPoint();
                                    ellipse.width = width;
                                    ellipse.height = height;
                                    ellipse.repaint();
                              }

                        }
                  }
            });   
      }
}