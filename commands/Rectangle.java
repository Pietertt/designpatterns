package commands;

import shapes.rectangle;
import ui.board;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class Rectangle extends Shape /*implements MouseListener*/ {
    public boolean selected = false;
    public boolean pressed = false;
    public int id;

    public ArrayList<Rectangle> rects = new ArrayList<Rectangle>();

    public JFrame frame;

    //MODUS
    //private int mode;

    public boolean dragging = false;
    public boolean added = false;

    public int x,y;
    public int[] rgb;

    public static int offsetX = 200;
    public static int offsetY = 200;
    public int width = 500;
    public int height = 500;

    // undo redo
    private Boolean undo = false;
    private Boolean redo = false;

    private JPanel board;

    public Rectangle(int x, int y, int width, int height, int id, int[] rgb){
        super(x, y, rgb);
        this.x = x;
        this.y = y;
        this.rgb = rgb;
        this.width = width;
        this.height = height;
        this.id = id;
        //this.board = board;
        //this.mode = mode;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        //Graphics2D g2d = (Graphics2D) g;
        //this.g2d = (Graphics2D) g;

        if(!undo) {
            g.setColor(new Color(rgb[0], rgb[1], rgb[2]));
            g.fillRect(x, y, width, height);
        }
    }

    public void undoButtonClicked()
    {
        System.out.println("Undo");
        undo = true;
        this.repaint();
    }

    public void redoButtonClicked()
    {
        System.out.println("Redo");
        undo = false;
        this.repaint();
    }
}
