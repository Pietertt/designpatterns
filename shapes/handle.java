package shapes;

public class handle extends shape {
      public int height = 7;
      public int width = 7;
      public boolean selected = false;

      public handle(int x, int y, int[] rgb){
            super(x, y, rgb);
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

      public void update(int x, int y){
            this.x = x;
            this.y = y;
      }
}