package UI;

import javax.swing.*;

import commands.*;
import shapes.*;
import shapes.Shape;
import strategies.*;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Board extends JPanel implements MouseListener, MouseMotionListener {
      public JFrame frame;
      public Invoker invoker = new Invoker();
      public ArrayList<BaseShape> shapes = new ArrayList<BaseShape>();
      public Strategy strategy;

      public boolean created = false;

      // New window to edit ornaments
      private JFrame RectangleOrnamentWindow;
      private JButton submit;

      public Board(JFrame frame){        
            setOpaque(false);    
            super.setFocusable(true);
            this.frame = frame;
            addMouseListener(this);
            addMouseMotionListener(this);
      }

      public void init(){
            this.strategy = new PlaceRectangleStrategy(this.invoker, this);

            for(int i = 0; i < 5; i++){
                  this.strategy.place(50 + i * 100, 100, 50, 50);
                  this.shapes.add(this.strategy.shape);
                  this.frame.add(this.strategy.shape);
                  this.frame.revalidate();
                  this.frame.repaint();
            }

            this.strategy = new PlaceEllipseStrategy(this.invoker, this);

            for(int i = 0; i < 5; i++){
                  this.strategy.place(50 + i * 100, 300, 50, 50);
                  this.shapes.add(this.strategy.shape);
                  this.frame.add(this.strategy.shape);
                  this.frame.revalidate();
                  this.frame.repaint();
            }

            Group group = new Group(100, 400, 50, 50, this);
            Order place = new PlaceShapeCommand(group);
            this.invoker.execute(place);

            this.strategy.place(200, 400, 50, 50);
            group.addd(this.strategy.shape);
            //this.shapes.add(this.strategy.shape);
            this.frame.add(this.strategy.shape);
            this.frame.revalidate();
            this.frame.repaint();

            this.strategy.place(300, 400, 50, 50);
            group.addd(this.strategy.shape);
            //this.shapes.add(this.strategy.shape);
            this.frame.add(this.strategy.shape);
            this.frame.revalidate();
            this.frame.repaint();

            this.strategy.place(500, 200, 50, 50);
            group.addd(this.strategy.shape);
            //this.shapes.add(this.strategy.shape);
            this.frame.add(this.strategy.shape);
            this.frame.revalidate();
            this.frame.repaint();

            this.shapes.add(group);
            this.frame.add(group);
            this.frame.revalidate();
            this.frame.repaint();
      }

      public void mousePressed(MouseEvent e){
            for(BaseShape shape : this.shapes){
                  if(shape.drawed){
                        //if(!shape.selected){
                              if(shape.getIfSelected(e.getX(), e.getY())){
                                    Order select = new SelectShapeCommand(shape, e);
                                    this.invoker.execute(select);
                                    if (e.getClickCount() == 2) {
                                          RectangleOrnamentWindow = new JFrame("New Window");
                                          RectangleOrnamentWindow.pack();
                                          RectangleOrnamentWindow.setVisible(true);

                                          String[] labels = {"Top side: ", "Bottom side: ", "Left side: ", "Right side: "};

                                          JPanel p = new JPanel();
                                          //Lay out the panel.
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
                                                if(!textTop.getText().isEmpty() || !textBottom.getText().isEmpty() ||
                                                        !textLeft.getText().isEmpty() || !textRight.getText().isEmpty()) {

                                                      Shape base =  new TextShapeDecorator(shape,
                                                              textBottom.getText(), textTop.getText(),
                                                              textLeft.getText(), textRight.getText());
                                                      for (Component component : shape.getComponents()) {
                                                            shape.remove(component);
                                                      }
                                                      shape.add((JComponent) base);
                                                      this.frame.repaint();
                                                      JOptionPane.showMessageDialog(null, "Ornament(s) added");
                                                }
                                                else
                                                      JOptionPane.showMessageDialog(null, "All fields empty\n " +
                                                              "Fill in at least one field to submit ornament");
                                          });
                                          p.add(submit);

                                          RectangleOrnamentWindow.add(p);

                                          RectangleOrnamentWindow.setSize(400,200);

                                    }
                              }
                        //}
      
                        if(shape.selected){
                              if(shape.getIfSelected(e.getX(), e.getY())){
                                    Order drag = new DragShapeCommand(shape, new Location(shape.x, shape.y, shape.width, shape.height));
                                    this.invoker.execute(drag);
                              } else {
                                    if(shape.getHandleIfSelected(e.getX(), e.getY())){
                                          Order resize = new ResizeShapeCommand(shape, new Location(shape.x, shape.y, shape.width, shape.height));
                                          this.invoker.execute(resize);
                                    } else {
                                          Order deselect = new DeselectShapeCommand(shape, e);
                                          this.invoker.execute(deselect);
                                    }
                              }
                        }
                  }
            }
      }

      public void mouseReleased(MouseEvent e){
            for(BaseShape shape : this.shapes){
                  if(shape.dragging){
                        shape.dragging = false;
                  }

                  if(shape.resizing){
                        shape.resizing = false;
                  }
            }
      }

      public void mouseEntered(MouseEvent e){
            
      }

      public void mouseExited(MouseEvent e){
            
      }

      public void mouseClicked(MouseEvent e){
            
      }

      @Override
      public void mouseMoved(MouseEvent e){

      }

      @Override
      public void mouseDragged(MouseEvent e){
            for(BaseShape shape : this.shapes){
                  if(shape.resizing){
                        Location location = new Location(shape.x, shape.y, e.getX() - shape.start.x, e.getY() - shape.start.y);
                        shape.move(location);
                  }

                  if(shape.dragging){
                        Location location = new Location(e.getX(), e.getY(), shape.width, shape.height);
                        shape.move(location);
                  }
            }
      }



}