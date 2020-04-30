import javax.swing.*;
import java.awt.BorderLayout;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.io.File;

import UI.Board;
import strategies.*;

public class main {

      public static void main(String[] args) {
            JFrame frame = new JFrame();
            JPanel UI = new JPanel();

            Board board = new Board(frame);

            JButton rectangle = new JButton();
            rectangle.addActionListener(actionEvent -> {
                  board.created = true;
                  board.strategy = new PlaceRectangleStrategy(board.invoker, board);     
            });
            try {
                  rectangle.setIcon(new ImageIcon(ImageIO.read(new File("img/rectangle.png"))));
            } catch (IOException e) {

            }

            rectangle.setBorderPainted(false);
            rectangle.setFocusPainted(false);
            rectangle.setContentAreaFilled(false);

            JButton ellipse = new JButton();

            ellipse.addActionListener(actionEvent -> {
                  board.created = true;
                  board.strategy = new PlaceEllipseStrategy(board.invoker, board);
            });

            try {
                  ellipse.setIcon(new ImageIcon(ImageIO.read(new File("img/ellipse.png"))));
            } catch (IOException e) {

            }

            ellipse.setBorderPainted(false);
            ellipse.setFocusPainted(false);
            ellipse.setContentAreaFilled(false);

            // Undo button
            JButton undo = new JButton();
            undo.addActionListener(actionEvent -> board.invoker.undo());
            try {
                  undo.setIcon(new ImageIcon(ImageIO.read(new File("img/undo.png"))));
            } catch (IOException e) {

            }

            undo.setBorderPainted(false);
            undo.setFocusPainted(false);
            undo.setContentAreaFilled(false);

            // Undo button
            JButton redo = new JButton();
            redo.addActionListener(actionEvent -> board.invoker.redo());
            try {
                  redo.setIcon(new ImageIcon(ImageIO.read(new File("img/redo.png"))));
            } catch (IOException e) {

            }

            redo.setBorderPainted(false);
            redo.setFocusPainted(false);
            redo.setContentAreaFilled(false);

            UI.add(rectangle);
            UI.add(ellipse);
            UI.add(undo);
            UI.add(redo);

            // added the board and the UI to the frame
            frame.getContentPane().add(UI, BorderLayout.SOUTH);
            frame.getContentPane().add(board);

            frame.setLocation(1000, 0);
            frame.pack();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
      }
}