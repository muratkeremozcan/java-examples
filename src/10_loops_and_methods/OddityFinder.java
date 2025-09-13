/**
 * A utility class for finding and working with odd numbers in an array. Demonstrates the use of
 * helper methods and array iteration.
 */
public class OddityFinder {
  /**
   * Main method that demonstrates finding odd numbers in an array. Iterates through an array of
   * integers and prints those that are odd.
   *
   * @param args command line arguments (not used)
   */
  public static void main(String[] args) {
    final int[] intArray = {32, 67, 86, 90, 26, 34, 92, 13, 70, 50};

    for (final int number : intArray) {
      if (isOdd(number)) {
        System.out.println(number);
      }
    }
  }

  /**
   * Determines if a number is odd.
   *
   * @param n the integer to check
   * @return true if the number is odd, false otherwise
   */
  public static boolean isOdd(final int n) {
    return n % 2 != 0;
  }
}
