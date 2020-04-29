package visitor;

import java.awt.event.*;
import java.awt.*;
import java.awt.Cursor;
import javax.swing.*;

import shapes.*;
import shapes.Rectangle;
import shapes.Shape;
import commands.*;

public class moveVisitor implements Visitor {

      public void visitRectangle(Rectangle rectangle){
            rectangle.addMouseListener(new MouseAdapter(){
                  public void mouseReleased(MouseEvent e){
                        /*
                              Pollution of the undo/redo history is prevented by only adding 
                              DragShapeCommand when the shape has been dragged
                        */
                        if(rectangle.dragging){
                              Location location = new Location(rectangle.getX(), rectangle.getY(), rectangle.getWidth(), rectangle.getHeight());
                              Order drag = new DragShapeCommand(rectangle, location);
                              rectangle.invoker.execute(drag);
                              rectangle.dragging = false;
                        }  
                  }

                  public void mousePressed(MouseEvent e){
                        /*
                              Executes a drag command to note the initial position
                        */
                        if(rectangle.undoStack.size() == 0){
                              Location location = new Location(rectangle.getX(), rectangle.getY(), rectangle.getWidth(), rectangle.getHeight());
                              Order drag = new DragShapeCommand(rectangle, location);
                              rectangle.invoker.execute(drag);
                        }
                  }
            });

            rectangle.addMouseMotionListener(new MouseAdapter(){
                  public void mouseDragged(MouseEvent e){
                        if (rectangle.start != null) {
                              int dx = e.getX() - rectangle.start.x;
                              int dy = e.getY() - rectangle.start.y;
            
                              if(rectangle.cursor == Cursor.MOVE_CURSOR){
                                    rectangle.resizing = false;  
                                    rectangle.dragging = true;      

                                    var bounds = rectangle.getBounds();
                                    bounds.translate(dx, dy);
                                    rectangle.setBounds(bounds);
                                    rectangle.width = rectangle.getWidth();
                                    rectangle.height = rectangle.getHeight();
                                    rectangle.repaint();
                              }
                        }
                  }
            });
      }

      public void visitEllipse(Ellipse ellipse){
            ellipse.addMouseListener(new MouseAdapter(){
                  public void mouseReleased(MouseEvent e){
                        /*
                              Pollution of the undo/redo history is prevented by only adding 
                              DragShapeCommand when the shape has been dragged
                        */
                        if(ellipse.dragging){
                              Location location = new Location(ellipse.getX(), ellipse.getY(), ellipse.getWidth(), ellipse.getHeight());
                              Order drag = new DragShapeCommand(ellipse, location);
                              ellipse.invoker.execute(drag);
                              ellipse.dragging = false;
                        }  
                  }

                  public void mousePressed(MouseEvent e){
                        /*
                              Executes a drag command to note the initial position
                        */
                        if(ellipse.undoStack.size() == 0){
                              Location location = new Location(ellipse.getX(), ellipse.getY(), ellipse.getWidth(), ellipse.getHeight());
                              Order drag = new DragShapeCommand(ellipse, location);
                              ellipse.invoker.execute(drag);
                        }
                  }
            });

            ellipse.addMouseMotionListener(new MouseAdapter(){
                  public void mouseDragged(MouseEvent e){
                        if (ellipse.start != null) {
                              int dx = e.getX() - ellipse.start.x;
                              int dy = e.getY() - ellipse.start.y;
            
                              if(ellipse.cursor == Cursor.MOVE_CURSOR){
                                    ellipse.resizing = false;  
                                    ellipse.dragging = true;      

                                    var bounds = ellipse.getBounds();
                                    bounds.translate(dx, dy);
                                    ellipse.setBounds(bounds);
                                    ellipse.width = ellipse.getWidth();
                                    ellipse.height = ellipse.getHeight();
                                    ellipse.repaint();
                              }
                        }
                  }
            });
      }
}