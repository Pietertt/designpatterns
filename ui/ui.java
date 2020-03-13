package ui;

import java.util.ArrayList;
import javax.swing.*;
import javax.swing.Timer;
import java.awt.event.*;

import shapes.*;

public class ui extends JPanel {
      private shapes s;

      public int mode = 0;
      public String kind = "";

      public ui(){
            JButton rect = new JButton();
            JButton ellipse = new JButton();
            JButton left = new JButton();
            JButton right = new JButton();

            rect.setIcon(new javax.swing.ImageIcon(getClass().getResource("rectangle.png")));
            rect.setBorderPainted(false);
            rect.setFocusPainted(false);
            rect.setContentAreaFilled(false);

            ellipse.setIcon(new javax.swing.ImageIcon(getClass().getResource("ellipse.png")));
            ellipse.setBorderPainted(false);
            ellipse.setFocusPainted(false);
            ellipse.setContentAreaFilled(false);

            left.setIcon(new javax.swing.ImageIcon(getClass().getResource("left.png")));
            left.setBorderPainted(false);
            left.setFocusPainted(false);
            left.setContentAreaFilled(false);

            right.setIcon(new javax.swing.ImageIcon(getClass().getResource("right.png")));
            right.setBorderPainted(false);
            right.setFocusPainted(false);
            right.setContentAreaFilled(false);

            rect.addActionListener(new ActionListener() { 
                  public void actionPerformed(ActionEvent e){ 
                        setMode(1);
                        setKind("rectangle");
                  }
            });

            ellipse.addActionListener(new ActionListener(){
                  public void actionPerformed(ActionEvent e){
                        setMode(1);
                        setKind("ellipse");
                  }
            });

            super.add(left);
            super.add(right);
            super.add(rect);
            super.add(ellipse);
            //super.add(ellipse);
      }

      // method to update (or completely set) the rectangle arraylist from outside
      public void set(shapes s){
            this.s = s;
      }

      // method used to update the rectangle arraylist in the main program
      public shapes get(){
            return this.s;
      }

      // returns the current mode
      public int getMode(){
            return this.mode;
      }

      // sets the current mode to whatever mode is given
      public void setMode(int i){
            this.mode = i;
      }

      public String getKind(){
            return this.kind;
      }

      public void setKind(String kind){
            this.kind = kind;
      }
}