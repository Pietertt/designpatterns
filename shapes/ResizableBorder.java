package shapes;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

public class ResizableBorder implements Border {

      private int size = 8;

      int locations[] = { SwingConstants.NORTH, SwingConstants.SOUTH, SwingConstants.WEST, SwingConstants.EAST,
                  SwingConstants.NORTH_WEST, SwingConstants.NORTH_EAST, SwingConstants.SOUTH_WEST,
                  SwingConstants.SOUTH_EAST };

      int cursors[] = { Cursor.N_RESIZE_CURSOR, Cursor.S_RESIZE_CURSOR, Cursor.W_RESIZE_CURSOR, Cursor.E_RESIZE_CURSOR,
                  Cursor.NW_RESIZE_CURSOR, Cursor.NE_RESIZE_CURSOR, Cursor.SW_RESIZE_CURSOR, Cursor.SE_RESIZE_CURSOR };

      @Override
      public Insets getBorderInsets(Component component) {
            return new Insets(this.size, this.size, this.size, this.size);
      }

      @Override
      public boolean isBorderOpaque() {
            return false;
      }

      @Override
      public void paintBorder(Component component, Graphics g, int x, int y, int width, int height) {
            // if (component.hasFocus()) {
                  for (int i = 0; i < locations.length; i++) {
                        var rect = getRectangle(x, y, width, height, locations[i]);
                        g.setColor(Color.WHITE);
                        g.fillOval(rect.x - 2, rect.y - 2, rect.width + 4, rect.height + 4);
                        g.setColor(new Color(80, 155, 229));
                        g.fillOval(rect.x, rect.y, rect.width, rect.height);
                  }
            //}
      }

      private Rectangle getRectangle(int x, int y, int width, int height, int location) {
            switch (location) {
                  case SwingConstants.SOUTH_EAST:
                        return new Rectangle(x + width - 8, y + height - this.size, 8, 8);
                  default:
                        return new Rectangle(0, 0, 0, 0);
            }
      }

      public int getCursor(MouseEvent e) {
            Component component = e.getComponent();
            int width = component.getWidth();
            int height = component.getHeight();

            for (int i = 0; i < locations.length; i++) {
                  var rect = getRectangle(0, 0, width, height, locations[i]);
                  if (rect.contains(e.getPoint())) {
                        return cursors[i];
                  }
            }
            return Cursor.MOVE_CURSOR;
      }
}