import shapes.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

public class Board extends JPanel {
      public ArrayList<Rectangle> shapes = new ArrayList<Rectangle>();

      public void paintComponent(Graphics g) {
            for(Rectangle rect : shapes){
                  rect.draw(g);
            }      
      }
}