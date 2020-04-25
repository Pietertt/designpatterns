import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;

import shapes.Rectangle;
import UI.Board;

public class main {
      
      public static void main(String[] args) {
            JFrame frame = new JFrame();

            Board board = new Board();
            frame.add(board);

            frame.setLocationRelativeTo(null);
            frame.pack();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
      }
}