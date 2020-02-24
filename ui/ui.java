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
import board.board;

public class ui extends JPanel {
      private JFrame frame;

      public ui(JFrame frame){
            this.frame = frame;

            JButton rect = new JButton("Add rectangle");
            JButton ellipse = new JButton("Add ellipse");

            rect.addActionListener(new ActionListener() { 
                  public void actionPerformed(ActionEvent e) { 
                        System.out.println(8);
                  }
            });

            super.add(rect);
            super.add(ellipse);

      }
}