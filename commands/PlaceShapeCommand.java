package commands;

import shapes.*;
import UI.Invoker;
import UI.Board;

public class PlaceShapeCommand extends Order {
      private Invoker invoker;
      private Board board;
      
      public PlaceShapeCommand(Shape shape, Invoker invoker, Board board){
            this.shape = shape;
            this.invoker = invoker;
            this.board = board;
      }

      public void execute(){
            this.shape.place(this.invoker, this.board);
      }

      public void undo(){
            this.shape.remove();
      }

      public void redo(){
            this.shape.place(this.invoker, this.board);
      }
}