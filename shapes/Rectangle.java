package shapes;

import ui.ui;
import ui.board;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Stack;

public class Rectangle extends JComponent implements MouseMotionListener {
    public boolean selected = false;
    public boolean pressed = false;
    public int id;

    public static int[] GRAY = { 213, 213, 213 };
    public static int[] BLUE = { 76, 153, 229 };


    // UNDO & REDO states
    private Stack<Rectangle> undoRectangleState;
    private Stack<Rectangle> redoRectangleState;
    // Is this rectangle paint or no? (Undo,Redo)
    private boolean painted;

    public JFrame frame;

    //MODUS
    public int mode = 0;

//    public boolean dragging = false;
//    public boolean added = false;

    public int x,y;
    public int[] rgb;

    public static int offsetX = 200;
    public static int offsetY = 200;
    public int width = 500;
    public int height = 500;

    // undo redo
    private Boolean undo = false;

    private Boolean redo = false;

    public int[] color;
    private board board;

    public Rectangle(int x, int y, int width, int height, int id, int[] rgb){
        //super(x, y, rgb);
        this.x = x;
        this.y = y;
        this.rgb = rgb;
        this.width = width;
        this.height = height;
        this.id = id;
        this.undoRectangleState = new Stack<>();
        this.redoRectangleState = new Stack<>();
        this.painted = true;
//        this.added = added;
//        this.dragging = dragging;

        //this.setSize(this.width, this.height);
        //this.setSize(this.width, this.height);
        //super.setFocusable(true);
        //addMouseListener(this);
        //this.selected = selected;
        //this.mode = mode;
    }

    public void setBoard(board board) {
        this.board = board;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        //g2d = (Graphics2D) g;



        if(painted) {
            g2d.setColor(new Color(rgb[0], rgb[1], rgb[2]));
            g2d.fillRect(x, y, width, height);
        }
    }



    public Rectangle update(int mode) {

        //-----------------------------------------------------------------------------
        //                Responsible for dragging rectangles around
        //-----------------------------------------------------------------------------


        //this.mode = ui.getMode();

        // decides what to execute based on the current mode
        // 0 ------------> default, allows dragging of rectangles
        // 1 ------------> a rectangle is being created
        switch(mode){
            case 0:
                    if(selected){
                        Point a = MouseInfo.getPointerInfo().getLocation();

                        int mouseX = (int)MouseInfo.getPointerInfo().getLocation().getX();
                        int mouseY = (int)MouseInfo.getPointerInfo().getLocation().getY();

//                        this.x = mouseX - offsetX - this.width /2;
//                        this.y = mouseY - offsetY - this.height;
//
                        // the absolute X and Y values of the cursor
                        int xAbsolute = (int)a.getX();
                        int yAbsolute = (int)a.getY();

                        // determines the current mouse position regarding the rectangle X and Y values
                        int xRelative = (int)a.getX() - offsetX - this.width / 2;
                        int yRelative = (int)a.getY() - offsetY - this.height;

                        int xRect = xAbsolute - this.width / 2;
                        int yRect = yAbsolute - this.height;

                        // updates the current selected rectangle to the current mouse position
                        if(xRect > offsetX){ // the X position of the rectangle must be bigger than the window X offset
                            if(xRect < (offsetX + 500 - this.width)){ // the X position of the rectangle must be bigger than the X offset of the screen + the height of the screen + the width of the rectangle / 2
                                if(yRect > offsetY){ // the Y position of the rectangle must be bigger than the window Y offset
                                    if(yRect < (offsetY + 500 - this.height)){ // the Y position of the rectangle must be bigger than the offset of the window + the height of the window - the height of the rectangle / 2
                                        this.x = xRelative;
                                        this.y = yRelative;
                                    } else {
                                        this.y = 500 - this.height;
                                    }
                                } else {
                                    this.y = 0;
                                }
                            } else {
                                this.x = 500 - this.width;
                            }
                        } else {
                            this.x = 0;
                        }
                    }
                break;
            case 1:
                if(board.dragging == true){
                    //Rectangle rect = this;
                    Point a = MouseInfo.getPointerInfo().getLocation();
                    int x = (int)a.getX();
                    int y = (int)a.getY();

                    if((x /*- offsetX*/) > this.x){
                        this.width = (x - offsetX) - this.x;
                        this.height = (y - offsetY) - this.y;
                    }
                }
                break;
            default:
                break;
        }

        // updates the frame which is given as a parameter
        this.repaint();
        // returns the rectangle arraylist for further use to the main program
        return this;
    }

    public void undoButtonClicked(Rectangle rectangle, boolean painted)
    {
        System.out.println("Undo in rectangle");
        this.painted = painted;
        //Rectangle rectangle = this.undoRectangleState.pop();
        //this.redoRectangleState.add(rectangle);
        this.x = rectangle.x;
        this.y = rectangle.y;
        this.width = rectangle.width;
        this.height = rectangle.height;
        this.id = id;
        this.repaint();
    }

    public void redoButtonClicked(Rectangle rectangle, boolean painted)
    {
        System.out.println("Redo in rectangle");
        this.painted = painted;
        //Rectangle rectangle = this.undoRectangleState.pop();
        //this.redoRectangleState.add(rectangle);
        this.x = rectangle.x;
        this.y = rectangle.y;
        this.width = rectangle.width;
        this.height = rectangle.height;
        this.id = id;
        this.repaint();
    }


    public void pressedHandler(MouseEvent e) {
        // the absolute X and Y values of the cursor
        int x = e.getX();
        int y = e.getY();
        this.pressed = false;
        for(int j = 0; j < this.width; j++){
            if(x == this.x + j){
                for(int k = 0; k < this.height; k++){
                    if(y == this.y + k){
                        this.pressed = true;
                    }
                }
            }
        }
    }

    public void selectionHandler(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
//


        // looping through the width of the current rectangle
        for (int j = 0; j < this.width; j++) {
            // checking if the current mouse.x is within the range of the rectangle width
            if (x == this.x + j) {
                // looping through the height of the rectangle
                for (int k = 0; k < this.height; k++) {
                    // checking if the current mouse.y is within the range of the rectangle height
                    if (y == this.y + k) {
                        this.selected = true;
                    }
                }
            }
        }
    }

    @Override
    public void mouseDragged(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseMoved(MouseEvent mouseEvent) {

    }
}
