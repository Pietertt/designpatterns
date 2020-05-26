package UI;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.*;
import java.util.ArrayList;

import shapes.BaseShape;

// Het updaten van het board
public class Layers extends JPanel {
      public JFrame frame;
      public Board board;

      public Layers(){
            setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 10));
            setLayout(new BoxLayout(this, BoxLayout.Y_AXIS)); 
      }

      public void update(ArrayList<BaseShape> shapes){
            removeAll();
            for(BaseShape shape : shapes){
                  // add(new Item(shape));
                  // add(Box.createRigidArea(new Dimension(10, 10)));
                  shape.print(this);
            }
            revalidate();
            repaint();
      }
}