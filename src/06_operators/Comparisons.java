/** Demonstrates comparison operators in Java. */
public class Comparisons {
  /**
   * Main method to demonstrate comparison operations.
   *
   * @param args Command line arguments (not used)
   */
  public static void main(String[] args) {
    final boolean first = 5 < 7;
    final boolean second = 9 >= 2;
    System.out.println("5 is smaller than 7: " + first);
    System.out.println("9 is greater or equal to 2: " + second);

    final int a = 193;
    final boolean third = 193 >= a;
    System.out.println("193 is greater or equal to a: " + third);
  }
}
