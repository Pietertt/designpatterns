import javax.swing.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.*;

import shapes.*;
import shapes.Rectangle;

public class App {
      private JButton rectangle = new JButton();
      private JButton ellipse = new JButton();
      private JPanel UI = new JPanel();
      private JPanel board = new JPanel();

      public JPanel generateBoard() {
            this.board.add(new Rectangle());

            return this.board;
      }

      public JPanel generateUI() throws IOException {
            this.rectangle.setIcon(new ImageIcon(ImageIO.read(new File("img/rectangle.png"))));
            this.ellipse.setIcon(new ImageIcon(ImageIO.read(new File("img/ellipse.png"))));

            this.rectangle.setBorderPainted(false);
            this.rectangle.setFocusPainted(false);
            this.rectangle.setContentAreaFilled(false);

            this.ellipse.setBorderPainted(false);
            this.ellipse.setFocusPainted(false);
            this.ellipse.setContentAreaFilled(false);

            this.UI.add(this.rectangle);
            this.UI.add(this.ellipse);

            return this.UI;
      }


      public static void main(String[] args) throws IOException {
            JFrame frame = new JFrame();

            App app = new App();

            frame.getContentPane().add(app.generateBoard());
            frame.getContentPane().add(app.generateUI(), BorderLayout.SOUTH);

            frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
            frame.setSize(600, 600);
            frame.setVisible(true);
      }
}