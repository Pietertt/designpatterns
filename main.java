import java.util.*;
import java.awt.*;
import javax.swing.*;

import javax.swing.Timer;
import java.awt.event.*;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.io.File;

import shapes.rectangle;
import shapes.ellipse;

import ui.board;
import ui.ui;

import io.parser;

public class main {

  public static void main(String[] args) {
      JFrame frame = new JFrame();      

      JPanel ui = new JPanel();
      board board = new board(frame);



      // Undo button
      JButton undo = new JButton();
      undo.addActionListener(actionEvent -> board.undo());
      try {
            undo.setIcon(new ImageIcon(ImageIO.read(new File("img/undo.png"))));
      } catch(IOException e){

      }

      undo.setBorderPainted(false);
      undo.setFocusPainted(false);
      undo.setContentAreaFilled(false);

      // Undo button
      JButton redo = new JButton();
      redo.addActionListener(actionEvent -> board.redo());
      try {
            redo.setIcon(new ImageIcon(ImageIO.read(new File("img/redo.png"))));
      } catch(IOException e){

      }

      redo.setBorderPainted(false);
      redo.setFocusPainted(false);
      redo.setContentAreaFilled(false);

      ui.add(undo);
      ui.add(redo);

      // added the board and the UI to the frame
      frame.getContentPane().add(ui, BorderLayout.SOUTH);
      frame.getContentPane().add(board);
      
      // // some window settings
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setSize(board.width, board.height);
      frame.setLocation(board.offsetX, board.offsetY);
      frame.setVisible(true);

  }
}