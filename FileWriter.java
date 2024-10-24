
import java.io.*;

public class FileWriter {
    public static void writeToFile(String filePath, String data) {
        try (BufferedWriter bw = new BufferedWriter(new java.io.FileWriter(filePath, true))) {
            bw.write(data);
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
