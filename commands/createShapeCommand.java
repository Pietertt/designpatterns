package commands;

import shapes.Shape;

import java.awt.*;
import java.util.*;

public class createShapeCommand implements Command {
      private Shape shape;

      public createShapeCommand(Shape shape){
            this.shape = shape;
      }     

      @Override
      public void execute(){
            this.shape.place();
      }

      public Shape get(){
            return this.shape;
      }

      public void undo(){

      }

      public void redo(){
            
      }
}