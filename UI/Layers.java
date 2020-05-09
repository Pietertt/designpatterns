package UI;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.GridLayout;

import shapes.BaseShape;

public class Layers extends JPanel {
      public JFrame frame;
      public Board board;

      public Layers(JFrame frame, Board board){
            this.frame = frame;
            this.board = board;
            GridLayout layout = new GridLayout(0, 1, 0, 0);
            setLayout(layout);
            update();
      }

      public void update(){
            for(int i = 0; i < 10; i++){
                  add(new Item("Button 1"));
            }
      }
}