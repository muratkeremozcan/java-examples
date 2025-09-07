/** A class that demonstrates method parameters and return values by multiplying numbers. */
public class Multiplier {
  /**
   * Main method that demonstrates the use of a method with parameters and return value.
   *
   * @param args Command line arguments (not used)
   */
  public static void main(String[] args) {

    final int n = 5;
    final int m = 6;
    final int hours = mult(n, m);

    System.out.println("I'm working " + hours + " hours a week");
  }

  /**
   * Multiplies two integers and returns the result.
   *
   * @param a The first integer to multiply
   * @param b The second integer to multiply
   * @return The product of a and b
   */
  private static int mult(final int a, final int b) {
    return a * b;
  }
}
