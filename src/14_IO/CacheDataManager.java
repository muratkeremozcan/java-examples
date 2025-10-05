import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/** Demonstrates basic file management with exception handling. */
class CacheDataManager {
  private static final Logger LOGGER = Logger.getLogger(CacheDataManager.class.getName());

  public static void main(String[] args) {
    // Create a File object
    File cacheFile = new File("cache.txt");

    try {
      // Check if the file exists
      if (cacheFile.exists()) {
        // Attempt to delete the file
        if (cacheFile.delete()) {
          LOGGER.info("Old cache file deleted successfully.");
        } else {
          LOGGER.warning("Failed to delete the old cache file.");
          return; // Exit if we can't delete the old file
        }
      }

      // Create the new file - this can throw IOException
      if (cacheFile.createNewFile()) {
        LOGGER.info("New cache file created successfully.");
      } else {
        LOGGER.warning("Failed to create the new cache file.");
      }
    } catch (IOException e) {
      LOGGER.log(
          Level.SEVERE, "An error occurred while managing the cache file: " + e.getMessage(), e);
    }
  }
}
