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
                        
                        if(rectangle.resizing){
                              Location location = new Location(rectangle.getX(), rectangle.getY(), rectangle.getWidth(), rectangle.getHeight());
                              Order drag = new ResizeShapeCommand(rectangle, location);
                              rectangle.invoker.execute(drag);
                              rectangle.resizing = false;
                        }
                  }

                  public void mousePressed(MouseEvent e){
                        if(rectangle.undoStack.size() == 0){
                              Location location = new Location(rectangle.getX(), rectangle.getY(), rectangle.getWidth(), rectangle.getHeight());
                              Order drag = new DragShapeCommand(rectangle, location);
                              rectangle.invoker.execute(drag);
                        }
            
                        if(!rectangle.selected){
                              for(Shape shape : rectangle.board.shapes){
                                    if(shape.selected){  
                                          Order deselect = new DeselectShapeCommand(shape, e);
                                          rectangle.invoker.execute(deselect);
                                    }
                              }
            
                              Order select = new SelectShapeCommand(rectangle, e);
                              rectangle.invoker.execute(select);
                              rectangle.requestFocus();
                        }
            
                        var resizableBorder = (ResizableBorder) rectangle.getBorder();
                        rectangle.cursor = resizableBorder.getCursor(e);
                        rectangle.start = e.getPoint();
                  }
            });

            rectangle.addMouseMotionListener(new MouseAdapter(){
                  public void mouseMoved(MouseEvent e){
                        if (rectangle.selected) {
                              var resizableBorder = (ResizableBorder) rectangle.getBorder();
                              var cursor = resizableBorder.getCursor(e);
                              rectangle.setCursor(Cursor.getPredefinedCursor(cursor));
                        }
                  }

                  public void mouseDragged(MouseEvent e){
                        if (rectangle.start != null) {
                              int x = rectangle.getX();
                              int y = rectangle.getY();
                              int width = rectangle.getWidth();
                              int height = rectangle.getHeight();
                              int dx = e.getX() - rectangle.start.x;
                              int dy = e.getY() - rectangle.start.y;
            
                              switch (rectangle.cursor) {
                                    case Cursor.SE_RESIZE_CURSOR:
                                          rectangle.resizing = true;
                                          rectangle.dragging = false;
            
                                          rectangle.setBounds(x, y, width + dx, height + dy);
                                          rectangle.start = e.getPoint();
                                          rectangle.width = width;
                                          rectangle.height = height;
                                          rectangle.repaint();
                                          break;
                                    case Cursor.MOVE_CURSOR:    
                                          rectangle.resizing = false;  
                                          rectangle.dragging = true;      
            
                                          var bounds = rectangle.getBounds();
                                          bounds.translate(dx, dy);
                                          rectangle.setBounds(bounds);
                                          rectangle.width = rectangle.getWidth();
                                          rectangle.height = rectangle.getHeight();
                                          rectangle.repaint();
            
                              }
                              rectangle.setCursor(Cursor.getPredefinedCursor(rectangle.cursor));
                        }
                  }
            });
      }

      public void visitEllipse(Ellipse ellipse){

      }
}