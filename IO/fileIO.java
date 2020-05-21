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

      public Group read(Board board){
            Group group = new Group(0, 0, 0, 0, board);
            board.currentStrategy = board.groupStrategy;
            group.setStrategy(board.currentStrategy);
            try {
                  Scanner reader = new Scanner(new File("data.txt"));
                  while(reader.hasNextLine()){
                        //lines.add(reader.nextLine() + "\n");
                        String[] line = reader.nextLine().trim().split("\\s+");
                        String shape = line[0];
                        if(!shape.equals("group") && !shape.equals("")){
                              Location location = new Location(Integer.parseInt(line[1]), Integer.parseInt(line[2]), Integer.parseInt(line[3]), Integer.parseInt(line[4]));
                              BaseShape s = this.getShape(shape, location, board);
                              Order place = new PlaceShapeCommand(s);
                              board.invoker.execute(place);
                              group.children.add(s);
                              board.app.add(s);

                              board.app.revalidate();
                              board.app.repaint();

                        }
                  }
                  reader.close();

                  //return lines;
            } catch(FileNotFoundException e){
                  System.out.println("An error occured");
            }
            //test();
                   return group;
            // }
      }

      private BaseShape getShape(String shape, Location location, Board board){
            Operation target = Factory.getOperation(shape).orElseThrow(() -> new IllegalArgumentException("Invalid shape"));
            return target.apply(location, board);
      }


}
