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

      // All strategies are instantiated one time
      public Strategy groupStrategy = PlaceGroupStrategy.getInstance();
      public Strategy rectangleStrategy = PlaceRectangleStrategy.getInstance();
      public Strategy ellipseStrategy = PlaceEllipseStrategy.getInstance();
      public Strategy triangleStrategy = PlaceTriangleStrategy.getInstance();

      public Strategy currentStrategy = this.rectangleStrategy;

      public Visitor dragVisitor = new DragVisitor();

      // Some variables for controlling the state
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

            // Loops through all children
            Iterator<BaseShape> i = this.group.children.iterator();
            while (i.hasNext()) {
                  BaseShape shape = i.next();
                  // Adds all selected children to an arraylist
                  if(shape.selected){
                        grouped.add(shape);
                        shape.selected = false;
                        shape.repaint();
                        i.remove();
                  }
            }

            // Instantiates the group
            Group group = new Group(this);
            group.setStrategy(this.currentStrategy);
            Order place = new PlaceShapeCommand(group);
            this.invoker.execute(place);
            
            // Adds all selected children to the new group
            for(BaseShape shape : grouped){
                  group.children.add(shape);
            }

            // Adds the group to the main group
            this.group.children.add(group);

            this.layers.update(this.group);
            this.app.add(group);
            this.app.revalidate();
            this.app.repaint();
      }

      private BaseShape getSelectedShape(BaseShape shape) {
            if(shape == null) {
                  return null;
            }

            // Loops recursive through all groups until it has no children
            if(shape.children != null) {
                  for (BaseShape children : shape.children) {
                        if(children.selected) {
                              return getSelectedShape(children);
                        }
                  }
            }

            // Returns the shape whether the shape is selected
            if(shape.selected) {
                  return shape;
            } else {
                  return null;
            }
      }

      private void addDecoratorToGroup(BaseShape shape, TextShapeDecorator decoratedShape) {
            if(shape == null) {
                  return;
            }
            boolean changed = false;

            // Determines the desired shape
            for (Iterator<BaseShape> iterator = shape.children.iterator(); iterator.hasNext();) {
                  BaseShape shape1 = iterator.next();
                  if (shape1.equals(decoratedShape.getDecoratedShape())) {
                        changed = true;
                  }
            }

            // Adds the shape to the children
            if(changed) {
                  shape.children.add(decoratedShape);
            } else {
                  for (BaseShape children : shape.children) {
                        addDecoratorToGroup(children, decoratedShape);
                  }
            }
      }

      public void addOrnament() {
            BaseShape selectedToDecorate = getSelectedShape(this.group);

            // Created the window
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

                  // Adds an action listener to the submit button
                  submit.addActionListener(arg0 -> {
                        if (!textTop.getText().isEmpty() || !textBottom.getText().isEmpty()
                                || !textLeft.getText().isEmpty()
                                || !textRight.getText().isEmpty()) {

                              // Created a new decorator and appends the texts to it
                              base = new TextShapeDecorator(finalSelectedToDecorate, textBottom.getText(),
                                      textTop.getText(), textLeft.getText(), textRight.getText());
                              for (Component component : finalSelectedToDecorate.getComponents()) {
                                    finalSelectedToDecorate.remove(component);
                              }

                              // Adds the decoration to the group
                              finalSelectedToDecorate.add((JComponent) base);
                              
                              addDecoratorToGroup(this.group, base);

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
                  // Adds a new shape to the main group
                  BaseShape shape = new Shape(e.getX(), e.getY(), 50, 50);
                  shape.setStrategy(this.currentStrategy);
                  Order place = new PlaceShapeCommand(shape);
                  this.invoker.execute(place);
                  this.group.children.add(0, place.shape);
                  this.app.add(place.shape);
                  this.app.revalidate();
                  this.app.repaint();
                  this.layers.update(this.group);
                  this.created = false;
            }

            for (BaseShape shape : this.group.children) {
                  if (shape.drawed) {
                        // Selected the shape when it is drawed
                        if (shape.getIfSelected(e.getX(), e.getY())) {
                              Order select = new SelectShapeCommand(shape, e);
                              select.execute();
                              //this.invoker.execute(select);
                        }
                        BaseShape selectedToDecorate = getSelectedShape(this.group);
                        if (shape.selected) {
                              // Drags the shape when it is selected and pressed
                              if (shape.getIfSelected(e.getX(), e.getY())) {
                                    Order drag = new DragShapeCommand(shape,
                                    new Location(shape.x, shape.y, shape.width, shape.height));
                                    this.invoker.execute(drag);
                              } else {
                                    // Resizes the shape when the handle is pressed
                                    if (shape.getHandleIfSelected(e.getX(), e.getY())) {
                                          Order resize = new ResizeShapeCommand(shape,
                                          new Location(shape.x, shape.y, shape.width, shape.height));
                                          this.invoker.execute(resize);

                                    } else {
                                          // Deselects the shape 
                                          if(!this.shifted){
                                                Order deselect = new DeselectShapeCommand(shape, e);
                                                //deselect.execute();
                                                this.invoker.execute(deselect);
                                          } 
                                    }
                              }
                        }
                  }
            }
      }

      public void mouseReleased(MouseEvent e) {
            // Clears all shapes
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

      // Sets some variables when specific keys are pressed
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

      // Resets all variables 
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
                  // Accepts a resize visitor when the shape is resizing
                  if(shape.resizing) {
                        shape.accept(resize);
                  }

                  // Accepts a drag visitor when the shape is dragging
                  if(shape.dragging) {
                        shape.accept(move);
                  }
            }

            if(move.selectedShape != null)
                  try {
                        // Drags the shape
                        move.drag(new Location(e.getX(), e.getY(), move.selectedShape.width, move.selectedShape.height));
                  } catch(NullPointerException error){
                        System.out.println("Er ging iets fout:" + error.getMessage());
                  }

            if(resize.selectedShape != null)
                  // Resizes the shape
                  resize.resize(new Location(resize.selectedShape.x, resize.selectedShape.y, e.getX() - resize.selectedShape.start.x, e.getY() - resize.selectedShape.start.y));
      }

      public void export(){
            // Accepts the accept visitor
            ExportVisitor exportVisitor = new ExportVisitor();
            this.group.accept(exportVisitor);

            // Exports the file
            fileIO.export(exportVisitor.export());
      }

      // Fetches data from file
      public void fetch(){
            this.app.clear();
            this.app.init();
      }


      public void init(){
            // Sets the strategy
            this.currentStrategy = this.groupStrategy;
            this.group.setStrategy(this.currentStrategy);

            // Places the group
            Order place = new PlaceShapeCommand(this.group);
            this.invoker.execute(place);

            // Reads from file
            fileIO file = new fileIO();
            this.group = file.read(this);

            this.app.revalidate();
            this.app.repaint();

            // Update the layers
            this.layers.update(this.group);
      }
}