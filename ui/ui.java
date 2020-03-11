package ui;

import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;

import java.awt.event.*;

import shapes.Rectangle;


public class ui extends JPanel {
      private ArrayList<Rectangle> rects = new ArrayList<Rectangle>();

      public int mode = 0;

      public ui(){
            JButton rect = new JButton();

            rect.setIcon(new javax.swing.ImageIcon(getClass().getResource("rectangle.png")));
            rect.setBorderPainted(false);
            rect.setFocusPainted(false);
            rect.setContentAreaFilled(false);

            rect.addActionListener(new ActionListener() { 
                  public void actionPerformed(ActionEvent e) {
                        setMode(1);
                  }
            });

            super.add(rect);
            //super.add(ellipse);
      }

      // method to update (or completely set) the rectangle arraylist from outside
      public void set(ArrayList<Rectangle> rects){
            this.rects = rects;
      }

      // method used to update the rectangle arraylist in the main program
      public ArrayList<Rectangle> get(){
            return this.rects;
      }

      public int getMode(){
            return this.mode;
      }

      public void setMode(int i){
            this.mode = i;
      }
}