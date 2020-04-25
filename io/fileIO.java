package io;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.io.*;

public class fileIO {


    public fileIO(String fileName) {
        createFile(fileName, selectPath());
    }

    public void createFile(String fileName, String path) {

        try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(path + "\\" + fileName + ".txt"), "utf-8"))) {
            writer.write("something");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String selectPath() {
        JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        jfc.setDialogTitle("Selecteer waar u dit wilt opslaan: ");
        jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        int returnValue = jfc.showSaveDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            if (jfc.getSelectedFile().isDirectory()) {
                System.out.println("You selected the directory: " + jfc.getSelectedFile());
                String path = "" + jfc.getSelectedFile();
                path = path.replace("\\", "\\\\");
                return path;
            }
        }
        return "Not a valid directory";
    }
}
