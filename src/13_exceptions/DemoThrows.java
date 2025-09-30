/**
 * Demonstrates when and how to throw unchecked (runtime) exceptions.
 *
 * <p>Runtime exceptions (such as {@link IllegalArgumentException}) are unchecked: the compiler does
 * not force callers to catch or declare them, so code that invokes our methods must watch for
 * problems at runtime. We throw them when a method cannot honor its contract—typically because the
 * caller supplied bad input—and we need to hand control back to the caller so they can decide what
 * to do next.
 */
public class DemoThrows {

  private static final String[] MOUNTS = {"Everest", "K2", "Kangchenjunga", "Lhotse"};

  /** Runs the example that triggers an unchecked exception when misused. */
  public static void main(String[] args) {
    // show what happens if we let the exception propagate without a guard
    // withoutTryCatch(5); // uncomment to see the program stop with an exception

    withTryCatch(2); // valid index, nothing thrown
    // withTryCatch(5); // uncomment: triggers the throw in selectMountain
  }

  /**
   * Shows how an unchecked exception bubbles up when no defensive code is in place.
   *
   * @param index position in the mount list
   */
  public static void withoutTryCatch(final int index) {
    // No defensive code here—the runtime will still receive the same IllegalArgumentException.
    String selectedMount = selectMountain(index);
    System.out.println("Selected item is: " + selectedMount);
  }

  /**
   * Guards the lookup with a try-catch so the caller can log and continue.
   *
   * @param index position in the mount list
   */
  public static void withTryCatch(final int index) {
    try {
      String selectedMount = selectMountain(index);
      System.out.println("Selected item is: " + selectedMount);
    } catch (IllegalArgumentException e) {
      System.out.println(
          "Oops - made a mistake accessing the mounts array with a bad index: " + e.getMessage());
    }
  }

  /**
   * Validates the index before returning, throwing when the argument falls outside the valid range.
   *
   * @param index position in the mount list
   * @return the selected mountain
   */
  private static String selectMountain(final int index) {
    if (index < 0 || index >= MOUNTS.length) {
      // This method cannot choose a sensible fallback, so it signals the caller by throwing.
      throw new IllegalArgumentException(
          "index " + index + " is outside valid range 0-" + (MOUNTS.length - 1));
    }
    return MOUNTS[index];
  }
}
