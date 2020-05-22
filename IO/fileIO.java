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
            currentGroup = new Group(board);
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
                                    Group group = new Group(board);
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
                                   // BaseShape shape = this.getShape(words[1], location, board);
//                                    Order place = new PlaceShapeCommand(shape);
//                                    board.invoker.execute(place);
//                                    currentGroup.children.add(shape);
//                                    board.app.add(shape);

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
