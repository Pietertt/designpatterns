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
            for(int i = 0; i < indent; i++){
                  string += "\t";
            }
            System.out.println(getComponents());
            string += this.strategy.toString() + " " + this.x + " " + this.y + " " + this.width + " " + this.height;
            return string;
      }

      public void accept(Visitor visitor){
            visitor.visit(this); 
      }
      
      public void place(){
            this.drawed = true;
            repaint();
      }

      public void setStrategy(Strategy strategy){
            this.strategy = strategy;
      }

      public void remove(){
            this.drawed = false;
            repaint();
      }

      public void resize(Location location){
            this.x = location.x;
            this.y = location.y;
            this.width = location.width;
            this.height = location.height;
            repaint();
      }

      public void drag(Location location){
            this.x = location.x;
            this.y = location.y;
            this.width = location.width;
            this.height = location.height;
            repaint();
      }

      public void dragCommand(Location location){
            this.redoStack.clear();
            this.undoStack.add(location);
            this.dragging = true;
            this.start = new Location(location.x, location.y, location.width, location.height);
            repaint();
      }

      public void undoDrag() {
            Location location = this.undoStack.pop();
            Location locationOld = new Location(this.x, this.y, this.width, this.height);
            this.redoStack.add(locationOld);
            this.x = location.x;
            this.y = location.y;
            this.width = location.width;
            this.height = location.height;
            repaint();
      }

      public void redoDrag() {
            if (this.redoStack.size() > 0) {
                  Location location = this.redoStack.pop();
                  Location locationOld = new Location(this.x, this.y, this.width, this.height);
                  this.undoStack.add(locationOld);
                  this.x = location.x;
                  this.y = location.y;
                  this.width = location.width;
                  this.height = location.height;
                  repaint();
            }
      }

      public void select(MouseEvent e) {
            this.selected = true;
            repaint();
      }

      public void deselect(MouseEvent e) {
            this.selected = false;
            this.setBorder(BorderFactory.createEmptyBorder());
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
      }

      public void print(Layers layers){
            JLabel label = new JLabel();
            label.setText("Rectangle");
            label.setBorder(new EmptyBorder(0, 30, 0, 0));
            try {
                  ImageIcon image = new ImageIcon(ImageIO.read(new File("img/" + this.strategy.toString() + ".png")));
                  label.setIcon(image);
                  layers.add(Box.createRigidArea(new Dimension(10, 10)));
                  layers.add(label);
            } catch(IOException e){
                  System.out.println("File not found");
            }
      }

      public boolean getIfSelected(int x, int y) {
            for (int i = 0; i < this.width; i++) {
                  if (x == this.x + i) {
                        for (int j = 0; j < this.height; j++) {
                              if (y == this.y + j) {
                                    return true;
                              }
                        }
                  }
            }
            return false;
      }

      public boolean getHandleIfSelected(int x, int y){
            for(int i = this.x + this.width - 6; i < this.x + this.width + 6; i++){
                  for(int j = this.y + this.height - 6; j < this.y + this.height + 6; j++){
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
            if(this.drawed){
                  this.strategy.execute(this.x, this.y, this.width, this.height, g, this.selected);
            }
      }
}