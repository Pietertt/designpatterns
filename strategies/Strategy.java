package strategies;

import commands.*;
import shapes.*;
import UI.Invoker;
import UI.Board;
import java.awt.Color;
import java.awt.Graphics;

public abstract class Strategy {
      protected int[] gray = { 205, 205, 205 };
      protected int[] blue = { 80, 155, 229 };

      public abstract void execute(int x, int y, int width, int height, Graphics g, boolean selected);
}