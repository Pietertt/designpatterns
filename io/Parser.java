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

      // Returns an arraylist of lines from a file
      public ArrayList<String> read(String filename){
            ArrayList<String> lines = new ArrayList<String>();
            try {
                  File file = new File(filename);
                  Scanner reader = new Scanner(file);
                  // Appends all lines to the arraylist 
                  while(reader.hasNextLine()){
                        lines.add(reader.nextLine());
                  }
                  reader.close();

                  return lines;
            // Throws an error when the file is not found
            } catch(FileNotFoundException e){
                  System.out.println("An error occured");
                  return lines;
            }
      }

      // Returns an arraylist based on the input arraylist
      public ArrayList<Shape> get(ArrayList<String> lines){
            ArrayList<Shape> shapes = new ArrayList<Shape>();
            // Loops through the input arraylist
            for(int i = 0; i < lines.size(); i++){
                  // Splits the current line on each blank character
                  String[] splitted = lines.get(i).split(" ");
                  // Returns a rectangle when the first item contains the string 'rectangle'
                  if(splitted[0].equals("rectangle")){
                        shapes.add(new Rectangle(Integer.parseInt(splitted[1]), Integer.parseInt(splitted[2]), Integer.parseInt(splitted[3]), Integer.parseInt(splitted[4])));
                  }
                  // Returns a ellipse when the first item contains the string 'ellipse'
                  if(splitted[0].equals("ellipse")){
                        shapes.add(new Ellipse(Integer.parseInt(splitted[1]), Integer.parseInt(splitted[2]), Integer.parseInt(splitted[3]), Integer.parseInt(splitted[4])));
                  }
            }
            
            return shapes;
      }
}