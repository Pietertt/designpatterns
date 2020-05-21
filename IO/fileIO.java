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

      public BaseShape read(Board board){
            BaseShape group = new Group(board);

            ArrayList<String> lines = new ArrayList<String>();

            try {
                  Scanner reader = new Scanner(new File("data.txt"));
                  while(reader.hasNextLine()){
                        lines.add(reader.nextLine());
                        // //lines.add(reader.nextLine() + "\n");
                        // String[] line = reader.nextLine().trim().split("\\s+");
                        // String shape = line[0];
                        // if(!shape.equals("")){
                        //       ArrayList<Value> value = new ArrayList<Value>();
                        //       Location location = new Location(Integer.parseInt(line[1]), Integer.parseInt(line[2]), Integer.parseInt(line[3]), Integer.parseInt(line[4]));

                        //       BaseShape s = this.getShape(shape, value, board);
                        //       Order place = new PlaceShapeCommand(s);
                        //       board.invoker.execute(place);
                        //       group.children.add(s);
                        //       board.app.add(s);

                        //       board.app.revalidate();
                        //       board.app.repaint();

                        // }
                  }
                  
                        String shape = lines.get(0).trim().split("\\s+")[0];
                        group = this.getShape(shape, lines, board);
                  
                  reader.close();

                  //return lines;
            } catch(FileNotFoundException e){
                  System.out.println("An error occured");
            }
            //test();
                   return group;
            // }
      }

      private BaseShape getShape(String shape, ArrayList<String> lines, Board board){
            Operation target = Factory.getOperation(shape);
            return target.apply(lines, board);
      }


}
