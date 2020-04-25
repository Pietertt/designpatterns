package shapes;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.event.MouseInputAdapter;
import javax.swing.event.MouseInputListener;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Point;
import java.awt.event.MouseEvent;

public class Rectangle extends JComponent {
      private int x;
      private int y;
      private int width;
      private int height;

      public Rectangle(int x, int y, int width, int height) {
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;

            JPanel area = new JPanel();
            area.setBackground(Color.WHITE);
            setBounds(this.x, this.y, this.width, this.height);
            setLayout(new BorderLayout());
            add(area);
            addMouseListener(resizeListener);
            addMouseMotionListener(resizeListener);
            setBorder(new ResizableBorder());
      }

      private void resize() {
            if(getParent() != null){
                  getParent().revalidate();
            }
      }

      MouseInputListener resizeListener = new MouseInputAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                  if (hasFocus()) {
                        var resizableBorder = (ResizableBorder) getBorder();
                        setCursor(Cursor.getPredefinedCursor(resizableBorder.getCursor(e)));
                  }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                  setCursor(Cursor.getDefaultCursor());
            }

            private int cursor;
            private Point start = null;

            @Override
            public void mousePressed(MouseEvent e) {

                  var resizableBorder = (ResizableBorder) getBorder();
                  cursor = resizableBorder.getCursor(e);
                  start = e.getPoint();

                  requestFocus();
                  repaint();
            }

            @Override
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
                                    break;

                              case Cursor.S_RESIZE_CURSOR:
                                    setBounds(x, y, width, height + dy);
                                    start = e.getPoint();
                                    resize();
                                    break;

                              case Cursor.W_RESIZE_CURSOR:
                                    setBounds(x + dx, y, width - dx, height);
                                    resize();
                                    break;

                              case Cursor.E_RESIZE_CURSOR:
                                    setBounds(x, y, width + dx, height);
                                    start = e.getPoint();
                                    resize();
                                    break;

                              case Cursor.NW_RESIZE_CURSOR:
                                    setBounds(x + dx, y + dy, width - dx, height - dy);
                                    resize();
                                    break;

                              case Cursor.NE_RESIZE_CURSOR:
                                    setBounds(x, y + dy, width + dx, height - dy);
                                    start = new Point(e.getX(), start.y);
                                    break;

                              case Cursor.SW_RESIZE_CURSOR:
                                    setBounds(x + dx, y, width - dx, height + dy);
                                    start = new Point(start.x, e.getY());
                                    resize();
                                    break;

                              case Cursor.SE_RESIZE_CURSOR:
                                    setBounds(x, y, width + dx, height + dy);
                                    start = e.getPoint();
                                    resize();
                                    break;

                              case Cursor.MOVE_CURSOR:
                                    var bounds = getBounds();
                                    bounds.translate(dx, dy);
                                    setBounds(bounds);
                                    resize();
                        }

                        setCursor(Cursor.getPredefinedCursor(cursor));
                  }
            }

            @Override
            public void mouseReleased(MouseEvent mouseEvent) {
                  start = null;
            }
      };
}