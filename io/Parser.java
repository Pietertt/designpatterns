package io;

import java.io.*;
import java.util.Scanner;

import UI.*;
import strategies.*;

import java.util.*;

import shapes.*;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Parser {

      public Parser(){
      }

      public static void saveFile(Board board) {
            String sb = saveGrammarCorrectly(board);
            JFileChooser chooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter("TEXT FILES", "txt", "text");
            chooser.setFileFilter(filter);
            chooser.setCurrentDirectory(new File("/home/me/Documents"));
            int retrival = chooser.showSaveDialog(null);
            if (retrival == JFileChooser.APPROVE_OPTION) {
                  try {
                        try(FileWriter fw = new FileWriter(chooser.getSelectedFile()+".txt")) {
                              fw.write(sb);
                        }
                  } catch (Exception ex) {
                        ex.printStackTrace();
                  }
            }
      }

      private static String saveGrammarCorrectly(Board board) {
            StringBuilder grammar = new StringBuilder();
            int countIdent = 1;
            for(BaseShape baseShape : board.group();)
            for(Shape shape : board.shapes) {
            }
            return grammar.toString();
      }
}