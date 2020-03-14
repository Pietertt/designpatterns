package mouse;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import commands.placeRectangle;
import ui.ui;

import java.util.*;

import shapes.Rectangle;

public class mouse implements MouseListener {
      private boolean RectangleButton = false;
      private boolean EllipseButton = false;

      public ArrayList<Rectangle> rects = new ArrayList<Rectangle>();

      public mouse(ArrayList<Rectangle> rects, Graphics2D g) {
            this.rects = rects;
            for(int i = 0; i < this.rects.size(); i++) {
                  //placeRectangle placeRect = new placeRectangle(this.rects.get(i), g);
                  //this.undoStack.push(placeRect);
                  //placeRect.execute();
            }
      }

      @Override
      public void mouseClicked(MouseEvent mouseEvent) {

      }

      @Override
      public void mousePressed(MouseEvent mouseEvent) {

      }

      @Override
      public void mouseReleased(MouseEvent mouseEvent) {

      }

      @Override
      public void mouseEntered(MouseEvent mouseEvent) {

      }

      @Override
      public void mouseExited(MouseEvent mouseEvent) {

      }

      public void setRectangleButton(boolean rectangleButton) {
            RectangleButton = rectangleButton;
      }

      public void setEllipseButton(boolean ellipseButton) {
            EllipseButton = ellipseButton;
      }
}

