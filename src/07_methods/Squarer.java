/** A class that demonstrates method return values by calculating squares. */
public class Squarer {
  /**
   * Main method that demonstrates the use of a method that returns a value.
   *
   * @param args Command line arguments (not used)
   */
  public static void main(String[] args) {
    final int hours = squareOf5();
    System.out.println("I'm working " + hours + " hours a week");
  }

  /**
   * Calculates and returns the square of 5.
   *
   * @return The square of 5 (25)
   */
  private static int squareOf5() {
    return 5 * 5;
  }
}
