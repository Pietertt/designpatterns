package io;

import java.io.*;
import java.util.Scanner;
import java.util.*;

public class parser {
      private File file;

      // Nieuwe file wordt aangemaakt.
      public parser(String filename){
            this.file = new File(filename);
      }


      // Inlezen van een file.
      public ArrayList<String> read(){
            ArrayList<String> lines = new ArrayList<String>();
            try {
                  Scanner reader = new Scanner(this.file);
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
}