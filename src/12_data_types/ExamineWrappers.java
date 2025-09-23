/** Demonstrates wrapper class utilities and comparisons. */
public class ExamineWrappers {

  /**
   * Compares wrapper values and shows handy constants from the Java standard library.
   *
   * @param args command line arguments (unused)
   */
  public static void main(final String[] args) {
    final Double pi = Math.PI;
    final Double eulers = Math.E;
    // Compare pi's value to euler's
    final int x = pi.compareTo(eulers);
    System.out.println(x);

    // Use Short to print out the maximum short
    // System.out.println(Short.MAX_VALUE);

    // // Use Boolean to turn string "true" to a boolean
    // final boolean y = Boolean.parseBoolean("true");
    // System.out.println(y);
  }
}
