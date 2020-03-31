import java.util.*;
import java.awt.*;
import javax.swing.*;

import javax.swing.Timer;

import commands.Command;

import java.awt.event.*;

import shapes.Shape;
import shapes.Rectangle;

import ui.board;
import ui.ui;

import io.parser;
import java.util.ArrayList;

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

      // // the timer which fires 100 times a second
      Timer timer = new Timer(10, new ActionListener() {
            public void actionPerformed(ActionEvent event){
                 board.update();
            }
      });
      timer.setInitialDelay(0);
      timer.start();
  }
}