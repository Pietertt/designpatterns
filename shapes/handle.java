package shapes;

// Handle van een shape, als deze geselecteerd is kan de shape vergroot worden
public class handle extends shape {
      public boolean selected = false;

      public handle(int x, int y, int[] rgb){
            super(x, y, 7, 7, rgb);
      }   

      public void update(int x, int y){
            this.x = x;
            this.y = y;
      }
}