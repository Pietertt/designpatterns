package shapes;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;
import visitor.*;

import UI.Layers;
import strategies.Strategy;

public class Shape extends BaseShape {

      public Shape(int x, int y, int width, int height) {
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
      }


      public String toString(int indent){
            String string = "";
            // Appends tabs to the string 
            for(int i = 0; i < indent; i++){
                  string += "\t";
            }
            // Adds data to the string
            string += this.strategy.toString() + " " + this.x + " " + this.y + " " + this.width + " " + this.height;
            return string;
      }

      // Accepts any visitor
      public void accept(Visitor visitor){
            visitor.visit(this); 
      }
      
      // Places the shape
      public void place(){
            this.drawed = true;
            repaint();
      }

      // Sets any strategy
      public void setStrategy(Strategy strategy){
            this.strategy = strategy;
      }

      // 'Removes' the shape
      public void remove(){
            this.drawed = false;
            repaint();
      }

      // Updates the shape's location
      public void resize(Location location){
            this.x = location.x;
            this.y = location.y;
            this.width = location.width;
            this.height = location.height;
            repaint();
      }

      // Updates the shape's location
      public void drag(Location location){
            this.x = location.x;
            this.y = location.y;
            this.width = location.width;
            this.height = location.height;
            repaint();
      }

      // Allows the shape to be dragged in the future
      public void dragCommand(Location location){
            this.redoStack.clear();
            this.undoStack.add(location);
            this.dragging = true;
            this.start = new Location(location.x, location.y, location.width, location.height);
            repaint();
      }

      public void undoDrag() {
            // Pops a item of the undo stack
            Location location = this.undoStack.pop();
            Location locationOld = new Location(this.x, this.y, this.width, this.height);
            // Adds an item to the redo stack
            this.redoStack.add(locationOld);

            // Updates the shape's location
            this.x = location.x;
            this.y = location.y;
            this.width = location.width;
            this.height = location.height;
            repaint();
      }

      public void redoDrag() {
            if (this.redoStack.size() > 0) {
                  // Pops an item off the redo stack
                  Location location = this.redoStack.pop();
                  Location locationOld = new Location(this.x, this.y, this.width, this.height);
                  // Adds an item to the undo stack
                  this.undoStack.add(locationOld);

                  // Updates the shape's location
                  this.x = location.x;
                  this.y = location.y;
                  this.width = location.width;
                  this.height = location.height;
                  repaint();
            }
      }

      // Selects the shape
      public void select(MouseEvent e) {
            this.selected = true;
            repaint();
      }

      // Deselects the shape
      public void deselect(MouseEvent e) {
            this.selected = false;
            this.setBorder(BorderFactory.createEmptyBorder());
            repaint();
      }

      // Allows the shape to be resized in the future
      public void resizeCommand(Location location){
            this.redoStack.clear();
            this.undoStack.add(location);
            this.resizing = true;
            this.start = new Location(location.x, location.y, location.width, location.height);
            repaint();
      }

      // Clears the shape
      public void clear(){
            this.dragging = false;
            this.resizing = false;
      }

      // 'Prints' the shape by displaying it on the right
      public void print(Layers layers){
            // Sets the label
            JLabel label = new JLabel();
            label.setText("Rectangle");
            label.setBorder(new EmptyBorder(0, 30, 0, 0));
            
            // Tries to set the icon
            try {
                  ImageIcon image = new ImageIcon(ImageIO.read(new File("img/" + this.strategy.toString() + ".png")));
                  label.setIcon(image);
                  layers.add(Box.createRigidArea(new Dimension(10, 10)));
                  layers.add(label);
            } catch(IOException e){
                  System.out.println("File not found");
            }
      }

      // Determines whether the shape is clicked
      public boolean getIfSelected(int x, int y) {
            // Loops through the width
            for (int i = 0; i < this.width; i++) {
                  if (x == this.x + i) {
                        // Loops through the height
                        for (int j = 0; j < this.height; j++) {
                              if (y == this.y + j) {
                                    // Returns true whether the shape is clicked
                                    return true;
                              }
                        }
                  }
            }
            return false;
      }

      // Determines whether the handle is selected
      public boolean getHandleIfSelected(int x, int y){
            // Loops through the handle width
            for(int i = this.x + this.width - 6; i < this.x + this.width + 6; i++){
                  // Loops through the handle height
                  for(int j = this.y + this.height - 6; j < this.y + this.height + 6; j++){
                        if(i == x){
                              if(j == y){
                                    // Returns true whether the handle is clicked
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
            // Draws the shape when it can be drawed. The drawing is left to the current strategy
            if(this.drawed){
                  this.strategy.execute(this.x, this.y, this.width, this.height, g, this.selected);
            }
      }
}