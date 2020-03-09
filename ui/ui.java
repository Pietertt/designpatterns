package ui;

import java.util.ArrayList;
import javax.swing.*;
import javax.swing.Timer;
import java.awt.event.*;

import shapes.rectangle;

public class ui extends JPanel {
      private ArrayList<rectangle> rects = new ArrayList<rectangle>();

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
      public void set(ArrayList<rectangle> rects){
            this.rects = rects;
      }

      // method used to update the rectangle arraylist in the main program
      public ArrayList<rectangle> get(){
            return this.rects;
      }

      // returns the current mode
      public int getMode(){
            return this.mode;
      }

      // sets the current mode to whatever mode is given
      public void setMode(int i){
            this.mode = i;
      }
}