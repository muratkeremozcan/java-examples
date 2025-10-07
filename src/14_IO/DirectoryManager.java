import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

/**
 * Showcases file and folder utilities.
 *
 * <p>Demonstrates mkdir(), createNewFile(), listFiles(), getAbsolutePath(), and getPath().
 */
class DirectoryManager {
  private static final Logger LOGGER = Logger.getLogger(DirectoryManager.class.getName());

  /** Creates a directory, writes a note, and logs path information. */
  public static void main(String[] args) {
    try {
      // Create a directory
      final File notesDir = new File("notes");
      if (notesDir.mkdir()) {
        LOGGER.info("Directory 'notes' created successfully");
      }

      final File noteFile = new File("notes/note.txt");
      if (noteFile.createNewFile()) {
        LOGGER.info("File 'note.txt' created successfully");
      }

      // List contents of the directory
      final File[] files = notesDir.listFiles();
      if (files != null) {
        for (File f : files) {
          LOGGER.info("File: " + f.getName());
        }
      }

      LOGGER.info("Absolute Path: " + noteFile.getAbsolutePath());
      LOGGER.info("Relative Path: " + noteFile.getPath());

    } catch (IOException e) {
      LOGGER.info("An error occurred: " + e.getMessage());
    }
  }
}
