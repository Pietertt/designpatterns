package UI;

import javax.swing.*;
import commands.*;
import shapes.*;
import shapes.Rectangle;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Iterator;

public class Board extends JPanel implements MouseListener, MouseMotionListener, KeyListener {
      public JFrame frame;
      public Layers layers;
      public Invoker invoker = new Invoker();
      private ArrayList<BaseShape> shapes = new ArrayList<BaseShape>();

      public boolean created = false;
      public boolean shifted = false;
      public boolean cmd = false;
      public boolean ctrl = false;

      public String placeWhich = "";

      // New window to edit ornaments
      private JFrame RectangleOrnamentWindow;
      private JButton submit;

      public Board(JFrame frame, Layers layers) {
            setOpaque(false);
            super.setFocusable(true);
            this.frame = frame;
            this.layers = layers;
            addMouseListener(this);
            addMouseMotionListener(this);
            addKeyListener(this);
      }

      // Initieer het board
      public void init() {
            for (int i = 0; i < 5; i++) {
                  BaseShape shape = new Rectangle(50 + i * 100, 100, 50, 50);
                  Order place = new PlaceShapeCommand(shape);
                  this.invoker.execute(place);
                  this.shapes.add(shape);
                  this.frame.add(shape);
                  this.frame.revalidate();
                  this.frame.repaint();
            }

            for (int i = 0; i < 5; i++) {
                  BaseShape shape2 = new Ellipse(50 + i * 100, 300, 50, 50);
                  Order place = new PlaceShapeCommand(shape2);
                  this.invoker.execute(place);
                  this.shapes.add(shape2);
                  this.frame.add(shape2);
                  this.frame.revalidate();
                  this.frame.repaint();
            }

            this.frame.revalidate();
            this.frame.repaint();
            
            this.layers.update(this.shapes);
      }

      // Groupeer meerdere geselecteerde shapes
      public void group(){
            ArrayList<BaseShape> grouped = new ArrayList<BaseShape>();
            Iterator<BaseShape> i = this.shapes.iterator();
            while (i.hasNext()) {
                  BaseShape shape = i.next();
                  if(shape.selected){
                        grouped.add(shape);
                        shape.selected = false;
                        shape.repaint();
                        i.remove();
                  }
            }

            Group group = new Group(0, 0, 0, 0, this);
            Order place = new PlaceShapeCommand(group);
            this.invoker.execute(place);
            
            for(BaseShape shape : grouped){
                  group.addd(shape);
            }

            this.shapes.add(group);
            this.layers.update(this.shapes);

            this.frame.add(group);
            this.frame.revalidate();
            this.frame.repaint();

      }

      // Maak nieuwe shapes aan, of selecteer ze door te klikken met shift
      public void mousePressed(MouseEvent e) {
            requestFocus();
            if (this.created) {
                  BaseShape shape = null;
                  if(placeWhich.equals("Rectangle")) {
                        shape = new Rectangle(e.getX(), e.getY(), 50, 50);
                  }
                  if(placeWhich.equals("Ellipse")) {
                        shape = new Ellipse(e.getX(), e.getY(), 50, 50);
                  }

                  Order place = new PlaceShapeCommand(shape);
                  this.invoker.execute(place);
                  this.shapes.add(shape);
                  this.frame.add(shape);
                  this.frame.revalidate();
                  this.frame.repaint();
                  this.created = false;   
                  
                  this.layers.update(this.shapes);
            }

            for (BaseShape shape : this.shapes) {
                  if (shape.drawed) {
                        if (shape.getIfSelected(e.getX(), e.getY())) {
                              Order select = new SelectShapeCommand(shape, e);
                              this.invoker.execute(select);
                        }

                        if (shape.selected) {
                              if (shape.getIfSelected(e.getX(), e.getY())) {
                                    Order drag = new DragShapeCommand(shape,
                                    new Location(shape.x, shape.y, shape.width, shape.height));
                                    this.invoker.execute(drag);
                              } else {
                                    if (shape.getHandleIfSelected(e.getX(), e.getY())) {
                                          Order resize = new ResizeShapeCommand(shape,
                                          new Location(shape.x, shape.y, shape.width, shape.height));
                                          this.invoker.execute(resize);

                                    } else {
                                          if(!this.shifted){
                                                Order deselect = new DeselectShapeCommand(shape, e);
                                                this.invoker.execute(deselect);
                                          } 
                                    }
                              }
                        }
                  }
            }
      }

      public void mouseReleased(MouseEvent e) {
            for (BaseShape shape : this.shapes) {
                  shape.clear();
            }
      }

      public void mouseEntered(MouseEvent e) {

      }

      public void mouseExited(MouseEvent e) {

      }

      public void mouseClicked(MouseEvent e) {

      }

      public void keyTyped(KeyEvent e) {

      }

      public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == 16) {
                  this.shifted = true;
            }

            if(e.getKeyCode() == 157){
                  this.cmd = true;
            }

            if(e.getKeyCode() == 17){
                  this.ctrl = true;
            }

            if(e.getKeyCode() == 71){
                  if((this.cmd) || (this.ctrl)){
                        this.group();
                  }
            }
      }

      public void keyReleased(KeyEvent e) {
            this.shifted = false;
            this.cmd = false;
            this.ctrl = false;
      }

      @Override
      public void mouseMoved(MouseEvent e) {

      }

      // Drag de shape
      @Override
      public void mouseDragged(MouseEvent e) {
            for (BaseShape shape : this.shapes) {
                  if (shape.resizing) {
                        Location location = new Location(shape.x, shape.y, e.getX() - shape.start.x, e.getY() - shape.start.y);
                        shape.resize(location);
                  }

                  if (shape.dragging) {
                        Location location = new Location(e.getX(), e.getY(), shape.width, shape.height);
                        shape.drag(location);
                  }
            }
      }

}