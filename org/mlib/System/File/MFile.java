package org.mlib.System.File;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class MFile {
    private String filename;

    public MFile() {}

    public MFile(String filename) {
        this.filename = filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public void write(String data, boolean isAppend) {
        write(this.filename, data, isAppend);
    }

    public String read() {
        return read(this.filename);
    }

    public static void write(String filename, String data, boolean isAppend) {
        try {
            FileWriter writer = new FileWriter(filename, isAppend);
            writer.write(data);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String read(String filename) {
        StringBuilder builder = new StringBuilder();

        try {
            FileReader reader = new FileReader(filename);
            BufferedReader bufferedReader = new BufferedReader(reader);

            String line;
            while((line = bufferedReader.readLine()) != null)
                builder.append(line);

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return builder.toString();
    }
}