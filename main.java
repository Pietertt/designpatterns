import java.util.ArrayList;

import java.util.*;
import java.awt.*;

import javax.swing.Timer;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.event.*;
import java.awt.geom.Ellipse2D;

import shapes.rectangle;
import shapes.ellipse;
import board.board;
import ui.ui;

public class main {

  public static void main(String[] args) {
      board main = new board();
      ui ui = new ui();
      main.frame = new JFrame();
      main.frame.add(main);
      //main.frame.add(ui);
      

      // populates the field with the initial 5 rectangles
      for(int i = 0; i < 5; i++){
            main.rects.add(new rectangle(50 + i * 75, 50, 50, 50, i, main.unselected));
      }

      // populates the field with the initial 5 ellipses 
      for(int i = 0; i < 5; i++){
            main.ellipses.add(new ellipse(50 + i * 75, 150, 50, 50, main.unselected));
      }
      
      // some window settings
      main.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      main.frame.setSize(main.width, main.height);
      main.frame.setLocation(main.offsetX, main.offsetY);
      main.frame.setVisible(true);

      // the timer which fires 1000 times a second
      Timer timer = new Timer(10, new ActionListener() {
            public void actionPerformed(ActionEvent event){
                  main.update();
            }
      });
      timer.setInitialDelay(0);
      timer.start();
  }
}