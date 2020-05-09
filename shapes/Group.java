package shapes;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.util.ArrayList;
import java.util.Stack;
import commands.*;
import UI.*;
import shapes.*;
import visitor.Visitor;

public class Group extends BaseShape {
      public ArrayList<BaseShape> children = new ArrayList<BaseShape>();
      public Board board;
      private JFrame RectangleOrnamentWindow;
      private JButton submit;

      public Group(int x, int y, int width, int height, Board board){
            super(x, y, width, height);
            this.board = board;
      }

      public void addd(BaseShape shape){
            this.children.add(shape);
      }

      public void remove(Shape shape){
            this.children.remove(shape);
      }

      public void accept(Visitor visitor){

      }

      public void place() {
            this.drawed = true;
            repaint();
      }

      public void remove(){
            this.drawed = false;
            repaint();
      }

      public void move(Location location){
            boolean selected = false;
            for(BaseShape shape : this.children){
                  if(shape.selected){
                        selected = true;
                        if(shape.dragging){
                              Location childLocation = new Location(location.x, location.y, shape.width, shape.height);
                              shape.move(childLocation);
                        }

                        if(shape.resizing){
                              Location childLocation = new Location(shape.x, shape.y, location.x - shape.start.x, location.y - shape.start.y);
                              shape.move(childLocation);
                        }
                  }                  
            }

            if(!selected){
                  if(this.dragging){
                        for(BaseShape shape : this.children){
                              Location childLocation = new Location((shape.x - this.x) + location.x, (shape.y - this.y) + location.y, shape.width, shape.height);
                              shape.move(childLocation);
                        }
                        repaint();
                  }

                  if(this.resizing){                        
                        float percentageWidth = (float)location.width / (float)this.start.width;
                        float percentageHeight = (float)location.height / (float)this.start.height;

                        for(BaseShape shape : this.children){
                              float diffX = ((float)shape.start.x - (float)this.x) * percentageWidth;
                              float diffY = ((float)shape.start.y - (float)this.y) * percentageHeight;

                              Location childLocation = new Location();
                              childLocation.x = this.start.x + Math.round(diffX);
                              childLocation.y = this.start.y + Math.round(diffY);
                              childLocation.width = Math.round((float)shape.start.width * percentageWidth);
                              childLocation.height = Math.round((float)shape.start.height * percentageHeight);

                              System.out.println(this.start.x + Math.round(diffX));
                             
                              shape.move(childLocation);
                        }
                  }
            }
      }

      public void select(MouseEvent e) {
            for(BaseShape shape : this.children){
                  if(this.selected){
                        if(shape.getIfSelected(e.getX(), e.getY())){
                              shape.clear();
                              Order select = new SelectShapeCommand(shape, e);
                              this.board.invoker.execute(select);

                              Order drag = new DragShapeCommand(shape, new Location(shape.x, shape.y, shape.width, shape.height));
                              this.board.invoker.execute(drag);
                        } else {
                              if(!board.shifted){
                                    Order deselect = new DeselectShapeCommand(shape, e);
                                    this.board.invoker.execute(deselect);
                              } 
                        }

                        if(shape.getHandleIfSelected(e.getX(), e.getY())){
                              shape.clear();
                              Order resize = new ResizeShapeCommand(shape, new Location(shape.x, shape.y, shape.width, shape.height));
                              this.board.invoker.execute(resize);
                        }
                  }
            }
            this.selected = true;
      }

      public void deselect(MouseEvent e) {
            for(BaseShape shape : this.children){
                  if(shape.selected){
                        Order deselect = new DeselectShapeCommand(shape, e);
                        this.board.invoker.execute(deselect);
                  }
            }

            this.selected = false;
            repaint();
      }

      public void drag(Location location){
            for(BaseShape shape : this.children){
                  Order save = new SaveShapeCommand(shape, new Location(shape.x, shape.y, shape.width, shape.height));
                  this.board.invoker.execute(save);
            }

            this.redoStack.clear();
            this.undoStack.add(location);
            this.dragging = true;
            this.start = new Location(location.x, location.y, location.width, location.height);
            repaint();
      }

      public void resize(Location location){
            for(BaseShape shape : this.children){
                  Order save = new SaveShapeCommand(shape, new Location(shape.x, shape.y, shape.width, shape.height));
                  this.board.invoker.execute(save);
            }

            this.redoStack.clear();
            this.undoStack.add(location);
            this.resizing = true;
            this.start = new Location(location.x, location.y, location.width, location.height);
            repaint();
      }

      public void clear(){
            this.dragging = false;
            this.resizing = false;

            for(BaseShape shape : this.children){
                  shape.clear();
            }
      }

      public void undoDrag() {
            Location location = this.undoStack.pop();
            this.redoStack.add(location);
            this.x = location.x;
            this.y = location.y;
            this.width = location.width;
            this.height = location.height;
            repaint();
      }

      public void redoDrag() {
            if (this.redoStack.size() > 0) {
                  Location location = this.redoStack.pop();
                  this.undoStack.add(location);
                  this.x = location.x;
                  this.y = location.y;
                  this.width = location.width;
                  this.height = location.height;
                  repaint();
            }
      }

      public void print(Layers layers){
            JLabel label = new JLabel();
            label.setText("Group");
            try {
                  ImageIcon image = new ImageIcon(ImageIO.read(new File("img/group.png")));
                  label.setIcon(image);
                  layers.add(Box.createRigidArea(new Dimension(10, 10)));
                  layers.add(label);
            } catch(IOException e){

            }

            for(BaseShape shape : this.children){
                  shape.print(layers);
                  
            }
      }

      public int X(){
            this.x = this.children.get(0).x;
            for(BaseShape shape : this.children){
                  if(shape.drawed){
                        if(shape.x < this.x){
                              this.x = shape.x;
                        }
                  }
            }
            return this.x;
      }

      public int Y(){
            this.y = this.children.get(0).y;
            for(BaseShape shape : this.children){
                  if(shape.drawed){
                        if(shape.y < this.y){
                              this.y = shape.y;
                        }
                  }
            }
            return this.y;
      }

      public int Width(){
            int maxWidth = 0;
            int x = this.X();

            for(BaseShape shape : this.children){
                  int relative = shape.x - x;
                  int width = relative + shape.width;
                  if(shape.drawed){
                        if(width > maxWidth){
                              maxWidth = width;
                        }
                  }
            }
            return maxWidth;
      }

      public int Height(){
            int maxHeight = 0;
            int y = this.Y();

            for(BaseShape shape : this.children){
                  int relative = shape.y - y;
                  int height = relative + shape.height;
                  if(shape.drawed){
                        if(height > maxHeight){
                              maxHeight = height;
                        }
                  }
            }
            return maxHeight;
      }

      public boolean getIfSelected(int x, int y) {
            for(BaseShape shape : this.children){
                  if(shape.getIfSelected(x, y)){
                        return true;
                  }
            }
            return false;
      }

      public boolean getHandleIfSelected(int x, int y){
            for(int i = this.X() + this.Width() - 6; i < this.X() + this.Width() + 6; i++){
                  for(int j = this.Y() + this.Height() - 6; j < this.Y() + this.Height() + 6; j++){
                        if(i == x){
                              if(j == y){
                                    return true;
                              }
                        }
                  }
            }
            return false;
      }

      @Override
      public void paintComponent(Graphics g) {
            super.paintComponent(g);
            this.x = this.X();
            this.y = this.Y();
            this.width = this.Width();
            this.height = this.Height();

            if(this.drawed){
                  if(this.selected){
                        boolean selected = false;
                        for(BaseShape shape : this.children){
                              if(shape.selected){
                                    selected = true;
                              }
                        }

                        if(!selected){
                              g.setColor(new Color(this.blue[0], this.blue[1], this.blue[2]));
                              g.drawRect(this.x, this.y, this.width, this.height);
            
                              g.setColor(Color.WHITE);
                              g.fillOval(this.x + this.width - 6, this.y + this.height - 6, 12, 12);
            
                              g.setColor(new Color(this.blue[0], this.blue[1], this.blue[2]));
                              g.fillOval(this.x + this.width - 4, this.y + this.height - 4, 8, 8);
                        }
                  }
            }
      }
}