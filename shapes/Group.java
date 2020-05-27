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
                              select.execute();
                              //this.board.invoker.execute(select);
                              // Deselects the shape when is it selected
                              // but isn't clicked
                        } else {
                              Order deselect = new DeselectShapeCommand(shape, e);
                              deselect.execute();
                              //this.board.invoker.execute(deselect);
                        }

                        // Initialises resizing when a handle is pressed
                        if(shape.getHandleIfSelected(e.getX(), e.getY())){
                              System.out.println("resizing execute");
                              Order resize = new ResizeShapeCommand(shape, new Location(shape.x, shape.y, shape.width, shape.height));
                              //resize.execute();
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
                        deselect.execute();
                        this.board.invoker.execute(deselect);
                  }
            }

            this.selected = false;
            repaint();
      }

      public void dragCommand(Location location){ 
            // Clears the redo stack
            this.redoStack.clear();
            // Adds a new item to the undo stack
            this.undoStack.add(location);
            this.dragging = true;
            // Updates the start location
            this.start = new Location(location.x, location.y, location.width, location.height);
            repaint();
      }

      public void resizeCommand(Location location){
            // Clears the redo stack
            this.redoStack.clear();
            // Adds a new item to the undo stack
            this.undoStack.add(location);
            this.resizing = true;
            // Updates the location
            this.start = new Location(location.x, location.y, location.width, location.height);
            repaint();
      }

      public void clear(){
            // Clears the group
            this.dragging = false;
            this.resizing = false;

            // Clears each child
            for(BaseShape shape : this.children){
                  Order save = new SaveShapeCommand(shape, new Location(shape.x, shape.y, shape.width, shape.height));
                  save.execute();
                  shape.clear();
            }
      }

      public void undoDrag() {
            // Pops an item from the undo stack
            Location location = this.undoStack.pop();
            Location locationOld = new Location(this.x, this.y, this.width, this.height);
            this.redoStack.add(locationOld);

            // Assigns the popped item to the current x, y, width and height values
            this.x = location.x;
            this.y = location.y;
            this.width = location.width;
            this.height = location.height;
            repaint();

            // Undos each child, if it exists
            if(this.children != null) {
                  for(BaseShape shape : this.children) {
                        shape.undoDrag();
                  }
            }
      }

      public void redoDrag() {
            if (this.redoStack.size() > 0) {
                  // Pops an item from the redo stack
                  Location location = this.redoStack.pop();
                  Location locationOld = new Location(this.x, this.y, this.width, this.height);
                  // Adds the current x, y, width and height values to the undo stack
                  this.undoStack.add(locationOld);

                  // Assigns the popped item to the current x, y, width and height values
                  this.x = location.x;
                  this.y = location.y;
                  this.width = location.width;
                  this.height = location.height;
                  repaint();
            }

            // Redos each child, if it exists
            if(this.children != null) {
                  for(BaseShape shape : this.children) {
                        shape.redoDrag();
                  }
            }
      }

      // 'Prints' the group by displaying it on the right
      public void print(Layers layers){
            // Sets the text of the label
            JLabel label = new JLabel();
            label.setText("Group");
            try {
                  // Sets the icon of the label
                  ImageIcon image = new ImageIcon(ImageIO.read(new File("img/group.png")));
                  label.setIcon(image);
                  layers.add(Box.createRigidArea(new Dimension(10, 10)));
                  layers.add(label);
            } catch(IOException e){

            }

            // 'Prints' each child
            for(BaseShape shape : this.children){
                  shape.print(layers);
                  
            }
      }

      // Returns true whether a child is selected, returns false otherwise
      public boolean getIfSelected(int x, int y) {
            for(BaseShape shape : this.children){
                  if(shape.getIfSelected(x, y)){
                        return true;
                  }
            }
            return false;
      }

      // Returns true when the handle is clicked
      public boolean getHandleIfSelected(int x, int y){
            boolean s = false;
            // Returns true when a child's handle is selected
            for(BaseShape shape : this.children){
                  if(shape.getHandleIfSelected(x, y)){
                        s = true;
                        return true;
                  }
            }

            // Gets the group handle if no child handle is selected
            if(s == false){
                  // Loops through the width of the handle
                  for(int i = this.x + this.width - 6; i < this.x + this.width + 6; i++){
                        // Loops through the height of the handle
                        for(int j = this.y + this.height - 6; j < this.y + this.height + 6; j++){
                              if(i == x){
                                    if(j == y){
                                          // Returns true when the handle is clicked
                                          return true;
                                    }
                              }
                        }
                  }
            }
            return false;
      }

      // Returns the smallest x value of all children
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

      // Returns the smallest y value of all children
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

      // Returns the width of the group
      public int getwidth(){
            int maxWidth = 0;
            for(BaseShape shape : this.children){
                  // Calculates the difference between the shape.x and the group.x 
                  int relative = shape.x - this.x;
                  // Adds the calculated difference to the shape.width
                  int width = relative + shape.width;
                  if(shape.drawed){
                        // Assigns the calculates max width to the width variable when is it larger
                        // than the previous width
                        if(width > maxWidth){
                              maxWidth = width;
                        }
                  }
            }
            return maxWidth;
      }

      // Returns the height of the group
      public int getheight(){
            int maxHeight = 0;
            for(BaseShape shape : this.children){
                  // Calculates the difference between the shape.y and the group.y
                  int relative = shape.y - this.y;
                  // Adds the calculated difference to the shape.height
                  int height = relative + shape.height;
                  if(shape.drawed){
                        // Assigns the calculates max height to the height variable when is it larger
                        // than the previous height
                        if(height > maxHeight){
                              maxHeight = height;
                        }
                  }
            }
            return maxHeight;
      }

      // Determines whether a child is selected
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
            
            // Sets the group x, y, width and height attributes
            this.x = this.getx();
            this.y = this.gety();
            this.width = this.getwidth();
            this.height = this.getheight();

            // The group must be selected at all times
            if(this.selected){
                  // Draw the border when no child is selected
                  if(!this.isChildSelected()){
                        this.strategy.execute(this.x, this.y, this.width, this.height, g, true);
                  }
            }
      }
}
