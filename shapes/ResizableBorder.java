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

      @Override
      public Insets getBorderInsets(Component component) {
            return new Insets(8, 8, 8, 8);
      }

      @Override
      public boolean isBorderOpaque() {
            return false;
      }

      // Paints the border
      @Override
      public void paintBorder(Component component, Graphics g, int x, int y, int width, int height) {
            var rect = new Rectangle(x + width - 8, y + height - 8, 8, 8);
            g.setColor(Color.WHITE);
            g.fillOval(rect.x - 2, rect.y - 2, rect.width + 4, rect.height + 4);
            g.setColor(new Color(80, 155, 229));
            g.fillOval(rect.x, rect.y, rect.width, rect.height);      
      }

      // Possibly returns a cursor based on the mouseevent
      public int getCursor(MouseEvent e) {
            // Creates a new rectangle 
            var rect = new Rectangle(0 + e.getComponent().getWidth() - 8, 0 + e.getComponent().getHeight() - 8, 8, 8);
            // Returns a cursor as an int when the rectangle contains the given mouseevent coordinates
            if (rect.contains(e.getPoint())) {
                  return Cursor.SE_RESIZE_CURSOR;
            }
            
            // Returns the default cursor
            return Cursor.MOVE_CURSOR;
      }
}