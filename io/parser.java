package io;

import java.io.*;
import java.util.Scanner;

import UI.*;
import strategies.*;

import java.util.*;

import shapes.*;

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

      public ArrayList<BaseShape> get(ArrayList<String> lines){
            ArrayList<BaseShape> shapes = new ArrayList<BaseShape>();
            for(int i = 0; i < lines.size(); i++){
                  String[] splitted = lines.get(i).split(" ");
                  if(splitted[0].equals("rectangle")){
                        BaseShape rectangle = new Rectangle(Integer.parseInt(splitted[1]), Integer.parseInt(splitted[2]), Integer.parseInt(splitted[3]), Integer.parseInt(splitted[4]));
                        shapes.add(rectangle);
                  }

                  if(splitted[0].equals("ellipse")){
                        shapes.add(new Ellipse(Integer.parseInt(splitted[1]), Integer.parseInt(splitted[2]), Integer.parseInt(splitted[3]), Integer.parseInt(splitted[4])));
                  }
            }
            
            return shapes;
      }
}