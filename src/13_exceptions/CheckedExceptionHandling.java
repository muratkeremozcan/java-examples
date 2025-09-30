/**
 * Demonstrates handling of a checked exception.
 *
 * <p>Checked exceptions (like {@link ClassNotFoundException}) are part of a method's signature and
 * the compiler forces callers to either catch or declare them.
 */
public class CheckedExceptionHandling {

  /** Entry point demonstrating that checked exceptions must be caught (or declared) by callers. */
  public static void main(String[] args) {
    // try this line by itself, the compiler will throw an error
    // Class.forName("java.util.ArrayList");

    // this is why we have to try-catch checked exceptions
    try {
      Class.forName("java.util.ArrayList");
    } catch (ClassNotFoundException e) {
      System.out.println("Work goes here to recover from the checked exception");
    }
    System.out.println("Work complete");
  }
}
