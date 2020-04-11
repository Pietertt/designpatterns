import java.awt.*;
import javax.swing.*;
import java.io.*;
import javax.imageio.ImageIO;

import shapes.Rectangle;

class App {
      private Board board = new Board();
      private JPanel UI = new JPanel();

      private JButton rectangle = new JButton();
      private JButton ellipse = new JButton();

      private void generateUI() throws IOException {
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
      }
      
      public static void main(String[] args) throws IOException {
            App app = new App();
            JFrame frame = new JFrame();

            app.generateUI();

            frame.getContentPane().add(app.UI, BorderLayout.SOUTH);
            frame.getContentPane().add(app.board);

            frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
            frame.setSize(600, 600);
            frame.setVisible(true);

      }
}