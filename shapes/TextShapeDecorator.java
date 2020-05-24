package shapes;

import javax.swing.*;

import UI.Layers;
import strategies.Strategy;
import visitor.Visitor;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

public class TextShapeDecorator extends ShapeDecorator {
      public String bottom = "";
      public String top = "";
      public String left = "";
      public String right = "";

      public TextShapeDecorator(BaseShape decoratedShape, String bottom, String top, String left, String right) {
            super(decoratedShape);
            this.bottom = bottom;
            this.top = top;
            this.left = left;
            this.right = right;
      }

      public TextShapeDecorator() {
      }

      @Override
      public void setStrategy(Strategy strategy) {

      }

      public String toString(int indent) {
            String string = "";
            for(int i = 0; i < indent; i++){
                  string += "\t";
            }

            //System.out.println(getComponents());
            if(!top.equals(""))
                  string += "ornament top" + " " + this.top + System.lineSeparator();
            if(!bottom.equals(""))
                  string += "ornament bottom" + " " + this.bottom + System.lineSeparator();
            if(!left.equals(""))
                  string += "ornament left" + " " + this.left + System.lineSeparator();
            if(!right.equals(""))
                  string += "ornament right" + " " + this.right + System.lineSeparator();

            //string += decoratedShape.toString(0);

            return string;
      }

      @Override
      public boolean getHandleIfSelected(int x, int y) {
            return false;
      }

      public void accept(Visitor visitor){
            visitor.visit(this);
      }

      public void setDecoratedShape(BaseShape shape) {
            this.decoratedShape = shape;
      }

      public BaseShape getDecoratedShape() {
            return decoratedShape;
      }


      public void setBottom(String bottom) {
            this.bottom = bottom.substring(1, bottom.length() - 1);
      }

      public void setTop(String top) {
            this.top = top.substring(1, top.length() - 1);
      }

      public void setLeft(String left) {
            this.left = left.substring(1, left.length() - 1);
      }

      public void setRight(String right) {
            this.right = right.substring(1, right.length() - 1);
      }

      public ArrayList<String> getOrnaments() {
            ArrayList<String> ornaments = new ArrayList<>();
            ornaments.add(bottom);
            ornaments.add(top);
            ornaments.add(left);
            ornaments.add(right);

            return ornaments;
      }

      @Override
      public void place() {
            decoratedShape.place();
      }

      public void resize(Location location){

      }

      public void drag(Location location){
       
      }

      @Override
      public void clear() {

      }

      @Override
      protected void paintComponent(Graphics g) {
            super.paintComponent(g);


            if (decoratedShape.selected) {

                  Graphics2D g2d = (Graphics2D) g;

                  // Ornament at bottom of component
                  g2d.drawString(bottom, decoratedShape.x, decoratedShape.y + decoratedShape.height + 10);

                  // Ornament at top of component
                  g2d.drawString(top, decoratedShape.x, decoratedShape.y - 2);

                  // Ornament at left side of component
                  verticalLeft(g2d, left);

                  // Ornament at right side of component
                  verticalRight(g2d, right);
            }
      }

      private void verticalLeft(Graphics2D g2d, String str) {
            int position = decoratedShape.y + 5;
            for (int i = 0; i < str.length(); i++) {
                  System.out.println();
                  g2d.setFont(new Font("TimesRoman", Font.PLAIN, 9));
                  g2d.drawString(String.valueOf(str.charAt(i)), decoratedShape.x - 10, position);
                  position += 7;
            }
      }

      private void verticalRight(Graphics2D g2d, String str) {
            int position = decoratedShape.y + 5;
            for (int i = 0; i < str.length(); i++) {
                  System.out.println();
                  g2d.setFont(new Font("TimesRoman", Font.PLAIN, 9));
                  g2d.drawString(String.valueOf(str.charAt(i)), decoratedShape.x + decoratedShape.width + 5, position);
                  position += 7;
            }
      }

      public void remove() {
            decoratedShape.remove();
      }

      public void select(MouseEvent e) {
            decoratedShape.select(e);
      }

      public void deselect(MouseEvent e) {
            decoratedShape.deselect(e);
      }

      @Override
      public boolean getIfSelected(int x, int y) {
            return false;
      }

      public void dragCommand(Location location) {
            decoratedShape.dragCommand(location);
      }

      public void resizeCommand(Location location) {
            decoratedShape.resizeCommand(location);
      }

      public void undoDrag() {
            decoratedShape.undoDrag();
      }

      public void redoDrag() {
            decoratedShape.redoDrag();
      }

      public void print(Layers layers){

      }

      @Override
      public int getX() {
            return decoratedShape.getX();
      }

      @Override
      public int getY() {
            return decoratedShape.getY();
      }

      @Override
      public int getWidth() {
            return decoratedShape.getWidth();
      }

      @Override
      public int getHeight() {
            return decoratedShape.getHeight();
      }
}
