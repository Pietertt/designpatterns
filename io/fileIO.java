package io;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import java.io.*;

public class fileIO {

    public static void saveFile() {
        String sb = "TEST CONTENT";
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
}
