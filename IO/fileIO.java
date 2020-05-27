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
import java.util.Stack;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class fileIO {

      public static void export(String sb) {
            // Initialises a new file chooser
            JFileChooser chooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter("TEXT FILES", "txt", "text");
            chooser.setFileFilter(filter);
            chooser.setCurrentDirectory(new File(System.getProperty("user.dir")));

            // Shows the window
            int retrival = chooser.showSaveDialog(null);

            // Writes to given string to the selected file
            // when the approve button is clicked
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

            // Tries to open a file
            try {
                  Scanner reader = new Scanner(new File("data.txt"));

                  // Appends all lines as an individual string to an arraylist
                  while(reader.hasNextLine()){                  
                              lines.add(reader.nextLine());
                  }

                  // Trims the first line and retrieves the first element
                  String shape = lines.get(0).trim().split("\\s+")[0];

                  //Gets the corresponding operation
                  Operation target = Factory.getOperation(shape);

                  // Executes the current operation
                  group = target.apply(lines, board);
                  
                  reader.close();

            } catch(FileNotFoundException e){
                  System.out.println("An error occured");
            }
            
            // Returns the group
            return group;
      }
}
