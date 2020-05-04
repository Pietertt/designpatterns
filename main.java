import javax.swing.JFrame;
import javax.swing.JPanel;

import UI.Board;
import shapes.Rectangle;

public class main {
      public static void main(String[] args){
            JFrame frame = new JFrame();
            Board board = new Board(frame);

            frame.getContentPane().add(board);
            frame.setLocation(1000, 0);
            frame.setSize(600, 600);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);

            board.init();
      }
}