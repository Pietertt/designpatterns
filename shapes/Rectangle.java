package shapes;

import java.awt.*;

public class Rectangle extends BaseShape {

      public Rectangle(int x, int y, int width, int height) {
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
            //setLayout(new FlowLayout());  
      }

//       public void print(){
//             System.out.println("rectangle");
//       }

//       public void accept(Visitor visitor){
//             // visitor.visitRectangle(this);
//       }

      public void place(){
            this.drawed = true;
            repaint();
      }

      public void remove(){
            this.drawed = false;
            repaint();
      }

      public void drag(Location location){
            this.redoStack.clear();
            this.undoStack.add(location);
            this.dragging = true;
            this.start = new Location(location.x, location.y, location.width, location.height);
            repaint();
      }

      public void resize(Location location){
            this.redoStack.clear();
            this.undoStack.add(location);
            this.resizing = true;
            this.start = new Location(location.x, location.y, location.width, location.height);
            repaint();
      }

     public void undoDrag() {
            Location location = this.undoStack.pop();
            this.redoStack.add(location);
            this.x = location.x;
            this.y = location.y;
            this.width = location.width;
            this.height = location.height;
            repaint();
      }

      public void redoDrag() {
            if (this.redoStack.size() > 0) {
                  Location location = this.redoStack.pop();
                  this.undoStack.add(location);
                  this.x = location.x;
                  this.y = location.y;
                  this.width = location.width;
                  this.height = location.height;
                  repaint();
            }
      }

      public boolean getHandleIfSelected(int x, int y){
            for(int i = this.x + this.width - 6; i < this.x + this.width + 6; i++){
                  for(int j = this.y + this.height - 6; j < this.y + this.height + 6; j++){
                        if(i == x){
                              if(j == y){
                                    return true;
                              }
                        }
                  }
            }
            return false;
      }

      @Override
      public void paintComponent(Graphics g) {
            super.paintComponent(g);
            if(this.drawed){
                  if(this.selected){
                        g.setColor(new Color(this.gray[0], this.gray[1], this.gray[2]));
                        g.fillRect(this.x, this.y, this.width, this.height);
                        g.setColor(new Color(this.blue[0], this.blue[1], this.blue[2]));
                        g.drawRect(this.x, this.y, this.width, this.height);
      
                        g.setColor(Color.WHITE);
                        g.fillOval(this.x + this.width - 6, this.y + this.height - 6, 12, 12);
      
                        g.setColor(new Color(this.blue[0], this.blue[1], this.blue[2]));
                        g.fillOval(this.x + this.width - 4, this.y + this.height - 4, 8, 8);
                  } else {
                        g.setColor(new Color(this.gray[0], this.gray[1], this.gray[2]));
                        g.fillRect(this.x, this.y, this.width, this.height);
                  }
            }
      }
}