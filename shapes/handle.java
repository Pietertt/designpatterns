package shapes;

public class handle extends Shape {
      public boolean selected = false;

      public handle(int x, int y, int[] rgb){
            super(x, y, 7, 7, rgb);
      }   

      public void update(int x, int y){
            this.x = x;
            this.y = y;
      }
}