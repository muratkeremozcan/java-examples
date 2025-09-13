/**
 * A utility class for finding the longest string in an array of strings. Demonstrates array
 * iteration and string manipulation.
 */
public class LongestFinder {
  /**
   * Main method that demonstrates finding the longest string in an array.
   *
   * @param args command line arguments (not used)
   */
  public static void main(String[] args) {
    final String[] words = {
      "possible", "first", "null", "avoidance", "mineral", "pretty", "tree", "rather", "innocuous"
    };

    System.out.println(findLongestIn(words));
  }

  /**
   * Finds and returns the longest string in the given array. Returns an empty string if the array
   * is empty.
   *
   * @param wordsList the array of strings to search through
   * @return the longest string found, or an empty string if the array is empty
   */
  private static String findLongestIn(final String[] wordsList) {
    String longest = "";

    for (final String word : wordsList) {
      if (word.length() > longest.length()) {
        longest = word;
      }
    }
    return longest;
  }
}
