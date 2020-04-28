package io;

import java.io.*;
import java.util.Scanner;

import UI.*;
import strategies.*;

import java.util.*;

import shapes.Rectangle;
import shapes.Shape;
import shapes.Ellipse;

public class Parser {

      public Parser(){
      }

      public ArrayList<String> read(String filename){
            ArrayList<String> lines = new ArrayList<String>();
            try {
                  File file = new File(filename);
                  Scanner reader = new Scanner(file);
                  while(reader.hasNextLine()){
                        lines.add(reader.nextLine());
                  }
                  reader.close();

                  return lines;
            } catch(FileNotFoundException e){
                  System.out.println("An error occured");
                  return lines;
            }
      }

      public ArrayList<Shape> get(ArrayList<String> lines){
            ArrayList<Shape> shapes = new ArrayList<Shape>();
            for(int i = 0; i < lines.size(); i++){
                  String[] splitted = lines.get(i).split(" ");
                  if(splitted[0].equals("rectangle")){
                        shapes.add(new Rectangle(Integer.parseInt(splitted[1]), Integer.parseInt(splitted[2]), Integer.parseInt(splitted[3]), Integer.parseInt(splitted[4])));
                  }

                  if(splitted[0].equals("ellipse")){
                        shapes.add(new Ellipse(Integer.parseInt(splitted[1]), Integer.parseInt(splitted[2]), Integer.parseInt(splitted[3]), Integer.parseInt(splitted[4])));
                  }
            }
            
            return shapes;
      }
}