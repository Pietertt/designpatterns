package shapes;

import javax.swing.*;

import java.util.ArrayList;

public class shape extends JPanel {
      public int x;
      public int y;
      public int width;
      public int height;

      public int[] color;

      // Moving op true betkeent dat shape kan worden verplaatst
      public boolean moving = false;
      public boolean selected = false;

      // Handle voor het resizen
      public ArrayList<handle> handles  = new ArrayList<handle>();

      public shape(int x, int y, int width, int height, int[] color){
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
            this.color = color;
      }

      // Controleer op de shape is geselecteerd dmv positie & grootte
      public boolean selected(int x, int y){
            for(int i = 0; i < this.width; i++){
                  if(x == this.x + i){
                        for(int j = 0; j < this.height; j++){
                              if(y == this.y + j){
                                    return true;
                              }
                        }
                  }
            }
            return false;
      }

      // clears all handels and creates a new one on the current position
      public void select(){
            this.handles.clear();
            this.handles.add(new handle(this.x + this.width, this.y + this.height, this.color));
      }
      
      // updates the width and height of the rectangle 
      public void resize(int offsetX, int offsetY, int x, int y){
            this.select();
            this.width = (x - offsetX) - this.x;
            this.height = (y - offsetY) - this.y;
      }    
}