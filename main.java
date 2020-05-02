import javax.swing.*;
import javax.swing.border.Border;

import java.awt.BorderLayout;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.io.File;

import UI.Board;
import UI.Layers;
import strategies.*;
import shapes.*;

public class main {

      public static void main(String[] args) {
            JFrame frame = new JFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            Border line = BorderFactory.createTitledBorder("Line");
            JPanel board = new JPanel();
            java.awt.LayoutManager layout = new java.awt.FlowLayout();  
            board.setLayout(layout);

            BaseShape shape = new BaseShape(100, 100, 100, 100);

            shape.setBorder(line);

            board.add(shape);


            // JPanel layers = new Layers();

            //Board board = new Board(frame);

           

            // added the board and the UI to the frame
            //frame.getContentPane().add(layers, BorderLayout.EAST);
            // frame.getContentPane().add(board);
            // frame.getContentPane().add(UI, BorderLayout.SOUTH);
            frame.getContentPane().add(board);

            //frame.setLocation(1000, 0);
            //frame.pack();
            frame.setSize(600, 600);
            frame.setLocation(1000, 0);
            frame.setVisible(true);

            //board.init();
      }
}