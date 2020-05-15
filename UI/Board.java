package UI;

import javax.swing.*;

import commands.*;
import shapes.*;
import shapes.Shape;
import strategies.*;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Iterator;

public class Board extends JPanel implements MouseListener, MouseMotionListener, KeyListener {
      public JFrame frame;
      public Layers layers;
      public Invoker invoker = new Invoker();
      private ArrayList<BaseShape> shapes = new ArrayList<BaseShape>();
      private Strategy strategy;

      public boolean created = false;
      public boolean shifted = false;
      public boolean cmd = false;
      public boolean ctrl = false;

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

      public void init() {
            // this.strategy = PlaceEllipseStrategy.getInstance(this);

            for (int i = 0; i < 5; i++) {
                  BaseShape shape = new Shape(50 + 75 * i, 100, 50, 50);
                  shape.setStrategy(new PlaceRectangleStrategy());
                  Order place = new PlaceShapeCommand(shape);
                  this.invoker.execute(place);
                  this.shapes.add(place.shape);
                  this.frame.add(place.shape);
                  this.frame.revalidate();
                  this.frame.repaint();
            }

            for (int i = 0; i < 5; i++) {
                  BaseShape shape = new Shape(50 + 75 * i, 200, 50, 50);
                  shape.setStrategy(new PlaceEllipseStrategy());
                  Order place = new PlaceShapeCommand(shape);
                  this.invoker.execute(place);
                  this.shapes.add(place.shape);
                  this.frame.add(place.shape);
                  this.frame.revalidate();
                  this.frame.repaint();
            }
            this.layers.update(this.shapes);
      }

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

      public void setStrategy(Strategy strategy){
            this.strategy = strategy;
      }

      public void mousePressed(MouseEvent e) {
            requestFocus();
            // if (this.created) {
            //       this.strategy.place(e.getX(), e.getY(), 50, 50);
            //       this.shapes.add(this.strategy.shape);
            //       this.frame.add(this.strategy.shape);
            //       this.frame.revalidate();
            //       this.frame.repaint();
            //       this.created = false;   
                  
            //       this.layers.update(this.shapes);
            // }

            for (BaseShape shape : this.shapes) {
                  if (shape.drawed) {
                        if (shape.getIfSelected(e.getX(), e.getY())) {
                              System.out.println("selected");
                              Order select = new SelectShapeCommand(shape, e);
                              this.invoker.execute(select);
                              if (e.getClickCount() == 2) {
                                    String[] labels = { "Top side: ", "Bottom side: ", "Left side: ", "Right side: " };

                                    RectangleOrnamentWindow = new JFrame("New Window");
                                    RectangleOrnamentWindow.pack();
                                    RectangleOrnamentWindow.setVisible(true);
                                    JPanel p = new JPanel();

                                    // Lay out the panel.
                                    GridLayout grid = new GridLayout();
                                    grid.setColumns(2);
                                    grid.setRows(5);
                                    grid.setHgap(5);
                                    grid.setVgap(5);
                                    p.setLayout(grid);

                                    JTextField textTop = new JTextField(25);
                                    JLabel labelTop = new JLabel("Top side: ");
                                    p.add(labelTop);
                                    p.add(textTop);

                                    JTextField textBottom = new JTextField(25);
                                    JLabel labelBottom = new JLabel("Bottom side: ");
                                    p.add(labelBottom);
                                    p.add(textBottom);

                                    JTextField textLeft = new JTextField(25);
                                    JLabel labelLeft = new JLabel("Left side: ");
                                    p.add(labelLeft);
                                    p.add(textLeft);

                                    JTextField textRight = new JTextField(25);
                                    JLabel labelRight = new JLabel("Right side: ");
                                    p.add(labelRight);
                                    p.add(textRight);

                                    submit = new JButton("Submit");
                                    submit.addActionListener(arg0 -> {
                                          if (!textTop.getText().isEmpty() || !textBottom.getText().isEmpty()
                                                      || !textLeft.getText().isEmpty()
                                                      || !textRight.getText().isEmpty()) {

                                                TextShapeDecorator base = new TextShapeDecorator(shape, textBottom.getText(),
                                                            textTop.getText(), textLeft.getText(), textRight.getText());
                                                for (Component component : shape.getComponents()) {
                                                      shape.remove(component);
                                                }
                                                shape.add((JComponent) base);
                                                this.frame.repaint();
                                                JOptionPane.showMessageDialog(null, "Ornament(s) added");
                                          } else
                                                JOptionPane.showMessageDialog(null, "All fields empty\n "
                                                            + "Fill in at least one field to submit ornament");
                                    });
                                    p.add(submit);

                                    RectangleOrnamentWindow.add(p);

                                    RectangleOrnamentWindow.setSize(400, 200);

                              }
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