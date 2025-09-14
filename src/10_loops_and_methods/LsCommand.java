/**
 * A utility class that simulates basic 'ls' command functionality. It processes directory contents
 * and categorizes them into visible files, hidden files, directories, and nested files.
 */
public final class LsCommand {

  /** Array representing directory contents in the format "/[type] [name]". */
  private static final String[] DIRECTORY_CONTENT = {
    "/d .Trash",
    "/f .history",
    "/d Applications",
    "/f tmp",
    "/f script",
    "/d Documents",
    "/f Documents/.bankAccounts",
    "/f .sshKeys",
    "/d Pictures",
    "/f content",
    "/f Documents/file"
  };

  /**
   * Checks if the element represents a file.
   *
   * @param elem the directory entry to check
   * @return true if the element is a file, false if it's a directory
   */
  private static boolean isFile(final String elem) {
    return elem.substring(0, 3).contains("/f");
  }

  /**
   * Checks if the element is hidden (starts with a dot).
   *
   * @param elem the directory entry to check
   * @return true if the element is hidden, false otherwise
   */
  private static boolean isHidden(final String elem) {
    return elem.contains(".");
  }

  /**
   * Checks if the element is in a subdirectory.
   *
   * @param elem the directory entry to check
   * @return true if the element is in a subdirectory, false otherwise
   */
  private static boolean isNonLocal(final String elem) {
    return elem.substring(3, elem.length()).contains("/");
  }

  /**
   * Prints the summary of directory contents.
   *
   * @param hiddenCounter count of hidden files
   * @param directoryCounter count of directories
   * @param nestedCounter count of nested files
   */
  private static void printSummary(
      final int hiddenCounter, final int directoryCounter, final int nestedCounter) {
    System.out.println();
    System.out.println(
        "With :\n"
            + hiddenCounter
            + " hidden files,\n"
            + directoryCounter
            + " directories,\nAnd "
            + nestedCounter
            + " nested files");
  }

  /**
   * Main method that processes the directory contents and prints the results.
   *
   * @param args command line arguments (not used)
   */
  public static void main(final String[] args) {
    int hiddenCounter = 0;
    int directoryCounter = 0;
    int nestedCounter = 0;

    for (final String elem : DIRECTORY_CONTENT) {
      if (!isFile(elem)) {
        directoryCounter++;
      }
      
      if (!isHidden(elem)) {
        System.out.print(elem.substring(2));
      }
      
      if (isNonLocal(elem)) {
        nestedCounter++;
      } else {
        hiddenCounter++;
      }
    }
    printSummary(hiddenCounter, directoryCounter, nestedCounter);
  }
}
