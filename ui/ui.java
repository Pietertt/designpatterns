package ui;

import java.util.ArrayList;
import javax.swing.*;
import javax.swing.Timer;

import java.awt.event.*;

import shapes.*;

public class ui extends JPanel {

      public ui(JFrame frame){
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

            super.add(left);
            super.add(right);
            super.add(rect);
            super.add(ellipse);
      }
}