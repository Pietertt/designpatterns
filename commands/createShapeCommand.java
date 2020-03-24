package commands;

import shapes.Shape;

import java.awt.*;
import java.util.*;

public class createShapeCommand implements Order {
      private Shape shape;
      private ArrayList<Shape> shapes;

      public createShapeCommand(Shape shape, ArrayList<Shape> shapes){
            this.shape = shape;
            this.shapes = shapes;
      }

      @Override
      public void execute(){
            this.shape.draw();
      }
}