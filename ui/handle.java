package ui;

public class handle {

      public int x;
      public int y;
      public int height = 7;
      public int width = 7;
      public boolean selected = false;

      public handle(int x, int y){
            this.x = x;
            this.y = y;
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