package io;

import shapes.ellipse;
import shapes.rectangle;
import shapes.shape;
import ui.board;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.io.*;

public class fileIO {

    public static void saveFile(board board) {
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

    private static String saveGrammarCorrectly(board board) {
        StringBuilder grammar = new StringBuilder();
        int countIdent = 1;
        for(shape shape : board.shapes) {
            if(shape.getClass().equals(rectangle.class) && shape.shapes.isEmpty() && shape.isRoot()) {
                grammar.append("rectangle " + shape.getX() + " " + shape.getY() + " "
                        + shape.getWidth() + " " + shape.getHeight()+ "\n");
            } else if(shape.getClass().equals(ellipse.class) && shape.shapes.isEmpty() && shape.isRoot()) {
                grammar.append("ellipse " + shape.getX() + " " + shape.getY() + " "
                        + shape.getWidth() + " " + shape.getHeight()+ "\n");
            }
            int countGroup = 1;
            if(!shape.shapes.isEmpty()) {
                for(shape shape1 : shape.shapes) {
                    countGroup++;
                }

                grammar.append("group " + countGroup + "\n");
                countIdent++;

                if(shape.getClass().equals(rectangle.class) && !shape.shapes.isEmpty() && shape.isRoot()) {
                    grammar.append(" ".repeat(Math.max(0, countIdent)));
                    grammar.append("rectangle " + shape.getX() + " " + shape.getY() + " "
                            + shape.getWidth() + " " + shape.getHeight()+ "\n");
                } else if(shape.getClass().equals(ellipse.class) && !shape.shapes.isEmpty() && shape.isRoot()) {
                    grammar.append(" ".repeat(Math.max(0, countIdent)));
                    grammar.append("ellipse " + shape.getX() + " " + shape.getY() + " "
                            + shape.getWidth() + " " + shape.getHeight()+ "\n");
                }

                for(shape shape1 : shape.shapes) {
                    if(shape1.getClass().equals(rectangle.class)) {
                        grammar.append(" ".repeat(Math.max(0, countIdent)));

                        grammar.append("rectangle " + shape1.getX() + " " + shape1.getY() + " "
                                + shape1.getWidth() + " " + shape1.getHeight() + "\n");
                    } else {
                        grammar.append(" ".repeat(Math.max(0, countIdent)));

                        grammar.append("ellipse " + shape1.getX() + " " + shape1.getY() + " "
                                + shape1.getWidth() + " " + shape1.getHeight()+ "\n");
                    }
                }
                countIdent++;
            }
        }
        return grammar.toString();
    }
}
