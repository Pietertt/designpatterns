package UI;

import javax.swing.*;

import IO.fileIO;
import commands.*;
import shapes.*;
import shapes.Shape;
import strategies.*;
import visitor.*;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import App.*;

public class Board extends JPanel implements MouseListener, MouseMotionListener, KeyListener {
      public App app;
      public Layers layers;
      public Invoker invoker = new Invoker();
      public BaseShape group = new Group(this);
      public Strategy groupStrategy = PlaceGroupStrategy.getInstance();
      public Strategy rectangleStrategy = PlaceRectangleStrategy.getInstance();
      public Strategy ellipseStrategy = PlaceEllipseStrategy.getInstance();
      public Strategy triangleStrategy = PlaceTriangleStrategy.getInstance();

      public Visitor dragVisitor = new DragVisitor();

      public Strategy currentStrategy = this.rectangleStrategy;

      public boolean created = false;
      public boolean shifted = false;
      public boolean cmd = false;
      public boolean ctrl = false;

      // New window to edit ornaments
      private JFrame RectangleOrnamentWindow;
      private JButton submit;


      // decorator
      private TextShapeDecorator base;

      public Board(App app, Layers layers) {
            setOpaque(false);
            super.setFocusable(true);
            this.app = app;
            this.layers = layers;
            addMouseListener(this);
            addMouseMotionListener(this);
            addKeyListener(this);
      }

      public void group(){
            this.currentStrategy = this.groupStrategy;

            ArrayList<BaseShape> grouped = new ArrayList<BaseShape>();
            Iterator<BaseShape> i = this.group.children.iterator();
            while (i.hasNext()) {
                  BaseShape shape = i.next();
                  if(shape.selected){
                        grouped.add(shape);
                        shape.selected = false;
                        shape.repaint();
                        i.remove();
                  }
            }

            Group group = new Group(this);
            group.setStrategy(this.currentStrategy);
            Order place = new PlaceShapeCommand(group);
            this.invoker.execute(place);
            
            for(BaseShape shape : grouped){
                  group.addd(shape);
            }

            this.group.children.add(group);
            this.layers.update(this.group);

            this.app.add(group);
            this.app.revalidate();
            this.app.repaint();
      }

      public BaseShape getSelectedShape(BaseShape shape) {
            for(BaseShape shapes : shape.children) {
                  if(shapes.selected) {
                        return getSelectedShape(shape);
                  }
            }
            return null;
      }

      public void addOrnament() {
            BaseShape selectedToDecorate = null;

            for (BaseShape shape : this.group.children) {
                  if(shape.selected) {
                        selectedToDecorate = shape;
                  }
                  for (BaseShape children : shape.children) {
                        if (children.selected) {
                              selectedToDecorate = getSelectedShape(children);
                        }

                  }
            }

            if (selectedToDecorate != null && selectedToDecorate.drawed) {
                  RectangleOrnamentWindow = new JFrame("Add ornaments");
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
                  BaseShape finalSelectedToDecorate = selectedToDecorate;
                  submit.addActionListener(arg0 -> {
                        if (!textTop.getText().isEmpty() || !textBottom.getText().isEmpty()
                                || !textLeft.getText().isEmpty()
                                || !textRight.getText().isEmpty()) {

                              base = new TextShapeDecorator(finalSelectedToDecorate, textBottom.getText(),
                                      textTop.getText(), textLeft.getText(), textRight.getText());
                              for (Component component : finalSelectedToDecorate.getComponents()) {
                                    finalSelectedToDecorate.remove(component);
                              }

                              finalSelectedToDecorate.add((JComponent) base);
                              this.app.repaint();
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

      public void mousePressed(MouseEvent e) {
            requestFocus();
            if (this.created) {
                  BaseShape shape = new Shape(e.getX(), e.getY(), 50, 50);
                  shape.setStrategy(this.currentStrategy);
                  Order place = new PlaceShapeCommand(shape);
                  this.invoker.execute(place);
                  this.group.children.add(place.shape);
                  this.app.add(place.shape);
                  this.app.revalidate();
                  this.app.repaint();
                  this.layers.update(this.group);
                  this.created = false;
            }

            for (BaseShape shape : this.group.children) {
                  if (shape.drawed) {
                        if (shape.getIfSelected(e.getX(), e.getY())) {
                              System.out.println("selected");
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
            for (BaseShape shape : this.group.children) {
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
            DragVisitor move = new DragVisitor();
            ResizeVisitor resize = new ResizeVisitor();
            for (BaseShape shape : this.group.children) {
                  if(shape.resizing) {
                        shape.accept(resize);
                  }

                  if(shape.dragging) {
                        shape.accept(move);
                  }
            }

            if(move.selectedShape != null)
                  move.drag(new Location(e.getX(), e.getY(), move.selectedShape.width, move.selectedShape.height));

            if(resize.selectedShape != null)
                  resize.resize(new Location(resize.selectedShape.x, resize.selectedShape.y, e.getX() - resize.selectedShape.start.x, e.getY() - resize.selectedShape.start.y));
      }

      public void export(){
            ExportVisitor exportVisitor = new ExportVisitor();
            this.group.accept(exportVisitor);            

            fileIO.export(exportVisitor.export());
      }

      public void fetch(){
            this.app.clear();
            this.app.init();
      }


      public void init(){
            this.currentStrategy = this.groupStrategy;
            this.group.setStrategy(this.currentStrategy);
            Order place = new PlaceShapeCommand(this.group);
            this.invoker.execute(place);
            fileIO file = new fileIO();
            this.group = file.read(this);
            //file.read(this);
            // this.app.add(this.group);
            this.app.revalidate();
            this.app.repaint();
            this.layers.update(this.group);
      }
}