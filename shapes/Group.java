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
import strategies.Strategy;
import visitor.Visitor;

public class Group extends BaseShape {
      public Board board;
      private JFrame RectangleOrnamentWindow;
      private JButton submit;

      public ArrayList<TextShapeDecorator> decorators = new ArrayList<>();

      public Group(Board board){
            this.board = board;
      }

      public void setStrategy(Strategy strategy){
            this.strategy = strategy;
      }

      public void addd(BaseShape shape){
            this.children.add(shape);
      }

      public String toString(int indent){
            StringBuilder string = new StringBuilder();

            // Appends the right amount of tabs to the string
            for(int i = 0; i < indent; i++){
                  string.append("\t");
            }

            string.append("group" + " " + this.children.size() + System.lineSeparator());

            for(BaseShape shape : this.children) {
                  if(shape instanceof TextShapeDecorator) {
                        decorators.add((TextShapeDecorator) shape);
                  }
            }

            // Adds any decorators to the grammar
            for(int i = 0; i < this.children.size(); i++){
                  if(i == (this.children.size() - 1)){
                        for(TextShapeDecorator decorator : decorators) {
                              if (this.children.get(i).equals(decorator.getDecoratedShape())) {
                                    string.append(decorator.toString(indent + 1));
                              }
                        }
                        if(!(this.children.get(i) instanceof TextShapeDecorator))
                              string.append(this.children.get(i).toString(indent + 1));
                  } else {
                        for(TextShapeDecorator decorator : decorators) {
                              if (this.children.get(i).equals(decorator.getDecoratedShape())) {
                                    string.append(decorator.toString(indent + 1));
                              }
                        }
                        if(!(this.children.get(i) instanceof TextShapeDecorator))
                              string.append(this.children.get(i).toString(indent + 1) + System.lineSeparator());
                  }
            }

            return string.toString();
            
      }

      // Removes a child
      public void remove(Shape shape){
            this.children.remove(shape);
      }

      // Accepts any visitor
      public void accept(Visitor visitor){
            visitor.visit(this); 
      }

      // Places the group
      public void place() {
            this.drawed = true;
            repaint();
      }

      // 'Removes' the group by not drawing it
      public void remove(){
            this.drawed = false;
            repaint();
      }

      public void drag(Location location){
            boolean s = false;

            // Drags any child when it is selected
            for(BaseShape shape : this.children){
                  if(shape.selected){
                        Location childLocation = new Location(location.x, location.y, shape.width, shape.height);
                        shape.drag(childLocation);
                        s = true; 
                  }
            }

            // Drags all children when no children are selected
            if(s == false){
                  for(BaseShape shape : this.children){
                        int dx = location.x - this.start.x + shape.start.x;
                        int dy = location.y - this.start.y + shape.start.y;

                        Location childLocation = new Location();
                        childLocation.x = dx;
                        childLocation.y = dy;
                        childLocation.width = shape.width;
                        childLocation.height = shape.height;

                        shape.drag(childLocation);
                        
                  }
                  repaint();      
            }
      }

      public void resize(Location location){
            boolean s = false;

            // Resizes any child when a child is selected
            for(BaseShape shape : this.children){
                  if(shape.selected){
                        s = true;
                        Location childLocation = new Location(shape.x, shape.y, location.width - (shape.start.x - location.x), location.height - (shape.start.y - location.y));
                        shape.resize(childLocation);
                  }
            }

            // Resizes all children when no child is selected
            if(s == false){
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
                        shape.resize(childLocation);
                  }
            }
      }

      public void select(MouseEvent e) {
            for(BaseShape shape : this.children){
                  // The group must be selected to selects its children
                  if(this.selected){
                        // Selects the shape when it is clicked
                        if(shape.getIfSelected(e.getX(), e.getY())){
                              Order select = new SelectShapeCommand(shape, e);
                              this.board.invoker.execute(select);
                              // Deselects the shape when is it selected
                              // but isn't clicked
                        } else {
                              Order deselect = new DeselectShapeCommand(shape, e);
                              this.board.invoker.execute(deselect);     
                        }

                        // Initialises resizing when a handle is pressed
                        if(shape.getHandleIfSelected(e.getX(), e.getY())){
                              System.out.println("resizing execute");
                              Order resize = new ResizeShapeCommand(shape, new Location(shape.x, shape.y, shape.width, shape.height));
                              this.board.invoker.execute(resize);
                        } 
                  }
            }
            this.selected = true;
      }

      public void deselect(MouseEvent e) {
            // Deselects all children
            for(BaseShape shape : this.children){
                  if(shape.selected){
                        Order deselect = new DeselectShapeCommand(shape, e);
                        this.board.invoker.execute(deselect);
                  }
            }

            this.selected = false;
            repaint();
      }

      public void dragCommand(Location location){ 
            this.redoStack.clear();
            this.undoStack.add(location);
            this.dragging = true;
            this.start = new Location(location.x, location.y, location.width, location.height);
            repaint();
      }

      public void resizeCommand(Location location){
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
                  Order save = new SaveShapeCommand(shape, new Location(shape.x, shape.y, shape.width, shape.height));
                  this.board.invoker.execute(save);
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

      public boolean getIfSelected(int x, int y) {
            for(BaseShape shape : this.children){
                  if(shape.getIfSelected(x, y)){
                        return true;
                  }
            }
            return false;
      }

      public boolean getHandleIfSelected(int x, int y){
            boolean s = false;
            for(BaseShape shape : this.children){
                  if(shape.getHandleIfSelected(x, y)){
                        s = true;
                        return true;
                  }
            }

            if(s == false){
                  for(int i = this.x + this.width - 6; i < this.x + this.width + 6; i++){
                        for(int j = this.y + this.height - 6; j < this.y + this.height + 6; j++){
                              if(i == x){
                                    if(j == y){
                                          return true;
                                    }
                              }
                        }
                  }
            }
            return false;
      }

      public int getx(){
            int x = this.children.get(0).x;
            for(BaseShape shape : this.children){
                  if(shape.drawed){
                        if(shape.x < x){
                              x = shape.x;
                        }
                  }
            }
            return x;
      }

      public int gety(){
            int y = this.children.get(0).y;
            for(BaseShape shape : this.children){
                  if(shape.drawed){
                        if(shape.y < y){
                              y = shape.y;
                        }
                  }
            }
            return y;
      }

      public int getwidth(){
            int maxWidth = 0;
            for(BaseShape shape : this.children){
                  int relative = shape.x - this.x;
                  int width = relative + shape.width;
                  if(shape.drawed){
                        if(width > maxWidth){
                              maxWidth = width;
                        }
                  }
            }
            return maxWidth;
      }

      public int getheight(){
            int maxHeight = 0;
            for(BaseShape shape : this.children){
                  int relative = shape.y - this.y;
                  int height = relative + shape.height;
                  if(shape.drawed){
                        if(height > maxHeight){
                              maxHeight = height;
                        }
                  }
            }
            return maxHeight;
      }

      public boolean isChildSelected(){
            for(BaseShape shape : this.children){
                  if(shape.selected){
                        return true;
                  }
            }
            return false;
      }

      @Override
      public void paintComponent(Graphics g) {
            super.paintComponent(g);                  
            
            this.x = this.getx();
            this.y = this.gety();
            this.width = this.getwidth();
            this.height = this.getheight();

            if(this.selected){
                  if(!this.isChildSelected()){
                        this.strategy.execute(this.x, this.y, this.width, this.height, g, true);
                  }
            }
      }
}
