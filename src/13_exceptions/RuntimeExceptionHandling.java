/**
 * Demonstrates handling of unchecked (runtime) exceptions.
 *
 * <p>Runtime exceptions (such as {@link ArrayIndexOutOfBoundsException}) are unchecked: the
 * compiler does not force callers to catch or declare them, so bugs can reach runtime if guardrails
 * are not added.
 */
public class RuntimeExceptionHandling {

  public static void main(String[] args) {
    // withoutTryCatch(); // try without try-catch, the compiler allows us to do this (unchecked)
    withTryCatch();
  }

  public static void withoutTryCatch() {
    String[] mounts = {"Everest", "K2", "Kangchenjunga", "Lhotse"};
    String selectedMount = mounts[3]; //
    // String selectedMount = mounts[4]; // change the 3 to 4 to throw an exception
    System.out.println("Selected item is: " + selectedMount);
  }

  public static void withTryCatch() {
    try {
      String[] mounts = {"Everest", "K2", "Kangchenjunga", "Lhotse"};
      String selectedMount = mounts[3];
      // String selectedMount = mounts[4]; // change the 3 to 4 to throw an exception
      System.out.println("Selected item is: " + selectedMount);
    } catch (ArrayIndexOutOfBoundsException e) {
      System.out.println("Oops - made a mistake accessing the mounts array with a bad index.");
    }
  }
}
