/** Demonstrates basic arithmetic operations in Java. */
public class BasicMaths {
  /**
   * Main method to demonstrate various arithmetic operations.
   *
   * @param args command line arguments (not used)
   */
  public static void main(String[] args) {
    final int varA = 5 + 6;
    final double varB = varA - 3.5;
    final int varC = 3 * 3;
    final int varD1 = varC / 3;
    final double varD2 = varC / 3;

    System.out.printf("int varD1: %d%n", varD1);
    System.out.printf("double varD2: %.1f%n", varD2);
    System.out.printf("double varB: %.1f%n", varB);
    // %d - integer
    // %f - floating point (use .1f for 1 decimal place, .2f for 2, etc.)
    // %s - string
    // %n - platform-independent newline
  }
}
