/** A simple greeter class that demonstrates method usage in Java. */
public class Greeter {
  /**
   * Main method that demonstrates both generic and specific greetings.
   *
   * @param args Command line arguments (not used)
   */
  public static void main(String[] args) {
    genericGreeter();
    final String firstName = "Murat";
    specificGreeter(firstName);
  }

  /**
   * Prints a personalized greeting message.
   *
   * @param name The name of the person to greet
   */
  private static void specificGreeter(final String name) {
    System.out.println("Nice day to you, " + name + "!");
  }

  /** Prints a generic greeting message. */
  private static void genericGreeter() {
    System.out.println("Nice day to you !");
  }
}
