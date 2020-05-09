package UI;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

import shapes.BaseShape;

import java.awt.Dimension;
import java.awt.Graphics;
import java.io.File;
import java.io.IOException;
import java.awt.Color;

public class Item extends JLabel {

      public Item(BaseShape shape) {
            setBackground(Color.YELLOW);
            setText(shape.print().substring(0, 1).toUpperCase() + shape.print().substring(1));
            try {
                  ImageIcon image = new ImageIcon(ImageIO.read(new File("img/" + shape.print() + ".png")));
                  setIcon(image);
            } catch (IOException e) {
                  e.printStackTrace();
            }
      }
}