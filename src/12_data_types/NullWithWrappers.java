/** Highlights default values for primitive and wrapper static fields. */
public class NullWithWrappers {

  // Declare a primitive integer called x
  private static int x;

  // Declare a wrapper Integer called y; keep explicit null for clarity in the demo
  @SuppressWarnings("PMD.RedundantFieldInitializer")
  private static Integer y = null;

  /**
   * Prints the default values to illustrate the difference between primitives and wrappers.
   *
   * @param args command line arguments (unused)
   */
  public static void main(final String[] args) {
    // Display x value
    System.out.println(x);
    System.out.println(y);

    // Check if y has been initialized and is null
    if (y == null) {
      System.out.println("y is not initialized so take corrective steps here");
    }
  }
}
