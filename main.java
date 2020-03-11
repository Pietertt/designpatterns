import java.util.*;
import java.awt.*;
import javax.swing.*;

import javax.swing.Timer;
import java.awt.event.*;

import shapes.rectangle;
import shapes.ellipse;
import shapes.shapes;
import ui.board;
import ui.ui;
import io.parser;

public class main {

      private static ArrayList<rectangle> rects = new ArrayList<rectangle>();
      private static ArrayList<ellipse> ellipses = new ArrayList<ellipse>();

      shapes s;

      public static shapes init(){
            parser p = new parser("test.pieter");
            ArrayList<String> commands =  p.read();
            shapes s = new shapes();
            for(int i = 0; i < commands.size(); i++){
                  String[] splitted = commands.get(i).split(" ");
                  if(splitted[0].equals("rectangle")){
                        s.rects.add(new rectangle(Integer.parseInt(splitted[1]), Integer.parseInt(splitted[2]), Integer.parseInt(splitted[3]), Integer.parseInt(splitted[4]), 4, board.GRAY));
                  }  
                  
                  if(splitted[0].equals("ellipse")){
                        s.ellipses.add(new ellipse(Integer.parseInt(splitted[1]), Integer.parseInt(splitted[2]), Integer.parseInt(splitted[3]), Integer.parseInt(splitted[4]), 4, board.GRAY));
                  }
            }

            return s;
      }

  public static void main(String[] args) {
      JFrame frame = new JFrame();      

      ui ui = new ui();

      // populates the board with the first shapes
      board board = new board(frame, init(), ui);

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