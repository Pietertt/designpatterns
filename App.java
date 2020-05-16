import javax.swing.*;
import java.awt.BorderLayout;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.io.File;
import java.awt.event.*;

import UI.*;
import IO.*;
import shapes.BaseShape;
import strategies.*;
import java.awt.*;

public class App extends JFrame {

      public App(){
            JPanel UI = new JPanel();

            Layers layers = new Layers();
            Board board = new Board(this, layers);

            JButton rectangle = new JButton();
            rectangle.addActionListener(actionEvent -> {
                  board.created = true;
                  board.currentStrategy = board.rectangleStrategy;
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
                  board.currentStrategy = board.ellipseStrategy;
            });

            try {
                  ellipse.setIcon(new ImageIcon(ImageIO.read(new File("img/ellipse.png"))));
            } catch (IOException e) {

            }

            ellipse.setBorderPainted(false);
            ellipse.setFocusPainted(false);
            ellipse.setContentAreaFilled(false);

            JButton triangle = new JButton();

            triangle.addActionListener(actionEvent -> {
                  board.created = true;
                  board.currentStrategy = board.triangleStrategy;
            });

            try {
                  triangle.setIcon(new ImageIcon(ImageIO.read(new File("img/triangle.png"))));
            } catch (IOException e) {

            }

            triangle.setBorderPainted(false);
            triangle.setFocusPainted(false);
            triangle.setContentAreaFilled(false);

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

            JButton save = new JButton();
            //save.addActionListener(actionEvent -> board.saveFile());
            try {
                  save.setIcon(new ImageIcon(ImageIO.read(new File("img/save.png"))));
            } catch(IOException e) {
                  e.printStackTrace();
            }

            save.setBorderPainted(false);
            save.setFocusPainted(false);
            save.setContentAreaFilled(false);


            JButton group = new JButton();
            group.addActionListener(actionEvent -> board.group());
            try {
                  group.setIcon(new ImageIcon(ImageIO.read(new File("img/group.png"))));
            } catch(IOException e) {
                  e.printStackTrace();
            }

            group.setBorderPainted(false);
            group.setFocusPainted(false);
            group.setContentAreaFilled(false);

            UI.add(rectangle);
            UI.add(ellipse);
            UI.add(triangle);
            UI.add(undo);
            UI.add(redo);
            UI.add(save);
            UI.add(group);

            // // added the board and the UI to the frame
            getContentPane().add(UI, BorderLayout.SOUTH);
            getContentPane().add(layers, BorderLayout.EAST);
            getContentPane().add(board);

            setLocation(1000, 0);
            setSize(800, 600);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setVisible(true);

            board.init();

            fileIO.saveFile();
      }

      public static void main(String[] args) {
            App app = new App();
      }
}
