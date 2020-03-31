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

      private static ArrayList<Command> commands = new ArrayList<Command>();
      public static void main(String[] args) {
            JFrame frame = new JFrame();     

            ui ui = new ui(frame);
            board board = new board(frame);

            
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