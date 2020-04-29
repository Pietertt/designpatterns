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

      int locations[] = { SwingConstants.SOUTH_EAST };

      int cursors[] = { Cursor.SE_RESIZE_CURSOR };

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
            var rect = new Rectangle(x + width - 8, y + height - this.size, 8, 8);
            g.setColor(Color.WHITE);
            g.fillOval(rect.x - 2, rect.y - 2, rect.width + 4, rect.height + 4);
            g.setColor(new Color(80, 155, 229));
            g.fillOval(rect.x, rect.y, rect.width, rect.height);      
      }

      public int getCursor(MouseEvent e) {
            Component component = e.getComponent();
            int width = component.getWidth();
            System.out.println(width);
            int height = component.getHeight();

            var rect = new Rectangle(0 + width - 8, 0 + height - this.size, 8, 8);
            System.out.println(rect);
            if (rect.contains(e.getPoint())) {
                  return Cursor.SE_RESIZE_CURSOR;
            }
            
            return Cursor.MOVE_CURSOR;
      }
}