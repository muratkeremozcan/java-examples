import java.util.Arrays;
import java.util.Locale;

/** Demonstrates string cleanup with regex replacements. */
public class CleanStringDemo {
  public static void main(String[] args) {
    regexExample();
    regexArrayExample();
  }

  /** Use regex to clean a string. */
  private static void regexExample() {
    String messyProduct = "Headphones *(Wireless)*";

    // Complete the regex pattern to exclude lower or upper case letters or spaces
    String regex = "[^a-zA-Z ]";

    // Remove any character that is not a letter or space
    String cleanedProduct = messyProduct.replaceAll(regex, "");
    System.out.println(cleanedProduct);
  }

  /**
   * Streams feel like TypeScript's array chains, but the work happens only at the terminal step.
   */
  private static void regexArrayExample() {
    String[] messyProducts = {
      "Laptop ", "SMARTPHONE",
      "Monitor *(Display)*", "   Bluetooth    Speaker   "
    };

    // trim() removes leading and trailing spaces
    Arrays.stream(messyProducts)
        .map(
            s ->
                s.trim()
                    .replaceAll("[^a-zA-Z ]", "") // replace lower/upper letters & spaces
                    .replaceAll("\\s+", " ") // replace multiple spaces with a single space
                    .toLowerCase(Locale.ROOT))
        .forEach(System.out::println);
  }
}
