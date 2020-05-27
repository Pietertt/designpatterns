package App;

import javax.swing.*;
import java.awt.BorderLayout;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.io.File;

import UI.*;
import java.awt.*;

public class App extends JFrame {

      public App(){
              
      }

      public void clear(){
            // Removes all components from the app
            getContentPane().removeAll();
      }

      public void init(){
            JPanel UI = new JPanel();
            Layers layers = new Layers();
            Board board = new Board(this, layers);

            // Add rectangle button
            JButton rectangle = new JButton();
            rectangle.addActionListener(actionEvent -> {
                  // Sets the current strategy to a rectangle and the created variable to true
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

            // Add ellipse button
            JButton ellipse = new JButton();
            ellipse.addActionListener(actionEvent -> {
                  // Sets the current strategy to a ellipse and the created variable to true
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

            // Add triangle button
            JButton triangle = new JButton();
            triangle.addActionListener(actionEvent -> {
                  // Sets the current strategy to a triangle and the created variable to true
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

            // Redo button
            JButton redo = new JButton();
            redo.addActionListener(actionEvent -> board.invoker.redo());
            try {
                  redo.setIcon(new ImageIcon(ImageIO.read(new File("img/redo.png"))));
            } catch (IOException e) {

            }

            redo.setBorderPainted(false);
            redo.setFocusPainted(false);
            redo.setContentAreaFilled(false);

            // Export or save button
            JButton save = new JButton();
            save.addActionListener(actionEvent -> board.export());
            try {
                  save.setIcon(new ImageIcon(ImageIO.read(new File("img/save.png"))));
            } catch(IOException e) {
                  e.printStackTrace();
            }

            save.setBorderPainted(false);
            save.setFocusPainted(false);
            save.setContentAreaFilled(false);

            // Import or fetch button
            JButton fetch = new JButton();
            fetch.addActionListener(actionEvent -> board.fetch());
            try {
                  fetch.setIcon(new ImageIcon(ImageIO.read(new File("img/import.png"))));
            } catch(IOException e) {
                  e.printStackTrace();
            }

            fetch.setBorderPainted(false);
            fetch.setFocusPainted(false);
            fetch.setContentAreaFilled(false);

            // Group button
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

            // Adds all button to the UI
            UI.add(rectangle);
            UI.add(ellipse);
            UI.add(triangle);
            UI.add(undo);
            UI.add(redo);
            UI.add(save);
            UI.add(fetch);
            UI.add(group);
            UI.setBackground(Color.WHITE);

            // // added the board and the UI to the frame
            getContentPane().add(UI, BorderLayout.SOUTH);
            getContentPane().add(layers, BorderLayout.EAST);
            getContentPane().add(board);

            // Some styling
            setSize(800, 600);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setVisible(true);

            board.init();
      }

      public static void main(String[] args) {
            App app = new App();
            app.init();
      }
}
