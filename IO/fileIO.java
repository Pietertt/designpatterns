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

public class fileIO {
      private Group importedGroup;
      private Group currentGroup;

      private Stack<Group> previousGroup;

      private int whiteSpaceCounter = 0;
      private int previousWhiteSpaceCount = 0;
      private int indentLimit = 0;

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

      public Group importFile(Board board) {
            previousGroup = new Stack<>();
            currentGroup = new Group(0,0,0,0,board);
            //importedGroup = new Group(0,0,0,0,board);
            try {
                  File myObj = new File("data.txt");
                  Scanner myReader = new Scanner(myObj);
                  while (myReader.hasNextLine()) {
                        String data = myReader.nextLine();
                        for (char c : data.toCharArray()) {
                              if (c == '\t') {
                                    whiteSpaceCounter++;
                              }
                              if(!(c == '\t')) {
                                    break;
                              }
                        }
                        String[] words = data.split("\\s+");
                        for (int i = 0; i < words.length; i++) {
                              words[i] = words[i].replaceAll("[^\\w]", "");
                        }
                        System.out.println(whiteSpaceCounter);

                        if(whiteSpaceCounter >= 1) {
                              System.out.println(words[1]);
                              if (words[1].contains("group") /* && whiteSpaceCounter > 3 */&& whiteSpaceCounter == previousWhiteSpaceCount) {
                                    previousGroup.push(currentGroup);
                                    Group group = new Group(0,0,0,0,board);
                                    currentGroup.children.add(group);
                                    //currentGroup = group;
                              }
                              if (words[1].contains("group") /*&& whiteSpaceCounter > 1*/ && whiteSpaceCounter < previousWhiteSpaceCount) {
                                    int goBack = previousWhiteSpaceCount / whiteSpaceCounter;
                                    for(int i = 0; i < goBack; i++) {
                                          currentGroup = previousGroup.pop();
                                    }
                              }
                              if (words[1].contains("rectangle") || words[1].contains("ellipse") || words[1].contains("triangle")) {
                                    Location location = new Location(Integer.parseInt(words[2]),
                                            Integer.parseInt(words[3]), Integer.parseInt(words[4]),
                                            Integer.parseInt(words[5]));
                                    BaseShape shape = this.getShape(words[1], location, board);
                                    Order place = new PlaceShapeCommand(shape);
                                    board.invoker.execute(place);
                                    currentGroup.children.add(shape);
                                    board.app.add(shape);

                                    board.app.revalidate();
                                    board.app.repaint();
                              }
                        }
                        previousWhiteSpaceCount = whiteSpaceCounter;
                        whiteSpaceCounter = 0;
                        //System.out.println(data);
                  }
                  myReader.close();
            } catch (FileNotFoundException e) {
                  System.out.println("An error occurred.");
                  e.printStackTrace();
            }
            whiteSpaceCounter = 0;

            importedGroup = currentGroup;
            return importedGroup;
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
