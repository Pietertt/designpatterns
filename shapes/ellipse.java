package shapes;

public class ellipse extends Shape {

      public int width;
      public int height;

      public ellipse(int x, int y, int width, int height, int[] rgb){
            super(x, y, rgb);
            this.width = width;
            this.height = height;
      }
}