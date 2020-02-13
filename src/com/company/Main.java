package com.company;

import com.sun.org.apache.xpath.internal.operations.Bool;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Main extends JFrame {
    private int x, y;
    JPanel jPanel;
    JPanel panel2;
    static JButton btnRectangle;
    static JButton btnEllipse;

    static Mouse mouse;

    static Boolean isEllipseBtnPressed = false;
    static Boolean isRectBtnPressed = false;

    /** Constructor **/
    public Main() {
        /** Create & Initialise Things **/
        //Rectangle rectangle = new Rectangle(0,0);
        x = 960; y = 960;
        this.jPanel = new JPanel();
        this.panel2 = new JPanel();
        panel2.setBackground(Color.blue);
        jPanel.setLayout(new BorderLayout());


        btnRectangle = new JButton("Rectangle");
        btnEllipse = new JButton("Ellipse");
        panel2.add(btnRectangle);
        panel2.add(btnEllipse);


        /** Add things to JFrame and JPanel **/
        //add(rectangle);
        getContentPane().add(jPanel);
        getContentPane().add(panel2, BorderLayout.NORTH);


        /** JFrame Properties **/
        setTitle("Editor");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setCursor(null);
        //setResizable(false);
        setSize(new Dimension(x,y));
        setLocationRelativeTo(null);

        mouse = new Mouse(jPanel);
        jPanel.addMouseListener(mouse);
        setVisible(true);

    }

    public static void main(String[] args) {
        Main main = new Main();
        //main.setCursor();

        class RectangleButtonListener implements ActionListener
        {

            @Override
            public void actionPerformed(ActionEvent event)
            {
                mouse.switchRectangle();
            }
        }

        class EllipseButtonListener implements ActionListener
        {

            @Override
            public void actionPerformed(ActionEvent event)
            {
                mouse.switchEllipse();
            }
        }

        btnRectangle.addActionListener(new RectangleButtonListener());
        btnEllipse.addActionListener(new EllipseButtonListener());
    }

    /** Set the Cursor **/
    public void setCursor() {
        setCursor (Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

}
