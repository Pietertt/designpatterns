import javax.swing.JFrame;
import javax.swing.JPanel;

import UI.Board;
import shapes.Rectangle;

public class main {
      private JFrame frame;
      private Board board;

      public void init(){
            this.frame = new JFrame();
            this.board = new Board(this.frame);

            this.frame.getContentPane().add(this.board);
            this.frame.setLocation(1000, 0);
            this.frame.setSize(600, 600);
            this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.frame.setVisible(true);

            Rectangle rect = new Rectangle(100, 100, 100, 100);
            this.frame.add(rect);
            this.frame.revalidate();
            this.frame.repaint();
      }

      public static void main(String[] args){
            main main = new main();
            main.init();
      }
}