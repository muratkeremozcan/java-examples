/**
 * A demonstration class showing the use of the 'break' keyword to exit a loop. This class
 * demonstrates how to use a for loop with a break statement to terminate the loop when a specific
 * condition is met.
 */
public class Breaker {
  private static final int MAX_VALUE = 8;

  /**
   * Main method that demonstrates loop termination using the break statement. The loop doubles the
   * counter each iteration and breaks when the value exceeds 8.
   *
   * @param args command line arguments (not used)
   */
  public static void main(String[] args) {
    for (int i = 1; i < 10; i *= 2) {
      System.out.println(i);
      if (i > MAX_VALUE) {
        // Exit the loop when i exceeds 8
        break;
      }
    }
    System.out.println("Broke out of the loop");
  }
}
