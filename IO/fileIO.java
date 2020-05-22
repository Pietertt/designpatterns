package IO;

import shapes.*;
import UI.Board;
import commands.*;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class fileIO {
      public static void export(String sb) {
            JFileChooser chooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter("TEXT FILES", "txt", "text");
            chooser.setFileFilter(filter);
            chooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
            int retrival = chooser.showSaveDialog(null);
            if (retrival == JFileChooser.APPROVE_OPTION) {
                  try {
                        try (FileWriter fw = new FileWriter(chooser.getSelectedFile())) {
                              fw.write(sb);
                        }
                  } catch (Exception ex) {
                        ex.printStackTrace();
                  }
            }
      }

      public BaseShape read(Board board) {
            BaseShape group = new Group(board);
            ArrayList<String> lines = new ArrayList<String>();

            try {
                  Scanner reader = new Scanner(new File("data.txt"));
                  while(reader.hasNextLine()){                  
                        //if(!(reader.nextLine() == "\r\n")){
                              lines.add(reader.nextLine());
                        //}
                  }

                  String shape = lines.get(0).trim().split("\\s+")[0];
                  Operation target = Factory.getOperation(shape);
                  group = target.apply(lines, board);
                  
                  reader.close();

            } catch(FileNotFoundException e){
                  System.out.println("An error occured");
            }
            
            return group;
      }
}
