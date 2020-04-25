package io;

import java.io.*;

public class fileIO {

    public fileIO(String fileName) {
        createFile(fileName);
    }

    public void createFile(String fileName) {
        try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(fileName + ".txt"), "utf-8"))) {
            writer.write("something");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void selectPath() {

    }
}
