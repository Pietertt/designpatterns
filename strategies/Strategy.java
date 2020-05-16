package strategies;

import commands.*;
import shapes.*;
import UI.Invoker;
import UI.Board;
import java.awt.Color;
import java.awt.Graphics;

public abstract class Strategy {
      public int[] gray = { 205, 205, 205 };
      public int[] blue = { 80, 155, 229 };
      public static Strategy strategy;

      public static Strategy getInstance(){
            return strategy;
      }

      public abstract String grammar(BaseShape shape);

      public abstract String toString();

      public abstract void execute(int x, int y, int width, int height, Graphics g, boolean selected);
}