/** A utility class for counting characters in an array of strings. */
public class CharacterCounter {
  /**
   * Main method to demonstrate character counting.
   *
   * @param args command line arguments (not used)
   */
  public static void main(String[] args) {
    final String[] conjugation = {
      "hide", "hindered", "hiding", "hidden", "hindering", "hid", "hides", "hinder"
    };
    final int tabLength = conjugation.length;
    int numberOfCharacters = 0;

    for (int i = 0; i < tabLength; i++) {
      final String word = conjugation[i];
      numberOfCharacters += word.length();
    }
    System.out.println("There are " + numberOfCharacters + "characters in the list");
  }
}
