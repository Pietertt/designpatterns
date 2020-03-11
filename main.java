import java.util.ArrayList;

import java.awt.*;

import javax.swing.Timer;

import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.event.*;

import shapes.Rectangle;
import shapes.ellipse;
import ui.board;
import ui.ui;

public class main {

      private static ArrayList<Rectangle> rects = new ArrayList<Rectangle>();
      private static ArrayList<ellipse> ellipses = new ArrayList<ellipse>();

  public static void main(String[] args) {

      JFrame frame = new JFrame();
      frame.setLayout(new BorderLayout());



      for(int i = 0; i < 5; i++){
          rects.add(new Rectangle(50 + i * 75, 50, 50, 50, i, board.unselected, false, false));
      }

      // populates the ellipses array with the initial 5
      for(int i = 0; i < 5; i++){
          ellipses.add(new ellipse(50 + i * 75, 150, 50, 50, board.unselected));
      }

      ui ui = new ui();

      // populates the board with the first shapes
      board board = new board(frame, rects, ellipses, ui);

      JButton undo = new JButton("UNDO");
      //frame.add(undo, BorderLayout.EAST);



      undo.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent actionEvent) {
              board.undoDrawRectangle();
          }
      });

      frame.getContentPane().add(board);
      frame.getContentPane().add(ui, BorderLayout.WEST);

      JButton redo = new JButton("REDO");
      //frame.getContentPane().add(redo,BorderLayout.NORTH);

      redo.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent actionEvent) {
              board.redoDrawRectangle();
          }
      });
      
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