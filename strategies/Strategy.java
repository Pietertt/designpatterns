package strategies;

import commands.*;
import shapes.*;
import UI.Invoker;
import UI.Board;
import java.awt.Color;
import java.awt.Graphics;

public class Strategy {
      protected int[] gray = { 205, 205, 205 };
      protected int[] blue = { 80, 155, 229 };

      public Strategy(){

      }

      public void execute(int x, int y, int width, int height, Graphics g, boolean selected){
            if(selected){
                  g.setColor(new Color(gray[0], gray[1], gray[2]));
                  g.fillRect(x, y, width, height);
                  g.setColor(new Color(blue[0], blue[1], blue[2]));
                  g.drawRect(x, y, width, height);

                  g.setColor(Color.WHITE);
                  g.fillOval(x + width - 6, y + height - 6, 12, 12);

                  g.setColor(new Color(blue[0], blue[1], blue[2]));
                  g.fillOval(x + width - 4, y + height - 4, 8, 8);
            }
            else {
                  g.setColor(new Color(gray[0], gray[1], gray[2]));
                  g.fillRect(x, y, width, height);
            }
      }
}