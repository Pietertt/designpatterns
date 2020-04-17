import java.awt.*;
import javax.swing.*;
import java.io.*;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import shapes.Rectangle;

class App {
      private ArrayList<Rectangle> shapes = new ArrayList<Rectangle>();
      private JFrame frame = new JFrame();
      private Board board = new Board(frame);
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

      private void generateBoard(){

            this.board.setFocusable(true);
            this.board.addMouseListener(this.board);
      }
      
      public static void main(String[] args) throws IOException {
            App app = new App();
            app.generateUI();
            app.generateBoard();

            app.frame.getContentPane().add(app.UI, BorderLayout.SOUTH);
            app.frame.getContentPane().add(app.board);

            app.frame.setDefaultCloseOperation(app.frame.EXIT_ON_CLOSE);
            app.frame.setSize(600, 600);
            app.frame.setVisible(true);

      }
}