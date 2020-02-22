package mouse;

import java.util.ArrayList;

import java.util.*;
import java.awt.event.MouseEvent;

import shapes.*;

public class mouse extends TimerTask {
      public ArrayList<rectangle> rects;

      public mouse(ArrayList<rectangle> rects){
            this.rects = rects;
      }

      public void run(){
            for(int i = 0; i < rects.size(); i++){
                  if(rects.get(i).selected == true){
                        System.out.print("Still pressed");
                        System.out.print(rects.get(i).id);
                        System.out.print(" ");
                        System.out.println();
                  }
            }
      }
}

