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

import UI.Layers;

public class Ellipse extends BaseShape {

      public Ellipse(int x, int y, int width, int height) {
            super(x, y, width, height);
      }

      // Kan een shape getekent worden?
      public void place(){
            this.drawed = true;
            repaint();
      }

      // Verwijderen van een shape
      public void remove(){
            this.drawed = false;
            repaint();
      }

      // Het vergrootten/verkleinen van een shape
      public void resize(Location location){
            this.x = location.x;
            this.y = location.y;
            this.width = location.width;
            this.height = location.height;
            repaint();
      }

      // Het verplaatsen van een shape
      public void drag(Location location){
            this.x = location.x;
            this.y = location.y;
            this.width = location.width;
            this.height = location.height;
            repaint();
      }

      /*
            Commando dat kan worden aangeroepen met het command pattern,
            zorgt ervoor dat deze staat toegevoegd wordt aan de stack
       */
      public void dragCommand(Location location){
            this.redoStack.clear();
            this.undoStack.add(location);
            this.dragging = true;
            this.start = new Location(location.x, location.y, location.width, location.height);
            repaint();
      }

      /*
            Het undo'en van een "drag" dmv het command pattern
       */
      public void undoDrag() {
            Location location = this.undoStack.pop();
            this.redoStack.add(location);
            this.x = location.x;
            this.y = location.y;
            this.width = location.width;
            this.height = location.height;
            repaint();
      }

      /*
            Het redo'en van een "drag" dmv het command pattern
       */
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

      // Selecteer deze shape
      public void select(MouseEvent e) {
            this.selected = true;
            repaint();
      }

      // Deselecteer deze shape
      public void deselect(MouseEvent e) {
            this.selected = false;
            this.setBorder(BorderFactory.createEmptyBorder());
            repaint();
      }

      // Het commando om het resizen aan te roepen
      public void resizeCommand(Location location){
            this.dragging = false;
            this.redoStack.clear();
            this.undoStack.add(location);
            this.resizing = true;
            this.start = new Location(location.x, location.y, location.width, location.height);
            repaint();
      }

      // Het clearen van een shape, zodat de verschillende modus uit staan
      public void clear(){
            this.dragging = false;
            this.resizing = false;
      }

      // het uitprinten van een shape voor de UI
      public void print(Layers layers){
            JLabel label = new JLabel();
            label.setBorder(new EmptyBorder(0, 30, 0, 0));
            label.setText("Ellipse");
            try {
                  ImageIcon image = new ImageIcon(ImageIO.read(new File("img/ellipse.png")));
                  label.setIcon(image);
                  layers.add(Box.createRigidArea(new Dimension(10, 10)));
                  layers.add(label);
            } catch(IOException e){

            }
      }

      // Kijken of een shape geselecteerd is
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

      // Kijken of de handle van deze shape geselecteerd is
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

      // Het tekenen van dit component
      @Override
      public void paintComponent(Graphics g) {
            super.paintComponent(g);
            if(this.drawed){
                  if(this.selected){
                        g.setColor(new Color(this.gray[0], this.gray[1], this.gray[2]));
                        g.fillOval(this.x, this.y, this.width, this.height);
                        g.setColor(new Color(this.blue[0], this.blue[1], this.blue[2]));
                        g.drawOval(this.x, this.y, this.width, this.height);
      
                        g.setColor(Color.WHITE);
                        g.fillOval(this.x + this.width - 6, this.y + this.height - 6, 12, 12);
      
                        g.setColor(new Color(this.blue[0], this.blue[1], this.blue[2]));
                        g.fillOval(this.x + this.width - 4, this.y + this.height - 4, 8, 8);
                  } else {
                        g.setColor(new Color(this.gray[0], this.gray[1], this.gray[2]));
                        g.fillOval(this.x, this.y, this.width, this.height);
                  }
            }
      }
}