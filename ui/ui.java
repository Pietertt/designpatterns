package ui;

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
import ui.board;

public class ui extends JPanel {
      private ArrayList<rectangle> rects = new ArrayList<rectangle>();

      public int mode = 0;

      public ui(){
            JButton rect = new JButton("Add rectangle");
            JButton ellipse = new JButton("Add ellipse");

            rect.addActionListener(new ActionListener() { 
                  public void actionPerformed(ActionEvent e) { 
                        setMode(1);
                  }
            });

            super.add(rect);
            super.add(ellipse);
      }

      // method to update (or completely set) the rectangle arraylist from outside
      public void set(ArrayList<rectangle> rects){
            this.rects = rects;
      }

      // method used to update the rectangle arraylist in the main program
      public ArrayList<rectangle> get(){
            return this.rects;
      }

      public int getMode(){
            return this.mode;
      }

      public void setMode(int i){
            this.mode = i;
      }
}