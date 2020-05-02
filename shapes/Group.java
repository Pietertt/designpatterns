package shapes;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.util.ArrayList;
import java.util.Stack;
import commands.*;
import UI.*;
import shapes.*;
import visitor.Visitor;

public class Group /*extends BaseShape*/ {
      private ArrayList<BaseShape> children = new ArrayList<BaseShape>();

      public void addd(BaseShape graphic){
            this.children.add(graphic);
      }

      public void remove(Shape graphic){
            this.children.remove(graphic);
      }

      public void accept(Visitor visitor){

      }

      public void print(){
            
            // for(BaseShape shape : this.children){
            //       shape.print();
            // }
      }

      public void place(Invoker invoker, Board board) {
            
      }

      public void remove() {
            
      }

      public void select(MouseEvent e) {
            
      }

      public void deselect() {
            
      }

      public void drag(Location location) {
           
      }

      public void undoDrag() {
           
      }

      public void redoDrag() {
            
      }
}