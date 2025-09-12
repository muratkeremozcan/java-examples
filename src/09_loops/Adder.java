/**
 * A utility class that demonstrates summing odd numbers until reaching a target sum. The class
 * calculates the sum of consecutive odd numbers starting from 1 until the sum meets or exceeds a
 * specified target value.
 */
public class Adder {
  /** The target sum to reach by adding consecutive odd numbers. */
  private static final int TARGET = 100;

  /**
   * Main method that calculates and prints the sum of consecutive odd numbers until the sum meets
   * or exceeds the target value.
   *
   * @param args command line arguments (not used)
   */
  public static void main(String[] args) {
    int total = 0;
    int i = 1;

    // while is just a loop with a condition
    while (total < TARGET) {
      total += i;
      i = i + 2;
    }
    System.out.println(
        "We reach " + total + " when adding up to " + (i - 2) + ". Next odd number would be " + i);
  }
}
