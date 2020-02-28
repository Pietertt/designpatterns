package shapes;

import javax.swing.*;

import java.awt.*;

import ui.handle;

import java.util.ArrayList;

public class rectangle extends shape {

      public int width;
      public int height;
      public int id;

      public ArrayList<handle> handles  = new ArrayList<handle>();

      public rectangle(int x, int y, int width, int height, int id, int[] rgb){
            super(x, y, rgb);
            this.width = width;
            this.height = height;
            this.id = id;
      }

      // clears all handels and creates a new one on the current position
      public void select(){
            this.handles.clear();
            this.handles.add(new handle(this.x + this.width, this.y + this.height));
            this.handles.add(new handle(this.x - 7, this.y - 7));
            this.handles.add(new handle(this.x + this.width, this.y - 7));
            this.handles.add(new handle(this.x - 7, this.y + this.height));
      }

      public boolean mouse(int x, int y){
            for(int i = 0; i < this.width; i++){
                  if(x == this.x + i){
                        for(int j = 0; j < this.height; j++){
                              if(y == this.height + j){
                                    return true;
                              } 
                        }
                  }
            }
            return false;
      }

      // updates the width and height of the rectangle 
      public void resize(int offsetX, int offsetY, int x, int y){
            this.select();
            this.width = (x - offsetX) - this.x;
            this.height = (y - offsetY) - this.y;
      }     
}