import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.logging.Logger;

/** Java's file classes are used for reading and writing files */
class FileReadWrite {
  private static final Logger LOGGER = Logger.getLogger(FileReadWrite.class.getName());

  public static void main(String[] args) {
    try {
      FileWriter fw = new FileWriter("note.txt", StandardCharsets.UTF_8);
      fw.write("Start from the beginning");
      fw.close();

      // KEY: append mode
      FileWriter fwAppendMode = new FileWriter("note.txt", StandardCharsets.UTF_8, true);
      fwAppendMode.write(" Add to the end");
      fwAppendMode.close();

      FileReader fr = new FileReader("note.txt", StandardCharsets.UTF_8);
      int character;

      // KEY: continuously read a file
      while ((character = fr.read()) != -1) {
        LOGGER.info(String.valueOf((char) character));
      }
      fr.close();
    } catch (IOException e) {
      LOGGER.info("An error occurred: " + e.getMessage());
    }
  }
}
