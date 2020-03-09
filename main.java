import java.util.*;
import java.awt.*;
import javax.swing.*;

import javax.swing.Timer;
import java.awt.event.*;

import shapes.rectangle;
import shapes.ellipse;
import ui.board;
import ui.ui;
import io.io;

public class main {

      private static ArrayList<rectangle> rects = new ArrayList<rectangle>();
      private static ArrayList<ellipse> ellipses = new ArrayList<ellipse>();

  public static void main(String[] args) {

      JFrame frame = new JFrame();

      io io = new io("test.pieter");
      System.out.println(io.read());

      // populates the rectangle array with the initial 5 rectangles
      for(int i = 0; i < 5; i++){
            rects.add(new rectangle(50 + i * 75, 50, 50, 50, i, board.GRAY));
      }

      // populates the ellipses array with the initial 5 ellipses 
      for(int i = 0; i < 5; i++){
            ellipses.add(new ellipse(50 + i * 75, 150, 50, 50, board.GRAY));
      }

      ui ui = new ui();

      // populates the board with the first shapes
      board board = new board(frame, rects, ellipses, ui);

      // added the board and the UI to the frame
      frame.getContentPane().add(board);
      frame.getContentPane().add(ui, BorderLayout.SOUTH);
      
      // // some window settings
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setSize(board.width, board.height);
      frame.setLocation(board.offsetX, board.offsetY);
      frame.setVisible(true);

      // // the timer which fires 100 times a second
      Timer timer = new Timer(10, new ActionListener() {
            public void actionPerformed(ActionEvent event){
                  // updates the main rectangle arraylist with any changes made in the board
                  rects = board.update();

                  // feeds the current rectangles arraylist to the ui
                  ui.set(rects);
                  
                  // retrieves the rectangle arraylist to catch any changes made by the ui
                  rects = ui.get();
            }
      });
      timer.setInitialDelay(0);
      timer.start();
  }
}