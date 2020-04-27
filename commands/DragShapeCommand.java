package commands;

import shapes.*;
import UI.Invoker;

public class DragShapeCommand extends Order {
      private java.awt.Rectangle bounds;

      public DragShapeCommand(Shape shape, java.awt.Rectangle bounds){
            this.shape = shape;
            this.bounds = bounds;
      }

      public void execute(){
            this.shape.drag(this.bounds);
            System.out.println("Executed");
      }

      public void undo(){
            this.shape.undoDrag();
            System.out.println("Undod");
      }

      public void redo(){
            this.shape.redoDrag();
            System.out.println("Redod");
      }
}