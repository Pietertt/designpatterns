package strategies;

import commands.*;
import shapes.*;
import UI.Invoker;
import UI.Board;
import java.awt.Color;
import java.awt.Graphics;

public class Strategy {
      int[] gray = { 205, 205, 205 };
      int[] blue = { 80, 155, 229 };

      public Strategy(){

      }

      public void execute(int x, int y, int width, int height, Graphics g){
            System.out.println("executed");
      //       g.setColor(new Color(this.gray[0], this.gray[1], this.gray[2]));
      //       g.fillRect(this.x, this.y, this.width, this.height);
            g.setColor(new Color(this.blue[0], this.blue[1], this.blue[2]));
            g.fillRect(x, y, width, height);

      //       g.setColor(Color.WHITE);
      //       g.fillOval(this.x + this.width - 6, this.y + this.height - 6, 12, 12);

      //       g.setColor(new Color(this.blue[0], this.blue[1], this.blue[2]));
      //       g.fillOval(this.x + this.width - 4, this.y + this.height - 4, 8, 8);
      // } else {
      //       g.setColor(new Color(this.gray[0], this.gray[1], this.gray[2]));
      //       g.fillRect(this.x, this.y, this.width, this.height);

      }

}