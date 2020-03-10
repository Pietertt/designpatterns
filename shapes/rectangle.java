package shapes;

public class rectangle extends shape {

      public int id;
      public int[] color;

      public rectangle(int x, int y, int width, int height, int id, int[] rgb){
            super(x, y, width, height, rgb);
            this.width = width;
            this.height = height;
            this.id = id;
            this.color = rgb;
      }

      // updates the width and height of the rectangle 
      public void resize(int offsetX, int offsetY, int x, int y){
            this.select();
            this.width = (x - offsetX) - this.x;
            this.height = (y - offsetY) - this.y;
      }     
}