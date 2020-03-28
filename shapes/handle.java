package shapes;

public class handle extends shape {
      public boolean selected = false;

      public handle(int x, int y){
            super(x, y, 7, 7);
      }   

      public void update(int x, int y){
            this.x = x;
            this.y = y;
      }
}