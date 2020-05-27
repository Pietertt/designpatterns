package shapes;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Stack;
import commands.*;
import UI.*;
import visitor.Visitor;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;

import javax.swing.border.Border;

public abstract class Shape extends JComponent implements MouseMotionListener, MouseListener {
      public int x;
      public int y;
      public int width;
      public int height;

      // The redo and undo stack
      public Stack<Location> redoStack = new Stack<Location>();
      public Stack<Location> undoStack = new Stack<Location>();

      public int cursor;
      public Point start = null;
      public Invoker invoker;
      public Board board;

      // Some variables to store the current state of the shape
      public boolean selected = false;
      public boolean drawed = false;
      public boolean dragging = false;
      public boolean resizing = false;

      // Some custom colors
      int[] gray = { 205, 205, 205 };
      int[] blue = { 80, 155, 229 };

      public abstract void accept(Visitor visitor);

      // Initializes the shape
      public void place(Invoker invoker, Board board) {
            this.invoker = invoker;
            this.board = board;
            JPanel area = new JPanel();
            setOpaque(true);

            setBounds(this.x, this.y, this.width, this.height);
            add(area);

            // Adds mouselisteners
            addMouseListener(this);
            addMouseMotionListener(this);
            this.drawed = true;

            repaint();
      }

      public void remove() {
            /*
             * Sets the drawed flag to false. The shape is hidden by doing this. Repaint is
             * immediatly called to redraw the shape
             */
            this.drawed = false;
            repaint();
      }

      // Sets the border to a custom border 
      public void select(MouseEvent e) {
            this.selected = true;
            setBorder(new ResizableBorder());
            repaint();
      }

      // Sets the border to an empty border
      public void deselect() {
            setBorder(BorderFactory.createEmptyBorder());
            this.selected = false;
            repaint();
      }

      public void drag(Location location) {
            // Clears the redo stack
            this.redoStack.clear();
            // Adds a new Location object to the undo stack
            this.undoStack.add(location);
      }

      public void undoDrag() {
            // Pops a element from the undo stack and stores it to a variable
            Location location = this.undoStack.pop();
            // Add the popped object to the redo stack
            this.redoStack.add(location);

            // Updates the bounds of the current shape 
            java.awt.Rectangle bounds = new java.awt.Rectangle();
            bounds.x = location.x;
            bounds.y = location.y;
            bounds.width = location.width;
            bounds.height = location.height;
            setBounds(bounds);
            repaint();
      }

      public void redoDrag() {
            // Redo is not possible when there is no history
            if (this.redoStack.size() > 0) {
                  // Pops a element from the redo stack and stores it in a variable
                  Location location = this.redoStack.pop();
                  // Add the popped object to the redo stack
                  this.undoStack.add(location);

                  // Updates the bounds of the current shape 
                  java.awt.Rectangle bounds = new java.awt.Rectangle();
                  bounds.x = location.x;
                  bounds.y = location.y;
                  bounds.width = location.width;
                  bounds.height = location.height;
                  setBounds(bounds);
                  repaint();
            }
      }

      public void mouseClicked(MouseEvent e) {

      }

      public void mouseExited(MouseEvent e) {

      }

      public void mouseEntered(MouseEvent e) {

      }

      public void mouseReleased(MouseEvent e) {

      }

      public void mousePressed(MouseEvent e) {
            // Only shapes which haven't been selected can be selected
            if (!this.selected) {
                  // Deselects a shape when it is selected
                  for (Shape shape : board.shapes) {
                        if (shape.selected) {
                              Order deselect = new DeselectShapeCommand(shape, e);
                              this.invoker.execute(deselect);
                        }
                  }

                  // Selects the shape
                  Order select = new SelectShapeCommand(this, e);
                  this.invoker.execute(select);
                  requestFocus();
            }

            var resizableBorder = (ResizableBorder) getBorder();
            this.cursor = resizableBorder.getCursor(e);
            this.start = e.getPoint();
      }

      @Override
      public void mouseMoved(MouseEvent e) {
            // Updates the mouse when the mouse is moving of the shape
            if (this.selected) {
                  var resizableBorder = (ResizableBorder) getBorder();
                  var cursor = resizableBorder.getCursor(e);
                  setCursor(Cursor.getPredefinedCursor(cursor));
            }
      }

      @Override
      public void mouseDragged(MouseEvent e) {

      }

      public boolean getIfSelected(int x, int y) {
            // Loops through the x axis
            for (int i = 0; i < this.width; i++) {
                  if (x == this.x + i) {
                        // Loops through the y axis
                        for (int j = 0; j < this.height; j++) {
                              if (y == this.y + j) {
                                    return true;
                              }
                        }
                  }
            }
            return false;
      }
}