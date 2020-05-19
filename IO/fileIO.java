package IO;

import shapes.*;
import UI.Board;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

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

      public static ArrayList<String> read(){
            ArrayList<String> lines = new ArrayList<String>();
            // try {
            //       Scanner reader = new Scanner(new File("data.txt"));
            //       while(reader.hasNextLine()){
            //             lines.add(reader.nextLine() + "\n");
            //       }
            //       reader.close();

            //       return lines;
            // } catch(FileNotFoundException e){
            //       System.out.println("An error occured");
            test();
                   return lines;
            // }
      }

      private static void test(){
            Operation target = Factory.getOperation("rectangle").orElseThrow(() -> new IllegalArgumentException("Invalid shape"));
            target.apply();
      }


}
