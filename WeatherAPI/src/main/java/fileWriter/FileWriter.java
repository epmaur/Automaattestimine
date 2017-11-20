package fileWriter;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.nio.file.StandardOpenOption.APPEND;
import static java.nio.file.StandardOpenOption.CREATE;
import static java.nio.file.StandardOpenOption.WRITE;

public class FileWriter {


    public void writeObjectToFile(Object report, String filename) {
        byte data[] = report.toString().getBytes();
        byte newLine[] = "\n".getBytes();
        Path path = Paths.get(filename);
        try (OutputStream outputStream = new BufferedOutputStream(
                Files.newOutputStream(path, CREATE, APPEND))){
            outputStream.write(data, 0, data.length);
            outputStream.write(newLine, 0, newLine.length);

            } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
