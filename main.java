import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;

import shapes.*;

public class main {

      private Rectangle res;

      public static void main(String[] args) {
            JFrame frame = new JFrame();

            JPanel board = new JPanel(null);
            frame.add(board);

            Rectangle rectangle1 = new Rectangle(100, 100, 50, 50);
            board.add(rectangle1);

            Rectangle rectangle2 = new Rectangle(400, 100, 50, 50);
            board.add(rectangle2);

            frame.addMouseListener(new MouseAdapter() {
                  @Override
                  public void mousePressed(MouseEvent me) {

                        frame.requestFocus();
                        rectangle1.repaint();
                        rectangle2.repaint();
                  }
            });

            frame.setSize(600, 600);
            frame.setLocationRelativeTo(null);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
      }
}