import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.logging.Logger;

/** Java's buffered classes help read and write large chunks of text efficiently. */
public class FileOperations {
  private static final Logger LOGGER = Logger.getLogger(FileOperations.class.getName());

  /** Demonstrates buffered reading and writing using the same backing file. */
  public static void main(String[] args) {
    try {
      // KEY: BufferedWriter is useful when writing multiple lines to a file
      BufferedWriter bw = new BufferedWriter(new FileWriter("note.txt", StandardCharsets.UTF_8));
      bw.write("This is the first line");
      bw.newLine();
      bw.write("This is the second line");
      bw.close();

      // KEY: BufferedReader is useful when reading multiple lines from a file
      BufferedReader br = new BufferedReader(new FileReader("note.txt", StandardCharsets.UTF_8));
      String line;
      while ((line = br.readLine()) != null) {
        LOGGER.info(line);
      }

      // Close the BufferedReader
      br.close();

    } catch (IOException e) {
      LOGGER.info("An error occurred: " + e.getMessage());
    }
  }
}
