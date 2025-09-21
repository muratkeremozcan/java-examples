/** A utility class for string manipulation operations, such as reversing strings. */
public class StringReverser {
  /**
   * Main method that demonstrates string reversal.
   *
   * @param args command line arguments (not used)
   */
  public static void main(String[] args) {
    final String word = "stressed";
    System.out.println(reverse(word));
  }

  /**
   * Reverses the characters in the input string.
   *
   * @param toReverse the string to be reversed
   * @return the reversed string
   */
  private static String reverse(final String toReverse) {
    /*
     * StringBuilder is preferred over string concatenation in loops or whenever you mutate strings
     * repeatedly. Plain concatenation creates a new String object every iteration.
     *
     * String result = "";
     * for (int i = 0; i < 100; i++) {
     *   result += i; // New String object created in each iteration
     * }
     *
     * // Efficient - uses a single StringBuilder
     * StringBuilder sb = new StringBuilder();
     * for (int i = 0; i < 100; i++) {
     *   sb.append(i); // Appends to same buffer
     * }
     * String result = sb.toString();
     */
    final StringBuilder result =
        new StringBuilder(toReverse.length()); // better than String result = "";

    for (int i = toReverse.length() - 1; i >= 0; i--) {
      result.append(toReverse.charAt(i));
    }

    return result.toString();
  }
}
