import java.util.*;
import java.awt.*;
import javax.swing.*;

import javax.swing.Timer;
import java.awt.event.*;

import shapes.rectangle;
import shapes.ellipse;

import ui.board;
import ui.ui;

import io.parser;

public class main {

  public static void main(String[] args) {
      JFrame frame = new JFrame();      

      ui ui = new ui();
      board board = new board(frame);
      
      // added the board and the UI to the frame
      frame.getContentPane().add(ui, BorderLayout.SOUTH);
      frame.getContentPane().add(board);
      
      // // some window settings
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setSize(board.width, board.height);
      frame.setLocation(board.offsetX, board.offsetY);
      frame.setVisible(true);


      // Undo button
      JButton undo = new JButton("UNDO");
      frame.add(undo, BorderLayout.EAST);
      undo.addActionListener(actionEvent -> board.undo());

      // Undo button
      JButton redo = new JButton("REDO");
      frame.add(redo, BorderLayout.WEST);
      redo.addActionListener(actionEvent -> board.redo());

  }
}