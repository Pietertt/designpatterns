package shapes;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;

public class Rectangle extends JComponent implements MouseListener, MouseMotionListener {
      private int x;
      private int y;
      private int width;
      private int height;

      private int cursor;
            private Point start = null;

      public Rectangle(int x, int y, int width, int height) {
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
      }

      @Override
      public void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(Color.RED);
            g.fillRect(0, 0, this.width, this.height);
            g.drawRect(0, 0, this.width, this.height);
      }

      public void place(){
            JPanel area = new JPanel();
            setOpaque(true);

            setBounds(this.x, this.y, this.width, this.height);
            add(area);
            addMouseListener(this);
            addMouseMotionListener(this);
            setBorder(new ResizableBorder());
      }

      private void resize() {
            if(getParent() != null){
                  getParent().revalidate();
            }
      }

      public void mouseClicked(MouseEvent e){

      }

      public void mouseExited(MouseEvent e) {
            setCursor(Cursor.getDefaultCursor());
      }

      public void mouseReleased(MouseEvent mouseEvent) {
            start = null;
      }

      public void mousePressed(MouseEvent e) {
            var resizableBorder = (ResizableBorder) getBorder();
            cursor = resizableBorder.getCursor(e);
            start = e.getPoint();

            requestFocus();
            repaint();
      }

      public void mouseDragged(MouseEvent e) {
            if (start != null) {
                  int x = getX();
                  int y = getY();
                  int width = getWidth();
                  int height = getHeight();
                  int dx = e.getX() - start.x;
                  int dy = e.getY() - start.y;

                  switch (cursor) {
                        case Cursor.N_RESIZE_CURSOR:
                              setBounds(x, y + dy, width, height - dy);
                              resize();
                              this.width = width;
                              this.height = height;
                              repaint();
                              break;
                        case Cursor.S_RESIZE_CURSOR:
                              setBounds(x, y, width, height + dy);
                              start = e.getPoint();
                              resize();
                              this.width = width;
                              this.height = height;
                              repaint();
                              break;
                        case Cursor.W_RESIZE_CURSOR:
                              setBounds(x + dx, y, width - dx, height);
                              resize();
                              this.width = width;
                              this.height = height;
                              repaint();
                              break;
                        case Cursor.E_RESIZE_CURSOR:
                              setBounds(x, y, width + dx, height);
                              start = e.getPoint();
                              resize();
                              this.width = width;
                              this.height = height;
                              repaint();
                              break;
                        case Cursor.NW_RESIZE_CURSOR:
                              setBounds(x + dx, y + dy, width - dx, height - dy);
                              resize();
                              this.width = width;
                              this.height = height;
                              repaint();
                              break;
                        case Cursor.NE_RESIZE_CURSOR:
                              setBounds(x, y + dy, width + dx, height - dy);
                              start = new Point(e.getX(), start.y);
                              this.width = width;
                              this.height = height;
                              repaint();
                              break;
                        case Cursor.SW_RESIZE_CURSOR:
                              setBounds(x + dx, y, width - dx, height + dy);
                              start = new Point(start.x, e.getY());
                              resize();
                              this.width = width;
                              this.height = height;
                              repaint();
                              break;
                        case Cursor.SE_RESIZE_CURSOR:
                              setBounds(x, y, width + dx, height + dy);
                              start = e.getPoint();
                              resize();
                              this.width = width;
                              this.height = height;
                              repaint();
                              break;
                        case Cursor.MOVE_CURSOR:
                              var bounds = getBounds();
                              bounds.translate(dx, dy);
                              setBounds(bounds);
                              resize();
                              this.width = width;
                              this.height = height;
                              repaint();

                  }
                  setCursor(Cursor.getPredefinedCursor(cursor));
            }
      }
      
      public void mouseMoved(MouseEvent e) {
            if (hasFocus()) {
                  var resizableBorder = (ResizableBorder) getBorder();
                  setCursor(Cursor.getPredefinedCursor(resizableBorder.getCursor(e)));
            }
      }   

      public void mouseEntered(MouseEvent e){

      }


}