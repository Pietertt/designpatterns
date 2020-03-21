package commands;

import java.util.ArrayList;

import java.awt.event.*;

import shapes.*;

public class selectShapeCommand implements order {
      private rectangle rect;

      public selectShapeCommand(rectangle rect){
            this.rect = rect;
      }

      public void execute(){
            this.rect.select();
      }
}