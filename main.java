import java.util.*;
import javax.swing.*;

import javax.swing.Timer;
import java.awt.event.*;
import java.awt.BorderLayout;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.io.File;

import io.fileIO;
import shapes.*;

import ui.board;
import commands.*;
import strategies.*;

import io.parser;

public class main {

  public static void main(String[] args) {
      JFrame frame = new JFrame();      

      JPanel ui = new JPanel();
      board board = new board(frame);

      JButton rectangle = new JButton();
      rectangle.addActionListener(actionEvent -> {
            board.created = true;
            board.strategy = new PlaceRectangleStrategy(board.commandInvoker);
      });
      try {
            rectangle.setIcon(new ImageIcon(ImageIO.read(new File("img/rectangle.png"))));
      } catch(IOException e){

      }

      rectangle.setBorderPainted(false);
      rectangle.setFocusPainted(false);
      rectangle.setContentAreaFilled(false);

      JButton ellipse = new JButton();

      ellipse.addActionListener(actionEvent -> { 
            board.created = true;
            board.strategy = new PlaceEllipseStrategy(board.commandInvoker);
      });

      try {
            ellipse.setIcon(new ImageIcon(ImageIO.read(new File("img/ellipse.png"))));
      } catch(IOException e){

      }

      ellipse.setBorderPainted(false);
      ellipse.setFocusPainted(false);
      ellipse.setContentAreaFilled(false);

      // Undo button
      JButton undo = new JButton();
      undo.addActionListener(actionEvent -> board.commandInvoker.undo());
      try {
            undo.setIcon(new ImageIcon(ImageIO.read(new File("img/undo.png"))));
      } catch(IOException e){

      }

      undo.setBorderPainted(false);
      undo.setFocusPainted(false);
      undo.setContentAreaFilled(false);

      // Undo button
      JButton redo = new JButton();
      redo.addActionListener(actionEvent -> board.commandInvoker.redo());
      try {
            redo.setIcon(new ImageIcon(ImageIO.read(new File("img/redo.png"))));
      } catch(IOException e){

      }

      redo.setBorderPainted(false);
      redo.setFocusPainted(false);
      redo.setContentAreaFilled(false);

      JButton save = new JButton();
      save.addActionListener(actionEvent -> board.saveFile());
      try {
          save.setIcon(new ImageIcon(ImageIO.read(new File("img/save.png"))));
      } catch(IOException e) {
          e.printStackTrace();
      }

      ui.add(rectangle);
      ui.add(ellipse);
      ui.add(undo);
      ui.add(redo);
      ui.add(save);

      // added the board and the UI to the frame
      frame.getContentPane().add(ui, BorderLayout.SOUTH);
      frame.getContentPane().add(board);
      
      // // some window settings
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setSize(board.width, board.height);
      frame.setLocation(board.offsetX, board.offsetY);
      frame.setVisible(true);

      for(int i = 0; i < 5; i++){
            shape rect = new rectangle(50 + i * 100, 100, 50, 50, 1);
            placeShapeCommand place = new placeShapeCommand(rect);

            board.commandInvoker.execute(place);
            frame.add(place.getShape());
            frame.revalidate();
            frame.repaint();

            board.shapes.add(place.getShape());

            board.addMouseMotionListener(place.getShape());
      }

      for(int i = 0; i < 5; i++){
            shape ell = new ellipse(50 + i * 100, 300, 50, 50, 1);
            placeShapeCommand place = new placeShapeCommand(ell);

            board.commandInvoker.execute(place);
            frame.add(place.getShape());
            frame.revalidate();
            frame.repaint();

            board.shapes.add(place.getShape());

            board.addMouseMotionListener(place.getShape());
      }

  }
}