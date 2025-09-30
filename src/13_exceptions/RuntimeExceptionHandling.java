/**
 * Demonstrates when and how to throw unchecked (runtime) exceptions.
 *
 * <p>Runtime exceptions (such as {@link IllegalArgumentException}) are unchecked: the compiler does
 * not force callers to catch or declare them. When a method cannot fulfill its promise because the
 * caller passes invalid data, the method should throw an exception so the caller can decide how to
 * recover.
 */
public class RuntimeExceptionHandling {

  private static final String[] MOUNTS = {"Everest", "K2", "Kangchenjunga", "Lhotse"};

  /** Entry point that runs both scenarios: one succeeds, the other triggers a thrown exception. */
  public static void main(String[] args) {
    // Remove the comment on the next line to watch the exception stop the program
    // withoutTryCatch(5);

    withTryCatch(2); // valid index, nothing thrown
    withTryCatch(5); // invalid index triggers the throw in selectMountain
  }

  /**
   * Looks up a mountain without guarding against invalid input, letting the exception propagate.
   *
   * @param index position in the mount list
   */
  public static void withoutTryCatch(final int index) {
    String selectedMount = selectMountain(index);
    System.out.println("Selected mountain is: " + selectedMount);
  }

  /**
   * Looks up a mountain while catching invalid indexes so the caller can recover gracefully.
   *
   * @param index position in the mount list
   */
  public static void withTryCatch(final int index) {
    try {
      String selectedMount = selectMountain(index);
      System.out.println("Selected mountain is: " + selectedMount);
    } catch (IllegalArgumentException e) {
      System.out.println(
          "Oops - made a mistake accessing the mounts array with a bad index: " + e.getMessage());
    }
  }

  /**
   * Validates the provided index and returns the matching mountain name.
   *
   * <p>If the index is outside the valid range the method throws an IllegalArgumentException. It
   * cannot honor its contract with that input, so it tells the caller to decide how to continue.
   *
   * @param index position in the mount list
   * @return the name of the selected mountain
   */
  private static String selectMountain(final int index) {
    if (index < 0 || index >= MOUNTS.length) {
      // Method cannot honor its contract with this input, so it signals the caller by throwing.
      throw new IllegalArgumentException(
          "index " + index + " is outside valid range 0-" + (MOUNTS.length - 1));
    }
    return MOUNTS[index];
  }
}
